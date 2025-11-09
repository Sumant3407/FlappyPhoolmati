package com.flappy.phoolmati;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.Random;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread gameThread;
    private Character character;
    private SoundManager soundManager;
    private HighScoreManager highScoreManager;

    // Game objects
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private int score = 0;
    private boolean gameOver = false;
    private boolean gameStarted = false;

    // Bitmaps
    private Bitmap birdBitmap;
    private Bitmap pipeBitmap;
    private Bitmap backgroundBitmap;

    // Screen dimensions
    private int screenWidth;
    private int screenHeight;

    // Paint objects
    private Paint scorePaint;
    private Paint gameOverPaint;
    private Paint instructionPaint;

    // Game constants
    private static final int PIPE_GAP = 400;
    private static final int PIPE_SPEED = 10;
    private static final int PIPE_SPACING = 600;
    private static final int GROUND_HEIGHT = 100;

    public GameView(Context context, Character character, SoundManager soundManager, HighScoreManager highScoreManager) {
        super(context);
        this.character = character;
        this.soundManager = soundManager;
        this.highScoreManager = highScoreManager;

        getHolder().addCallback(this);
        gameThread = new GameThread(getHolder(), this);

        initializeBitmaps();
        initializeGame();
        initializePaints();
    }

    private void initializeBitmaps() {
    try {
        birdBitmap = BitmapFactory.decodeResource(getResources(), character.getCharacterImageResId());
        if (birdBitmap == null) {
            throw new Exception("Failed to decode bird bitmap");
        }
        pipeBitmap = BitmapFactory.decodeResource(getResources(), character.getObstacleImageResId());
        if (pipeBitmap == null) {
            throw new Exception("Failed to decode pipe bitmap");
        }
        
        int birdSize = 150;
        birdBitmap = Bitmap.createScaledBitmap(birdBitmap, birdSize, birdSize, true);
        
        int pipeWidth = 150;
        pipeBitmap = Bitmap.createScaledBitmap(pipeBitmap, pipeWidth, 1000, true);
    } catch (Exception e) {
        birdBitmap = Bitmap.createBitmap(150, 150, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(birdBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#FF6B9E"));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(75, 75, 75, paint);
        
        pipeBitmap = Bitmap.createBitmap(150, 1000, Bitmap.Config.ARGB_8888);
        pipeBitmap.eraseColor(Color.parseColor("#8B4513"));
    }
}
    private void initializeGame() {
        pipes = new ArrayList<>();
        // Bird position will be set when surface is created
        int initialY = screenHeight > 0 ? screenHeight / 2 : 500;
        bird = new Bird(200, initialY, birdBitmap.getWidth(), birdBitmap.getHeight());
    }

    private void initializePaints() {
        scorePaint = new Paint();
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(60);
        scorePaint.setStyle(Paint.Style.FILL);
        scorePaint.setAntiAlias(true);

        gameOverPaint = new Paint();
        gameOverPaint.setColor(Color.RED);
        gameOverPaint.setTextSize(80);
        gameOverPaint.setStyle(Paint.Style.FILL);
        gameOverPaint.setAntiAlias(true);
        gameOverPaint.setTextAlign(Paint.Align.CENTER);

        instructionPaint = new Paint();
        instructionPaint.setColor(Color.WHITE);
        instructionPaint.setTextSize(40);
        instructionPaint.setStyle(Paint.Style.FILL);
        instructionPaint.setAntiAlias(true);
        instructionPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        screenWidth = getWidth();
        screenHeight = getHeight();
        
        // Reinitialize bird position and bitmaps if needed
        if (screenWidth > 0 && screenHeight > 0) {
            if (bird == null || bird.getHeight() == 0) {
                bird = new Bird(200, screenHeight / 2, birdBitmap.getWidth(), birdBitmap.getHeight());
            } else {
                // Update bird Y position to center
                bird = new Bird(200, screenHeight / 2, birdBitmap.getWidth(), birdBitmap.getHeight());
            }
        }
        
        if (!gameThread.isRunning()) {
            gameThread.setRunning(true);
            gameThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        screenWidth = width;
        screenHeight = height;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                gameThread.setRunning(false);
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if (gameOver || !gameStarted) {
            return;
        }

        // Update bird
        bird.update();

        // Generate pipes
        if (pipes.isEmpty() || pipes.get(pipes.size() - 1).getX() < screenWidth - PIPE_SPACING) {
            generatePipe();
        }

        // Update pipes
        for (int i = pipes.size() - 1; i >= 0; i--) {
            Pipe pipe = pipes.get(i);
            pipe.update();

            // Check collision
            Rect birdRect = bird.getRect();
            if (Rect.intersects(birdRect, pipe.getTopRect()) || 
                Rect.intersects(birdRect, pipe.getBottomRect())) {
                gameOver();
                return;
            }

            // Check if bird passed pipe
            if (!pipe.isScored() && pipe.getX() + pipe.getWidth() < bird.getX()) {
                pipe.setScored(true);
                score++;
            }

            // Remove pipes that are off screen
            if (pipe.getX() + pipe.getWidth() < 0) {
                pipes.remove(i);
            }
        }

        // Check ground and ceiling collision
        if (bird.getY() + bird.getHeight() >= screenHeight - GROUND_HEIGHT || bird.getY() <= 0) {
            gameOver();
        }
    }

    private void generatePipe() {
        if (screenHeight == 0) return; // Wait for surface to be created
        Random random = new Random();
        int minGapY = 150;
        int maxGapY = screenHeight - PIPE_GAP - GROUND_HEIGHT - 150;
        if (maxGapY < minGapY) maxGapY = minGapY + 100;
        int gapY = random.nextInt(maxGapY - minGapY) + minGapY;
        pipes.add(new Pipe(screenWidth, gapY, PIPE_GAP, pipeBitmap.getWidth(), screenHeight - GROUND_HEIGHT));
    }

    private void gameOver() {
        if (!gameOver) {
            gameOver = true;
            soundManager.playGameOverSound();
            soundManager.pauseBackgroundMusic();
            
            // Check for high score
            boolean isNewHighScore = highScoreManager.setHighScore(score);
            if (isNewHighScore) {
                soundManager.playHighScoreSound();
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        
        if (canvas != null) {
            // Draw background
            canvas.drawColor(Color.parseColor("#4EC0CA"));

            // Draw ground
            Paint groundPaint = new Paint();
            groundPaint.setColor(Color.parseColor("#8B4513"));
            canvas.drawRect(0, screenHeight - GROUND_HEIGHT, screenWidth, screenHeight, groundPaint);

            // Draw pipes
            for (Pipe pipe : pipes) {
                Rect topRect = pipe.getTopRect();
                Rect bottomRect = pipe.getBottomRect();
                
                // Draw top pipe (flipped)
                canvas.save();
                canvas.translate(pipe.getX() + pipeBitmap.getWidth() / 2, topRect.bottom);
                canvas.scale(1, -1); // Flip vertically
                canvas.drawBitmap(pipeBitmap, -pipeBitmap.getWidth() / 2, 0, null);
                canvas.restore();
                
                // Draw bottom pipe
                canvas.drawBitmap(pipeBitmap, pipe.getX(), bottomRect.top, null);
            }

            // Draw bird
            canvas.drawBitmap(birdBitmap, bird.getX(), bird.getY(), null);

            // Draw score
            canvas.drawText("Score: " + score, 50, 100, scorePaint);

            // Draw high score
            int highScore = highScoreManager.getHighScore();
            canvas.drawText("High: " + highScore, screenWidth - 250, 100, scorePaint);

            // Draw game over
            if (gameOver) {
                canvas.drawText("GAME OVER", screenWidth / 2, screenHeight / 2 - 100, gameOverPaint);
                canvas.drawText("Tap to Restart", screenWidth / 2, screenHeight / 2, instructionPaint);
            } else if (!gameStarted) {
                canvas.drawText("Tap to Start", screenWidth / 2, screenHeight / 2, instructionPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (gameOver) {
                restartGame();
            } else {
                if (!gameStarted) {
                    gameStarted = true;
                }
                bird.jump();
                soundManager.playJumpSound();
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void restartGame() {
        gameOver = false;
        gameStarted = false;
        score = 0;
        bird = new Bird(200, screenHeight / 2, birdBitmap.getWidth(), birdBitmap.getHeight());
        pipes.clear();
        soundManager.resumeBackgroundMusic();
    }

   public void pause() {
    if (gameThread != null) {
        gameThread.setRunning(false);
    }
    soundManager.pauseBackgroundMusic();
}

public void resume() {
    if (gameThread != null && !gameThread.isRunning()) {
        gameThread.setRunning(true);
        try {
            gameThread = new GameThread(getHolder(), this);
            gameThread.start();
        } catch (IllegalThreadStateException e) {
        }
    }
    if (soundManager != null) {
        soundManager.resumeBackgroundMusic();
    }
}
    private class Bird {
        private float x, y;
        private float velocity = 0;
        private int width, height;
        private static final float GRAVITY = 0.6f;
        private static final float JUMP_VELOCITY = -12f;

        public Bird(float x, float y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public void update() {
            velocity += GRAVITY;
            y += velocity;
        }

        public void jump() {
            velocity = JUMP_VELOCITY;
        }

        public float getX() { return x; }
        public float getY() { return y; }
        public int getWidth() { return width; }
        public int getHeight() { return height; }

        public Rect getRect() {
            return new Rect((int)x, (int)y, (int)(x + width), (int)(y + height));
        }
    }

    // Pipe class
    private class Pipe {
        private float x;
        private int gapY;
        private int gap;
        private int width;
        private int screenHeight;
        private boolean scored = false;

        public Pipe(float x, int gapY, int gap, int width, int screenHeight) {
            this.x = x;
            this.gapY = gapY;
            this.gap = gap;
            this.width = width;
            this.screenHeight = screenHeight;
        }

        public void update() {
            x -= PIPE_SPEED;
        }

        public float getX() { return x; }
        public int getWidth() { return width; }
        public boolean isScored() { return scored; }
        public void setScored(boolean scored) { this.scored = scored; }

        public Rect getTopRect() {
            return new Rect((int)x, 0, (int)(x + width), gapY);
        }

        public Rect getBottomRect() {
            return new Rect((int)x, gapY + gap, (int)(x + width), screenHeight);
        }
    }

    // Game thread
    private class GameThread extends Thread {
        private SurfaceHolder surfaceHolder;
        private GameView gameView;
        private boolean running;

        public GameThread(SurfaceHolder surfaceHolder, GameView gameView) {
            this.surfaceHolder = surfaceHolder;
            this.gameView = gameView;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }

        public boolean isRunning() {
            return running;
        }

        @Override
        public void run() {
            Canvas canvas;
            while (running) {
                canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder) {
                        if (canvas != null) {
                            gameView.update();
                            gameView.draw(canvas);
                        }
                    }
                } finally {
                    if (canvas != null) {
                        try {
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    Thread.sleep(16); // ~60 FPS
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

