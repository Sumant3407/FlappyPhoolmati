package com.flappy.phoolmati;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Character> characters;
    private int currentCharacterIndex = 0;
    private ImageView characterImageView;
    private TextView characterNameView;
    private Button startButton;
    private Button prevButton;
    private Button nextButton;
    private TextView highScoreView;
    private HighScoreManager highScoreManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        highScoreManager = new HighScoreManager(this);
        initializeCharacters();
        initializeViews();
        updateCharacterDisplay();
        updateHighScore();
    }

    private void initializeCharacters() {
        characters = new ArrayList<>();
        
        // Character 1 - Default Bird
        characters.add(new Character(
            "Phoolmati",
            R.drawable.character1,
            R.raw.bg_music1,
            R.raw.jump_sound1,
            R.drawable.obstacle1,
            R.raw.game_over_sound1
        ));
        
        // Character 2
        characters.add(new Character(
            "Flappy",
            R.drawable.character2,
            R.raw.bg_music2,
            R.raw.jump_sound2,
            R.drawable.obstacle2,
            R.raw.game_over_sound2
        ));
        
        // Character 3
        characters.add(new Character(
            "Sky Rider",
            R.drawable.character3,
            R.raw.bg_music3,
            R.raw.jump_sound3,
            R.drawable.obstacle3,
            R.raw.game_over_sound3
        ));
    }

    private void initializeViews() {
        characterImageView = findViewById(R.id.characterImageView);
        characterNameView = findViewById(R.id.characterNameView);
        startButton = findViewById(R.id.startButton);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);
        highScoreView = findViewById(R.id.highScoreView);

        startButton.setOnClickListener(v -> startGame());
        prevButton.setOnClickListener(v -> {
            currentCharacterIndex = (currentCharacterIndex - 1 + characters.size()) % characters.size();
            updateCharacterDisplay();
        });
        nextButton.setOnClickListener(v -> {
            currentCharacterIndex = (currentCharacterIndex + 1) % characters.size();
            updateCharacterDisplay();
        });
    }

    private void updateCharacterDisplay() {
        if (characters != null && !characters.isEmpty()) {
            Character currentCharacter = characters.get(currentCharacterIndex);
            characterImageView.setImageResource(currentCharacter.getCharacterImageResId());
            characterNameView.setText(currentCharacter.getName());
        }
    }

    private void updateHighScore() {
        int highScore = highScoreManager.getHighScore();
        highScoreView.setText(getString(R.string.high_score, highScore));
    }

    private void startGame() {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("character_index", currentCharacterIndex);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateHighScore();
    }
}

