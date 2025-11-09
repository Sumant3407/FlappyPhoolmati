package com.flappy.phoolmati;

import android.content.Context;
import android.content.SharedPreferences;

public class HighScoreManager {
    private static final String PREFS_NAME = "FlappyPhoolmatiPrefs";
    private static final String KEY_HIGH_SCORE = "high_score";
    private SharedPreferences prefs;

    public HighScoreManager(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public int getHighScore() {
        return prefs.getInt(KEY_HIGH_SCORE, 0);
    }

    public boolean setHighScore(int score) {
        int currentHighScore = getHighScore();
        if (score > currentHighScore) {
            prefs.edit().putInt(KEY_HIGH_SCORE, score).apply();
            return true;
        }
        return false;
    }

    public void resetHighScore() {
        prefs.edit().putInt(KEY_HIGH_SCORE, 0).apply();
    }
}

