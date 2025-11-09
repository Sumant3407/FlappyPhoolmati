package com.flappy.phoolmati;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class SoundManager {
    private MediaPlayer backgroundMusic;
    private SoundPool soundPool;
    private int jumpSoundId;
    private int gameOverSoundId;
    private int highScoreSoundId;
    private Context context;
    private boolean soundEnabled = true;
    private boolean musicEnabled = true;

    public SoundManager(Context context) {
        this.context = context;
        soundPool = new SoundPool.Builder()
                .setMaxStreams(10)
                .build();
    }

    public void loadCharacterSounds(Character character) {
        if (character == null || soundPool == null) return;
        
        // Release previous sounds
        releaseSounds();
        
        // Load new sounds (with error handling)
        try {
            jumpSoundId = soundPool.load(context, character.getJumpSoundResId(), 1);
        } catch (Exception e) {
            jumpSoundId = 0;
        }
        try {
            gameOverSoundId = soundPool.load(context, character.getGameOverSoundResId(), 1);
        } catch (Exception e) {
            gameOverSoundId = 0;
        }
    }

    public void loadHighScoreSound(int highScoreSoundResId) {
        if (soundPool == null) return;
        try {
            highScoreSoundId = soundPool.load(context, highScoreSoundResId, 1);
        } catch (Exception e) {
            highScoreSoundId = 0;
        }
    }

    public void playBackgroundMusic(Character character) {
        if (character == null || !musicEnabled) return;
        
        stopBackgroundMusic();
        try {
            backgroundMusic = MediaPlayer.create(context, character.getBackgroundMusicResId());
            if (backgroundMusic != null) {
                backgroundMusic.setLooping(true);
                backgroundMusic.setVolume(0.5f, 0.5f);
                backgroundMusic.start();
            }
        } catch (Exception e) {
            // Music file might be missing, continue without music
            backgroundMusic = null;
        }
    }

    public void stopBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
            backgroundMusic.release();
            backgroundMusic = null;
        }
    }

    public void pauseBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isPlaying()) {
            backgroundMusic.pause();
        }
    }

    public void resumeBackgroundMusic() {
        if (backgroundMusic != null && !backgroundMusic.isPlaying() && musicEnabled) {
            backgroundMusic.start();
        }
    }

    public void playJumpSound() {
        if (soundEnabled && jumpSoundId != 0) {
            soundPool.play(jumpSoundId, 1.0f, 1.0f, 1, 0, 1.0f);
        }
    }

    public void playGameOverSound() {
        if (soundEnabled && gameOverSoundId != 0) {
            soundPool.play(gameOverSoundId, 1.0f, 1.0f, 1, 0, 1.0f);
        }
    }

    public void playHighScoreSound() {
        if (soundEnabled && highScoreSoundId != 0) {
            soundPool.play(highScoreSoundId, 1.0f, 1.0f, 1, 0, 1.0f);
        }
    }

    public void release() {
        releaseSounds();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }

    private void releaseSounds() {
        if (soundPool != null) {
            if (jumpSoundId != 0) {
                soundPool.unload(jumpSoundId);
                jumpSoundId = 0;
            }
            if (gameOverSoundId != 0) {
                soundPool.unload(gameOverSoundId);
                gameOverSoundId = 0;
            }
        }
    }

    public void setSoundEnabled(boolean enabled) {
        this.soundEnabled = enabled;
    }

    public void setMusicEnabled(boolean enabled) {
        this.musicEnabled = enabled;
        if (!enabled) {
            stopBackgroundMusic();
        }
    }
}

