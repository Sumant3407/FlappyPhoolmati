# Build Instructions for Flappy Phoolmati

## Prerequisites

1. **Android Studio** - Download and install the latest version from [developer.android.com](https://developer.android.com/studio)
2. **Android SDK** - Minimum SDK 24 (Android 7.0), Target SDK 34 (Android 14)
3. **Java Development Kit (JDK)** - JDK 8 or higher

## Setup Steps

### 1. Open Project in Android Studio

1. Launch Android Studio
2. Select "Open an existing project"
3. Navigate to the project directory and select it
4. Wait for Gradle sync to complete

### 2. Add Required Assets

Before building, you need to add the game assets:

#### Character Images
- Place character images in `app/src/main/res/drawable/`:
  - `character1.png` (or .jpg/.jpeg)
  - `character2.png` (or .jpg/.jpeg)
  - `character3.png` (or .jpg/.jpeg)

#### Obstacle Images
- Place obstacle images in `app/src/main/res/drawable/`:
  - `obstacle1.png` (or .jpg/.jpeg)
  - `obstacle2.png` (or .jpg/.jpeg)
  - `obstacle3.png` (or .jpg/.jpeg)

#### Sound Files
- Create directory `app/src/main/res/raw/` if it doesn't exist
- Place sound files in `app/src/main/res/raw/`:
  - `bg_music1.mp3`, `bg_music2.mp3`, `bg_music3.mp3`
  - `jump_sound1.mp3`, `jump_sound2.mp3`, `jump_sound3.mp3`
  - `game_over_sound1.mp3`, `game_over_sound2.mp3`, `game_over_sound3.mp3`
  - `high_score_sound.mp3`

**Note:** The game will run with placeholder graphics if images are missing, but sound files are optional (game will continue without sound if files are missing).

### 3. Sync Gradle

1. Click "Sync Now" if prompted
2. Or go to `File > Sync Project with Gradle Files`

### 4. Build the Project

#### For Debug Build:
1. Go to `Build > Make Project`
2. Or use the shortcut `Ctrl+F9` (Windows/Linux) or `Cmd+F9` (Mac)

#### For Release Build:
1. Go to `Build > Generate Signed Bundle / APK`
2. Follow the wizard to create a signed APK or AAB

### 5. Run on Device/Emulator

#### Using Emulator:
1. Create an Android Virtual Device (AVD) from `Tools > Device Manager`
2. Select the device and click the "Run" button (green play icon)
3. Or use `Shift+F10` (Windows/Linux) or `Ctrl+R` (Mac)

#### Using Physical Device:
1. Enable USB debugging on your Android device
2. Connect device via USB
3. Select your device from the device dropdown
4. Click the "Run" button

## Troubleshooting

### Gradle Sync Failed
- Check your internet connection (Gradle needs to download dependencies)
- Ensure you have the correct Android SDK version installed
- Try `File > Invalidate Caches / Restart`

### Assets Not Showing
- Ensure image files are in the correct directory (`res/drawable/`)
- Check that file names match exactly (case-sensitive)
- Rebuild the project after adding assets

### Sound Not Playing
- Verify sound files are in `res/raw/` directory
- Check file format (MP3 or OGG recommended)
- Ensure file names match exactly
- Check device volume settings

### Build Errors
- Clean project: `Build > Clean Project`
- Rebuild project: `Build > Rebuild Project`
- Check that all required files are present
- Verify Android SDK is properly installed

## Testing Without Assets

The game is designed to work even without all assets:
- Missing character images: Will show colored circles
- Missing obstacle images: Will show colored rectangles
- Missing sounds: Game will continue silently (no crashes)

This allows you to test the game mechanics before adding all assets.

## Additional Resources

- [Android Developer Documentation](https://developer.android.com/docs)
- [Android Studio User Guide](https://developer.android.com/studio/intro)
- See `ASSETS_README.md` for detailed asset specifications

