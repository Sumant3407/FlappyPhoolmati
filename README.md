# Flappy Phoolmati - Android Game

A Flappy Bird inspired game with multiple characters, each with unique assets and sound effects.

## Features

- **Character Selection**: Choose from multiple characters in the start menu
- **Unique Characters**: Each character has:
  - Unique character image
  - Unique background music
  - Unique jump sound effect
  - Unique obstacle images
  - Unique game over sound effect
- **High Score System**: Track your best score with a universal high score sound effect
- **Full Screen Gameplay**: Immersive gaming experience

## Setup Instructions

### 1. Asset Requirements

You need to add the following assets to the project:

#### Character Images (Place in `app/src/main/res/drawable/`)
- `character1.png` - First character image (Phoolmati)
- `character2.png` - Second character image (Flappy)
- `character3.png` - Third character image (Sky Rider)

#### Obstacle Images (Place in `app/src/main/res/drawable/`)
- `obstacle1.png` - Obstacles for character 1
- `obstacle2.png` - Obstacles for character 2
- `obstacle3.png` - Obstacles for character 3

#### Sound Effects (Place in `app/src/main/res/raw/`)
- `bg_music1.mp3` - Background music for character 1
- `bg_music2.mp3` - Background music for character 2
- `bg_music3.mp3` - Background music for character 3
- `jump_sound1.mp3` - Jump sound for character 1
- `jump_sound2.mp3` - Jump sound for character 2
- `jump_sound3.mp3` - Jump sound for character 3
- `game_over_sound1.mp3` - Game over sound for character 1
- `game_over_sound2.mp3` - Game over sound for character 2
- `game_over_sound3.mp3` - Game over sound for character 3
- `high_score_sound.mp3` - Universal high score sound effect

### 2. Asset Specifications

#### Images:
- **Character Images**: Recommended size 200x200 pixels, PNG format with transparency
- **Obstacle Images**: Recommended size 150x1000 pixels (will be scaled), PNG format

#### Audio:
- **Background Music**: MP3 or OGG format, loopable
- **Sound Effects**: MP3 or OGG format, short duration (0.5-2 seconds)

### 3. Building the Project

1. Open the project in Android Studio
2. Ensure you have Android SDK 24+ installed
3. Add all required assets to the appropriate directories
4. Build and run the project

### 4. Adding More Characters

To add more characters:

1. Add character image to `res/drawable/`
2. Add obstacle image to `res/drawable/`
3. Add sound files to `res/raw/`
4. Update `MainActivity.java` to add the new character to the `initializeCharacters()` method
5. Update `GameActivity.java` to handle the new character in `getCharacterByIndex()`

## Game Controls

- **Tap Screen**: Make the bird jump/flap
- **Character Selection**: Use left/right arrows in main menu
- **Start Game**: Tap "Start Game" button

## Project Structure

```
app/
├── src/main/
│   ├── java/com/flappy/phoolmati/
│   │   ├── MainActivity.java          # Start menu with character selection
│   │   ├── GameActivity.java          # Game screen
│   │   ├── GameView.java              # Game rendering and logic
│   │   ├── Character.java             # Character data model
│   │   ├── SoundManager.java          # Sound effects and music management
│   │   └── HighScoreManager.java      # High score persistence
│   ├── res/
│   │   ├── drawable/                  # Character and obstacle images
│   │   ├── raw/                       # Sound files
│   │   ├── layout/                    # Activity layouts
│   │   └── values/                    # Strings, colors, etc.
│   └── AndroidManifest.xml
```

## License

This project is open source and available for modification.

