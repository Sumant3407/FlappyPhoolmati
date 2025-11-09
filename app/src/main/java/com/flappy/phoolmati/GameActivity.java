package com.flappy.phoolmati;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    private GameView gameView;
    private SoundManager soundManager;
    private HighScoreManager highScoreManager;
    private Character selectedCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        int characterIndex = getIntent().getIntExtra("character_index", 0);
        selectedCharacter = getCharacterByIndex(characterIndex);

        soundManager = new SoundManager(this);
        highScoreManager = new HighScoreManager(this);

        // Load high score sound (universal)
        soundManager.loadHighScoreSound(R.raw.high_score_sound);
        soundManager.loadCharacterSounds(selectedCharacter);
        soundManager.playBackgroundMusic(selectedCharacter);

        gameView = new GameView(this, selectedCharacter, soundManager, highScoreManager);
        setContentView(gameView);
    }

    private Character getCharacterByIndex(int index) {
        // Same character list as MainActivity
        if (index == 0) {
            return new Character("Phoolmati", R.drawable.character1, R.raw.bg_music1,
                    R.raw.jump_sound1, R.drawable.obstacle1, R.raw.game_over_sound1);
        } else if (index == 1) {
            return new Character("Flappy", R.drawable.character2, R.raw.bg_music2,
                    R.raw.jump_sound2, R.drawable.obstacle2, R.raw.game_over_sound2);
        } else {
            return new Character("Sky Rider", R.drawable.character3, R.raw.bg_music3,
                    R.raw.jump_sound3, R.drawable.obstacle3, R.raw.game_over_sound3);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (gameView != null) {
            gameView.pause();
        }
        soundManager.pauseBackgroundMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (gameView != null) {
            gameView.resume();
        }
        soundManager.resumeBackgroundMusic();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (gameView != null) {
            gameView.destroy();
        }
        soundManager.stopBackgroundMusic();
        soundManager.release();
    }
}

