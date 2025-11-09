package com.flappy.phoolmati;

public class Character {
    private String name;
    private int characterImageResId;
    private int backgroundMusicResId;
    private int jumpSoundResId;
    private int obstacleImageResId;
    private int gameOverSoundResId;

    public Character(String name, int characterImageResId, int backgroundMusicResId,
                     int jumpSoundResId, int obstacleImageResId, int gameOverSoundResId) {
        this.name = name;
        this.characterImageResId = characterImageResId;
        this.backgroundMusicResId = backgroundMusicResId;
        this.jumpSoundResId = jumpSoundResId;
        this.obstacleImageResId = obstacleImageResId;
        this.gameOverSoundResId = gameOverSoundResId;
    }

    public String getName() {
        return name;
    }

    public int getCharacterImageResId() {
        return characterImageResId;
    }

    public int getBackgroundMusicResId() {
        return backgroundMusicResId;
    }

    public int getJumpSoundResId() {
        return jumpSoundResId;
    }

    public int getObstacleImageResId() {
        return obstacleImageResId;
    }

    public int getGameOverSoundResId() {
        return gameOverSoundResId;
    }
}

