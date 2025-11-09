# 🚀 Quick Start Guide - Flappy Phoolmati

## ⚡ Get Up and Running in 5 Minutes!

### 📋 Prerequisites Checklist
- [ ] Android Studio installed ([Download here](https://developer.android.com/studio))
- [ ] Android SDK (comes with Android Studio)
- [ ] Java JDK 8 or higher
- [ ] Android device OR Android Emulator

---

## 🎯 Step-by-Step Setup

### **Step 1: Install Android Studio** (if not already installed)
1. Download Android Studio from [developer.android.com/studio](https://developer.android.com/studio)
2. Run the installer and follow the setup wizard
3. Install Android SDK when prompted (SDK 24+ required)

### **Step 2: Open the Project**
1. Launch Android Studio
2. Click **"Open"** or **"Open an Existing Project"**
3. Navigate to the `flappy` folder and select it
4. Click **"OK"**
5. ⏳ Wait for Gradle sync to complete (first time may take 5-10 minutes)

### **Step 3: Test Run (Without Assets)** ✨
You can test the game immediately with placeholder graphics!

1. **Connect Device OR Start Emulator:**
   - **Physical Device**: Enable USB debugging and connect via USB
   - **Emulator**: Click "Device Manager" → "Create Device" → Select a device → Click "Run"
   
2. **Run the Game:**
   - Click the green **▶ Run** button (or press `Shift+F10`)
   - Select your device/emulator
   - Wait for the app to install and launch
   - 🎮 Play with placeholder graphics!

### **Step 4: Add Your Assets** (For Full Experience)
Replace placeholder files with your actual assets:

#### 📸 **Character Images**
1. Navigate to: `app/src/main/res/drawable/`
2. **Delete** these placeholder files:
   - `character1.xml`
   - `character2.xml`
   - `character3.xml`
3. **Add** your image files:
   - `character1.png` (200x200px recommended)
   - `character2.png`
   - `character3.png`

#### 🎯 **Obstacle Images**
1. In the same `drawable/` folder
2. **Delete** these placeholder files:
   - `obstacle1.xml`
   - `obstacle2.xml`
   - `obstacle3.xml`
3. **Add** your obstacle images:
   - `obstacle1.png` (150x1000px recommended)
   - `obstacle2.png`
   - `obstacle3.png`

#### 🎵 **Sound Files**
1. Navigate to: `app/src/main/res/raw/`
2. **Create the folder** if it doesn't exist
3. **Add** all sound files:
   ```
   bg_music1.mp3
   bg_music2.mp3
   bg_music3.mp3
   jump_sound1.mp3
   jump_sound2.mp3
   jump_sound3.mp3
   game_over_sound1.mp3
   game_over_sound2.mp3
   game_over_sound3.mp3
   high_score_sound.mp3
   ```

4. **Rebuild** the project: `Build → Rebuild Project`

### **Step 5: Run Again with Assets**
1. Click **▶ Run** again
2. Enjoy the full game experience with your assets! 🎉

---

## 🎮 How to Play

### **In the Menu:**
1. 👆 Use **◀ Previous** and **Next ▶** buttons to select a character
2. 🎯 Click **"Start Game"** to begin

### **During Gameplay:**
1. 👆 **Tap the screen** to make the bird jump/flap
2. ⚠️ **Avoid obstacles** (pipes) - don't hit them!
3. 🚫 **Don't hit the ground** or ceiling
4. 📈 **Score points** by passing through pipes
5. 🎯 **Beat your high score!**

### **Game Over:**
1. 👆 **Tap the screen** to restart
2. 📊 View your score and high score
3. 🏠 Return to menu (currently requires app restart)

---

## 🎯 Quick Reference

### **File Locations:**
```
📁 Project Root
  └── 📁 app
      └── 📁 src
          └── 📁 main
              ├── 📁 res
              │   ├── 📁 drawable/     ← Character & Obstacle images
              │   ├── 📁 raw/          ← Sound files
              │   └── 📁 layout/       ← UI layouts
              └── 📁 java
                  └── 📁 com/flappy/phoolmati/  ← Game code
```

### **Asset File Names (Must Match Exactly):**
- Characters: `character1.png`, `character2.png`, `character3.png`
- Obstacles: `obstacle1.png`, `obstacle2.png`, `obstacle3.png`
- Sounds: `bg_music1.mp3`, `jump_sound1.mp3`, etc.

---

## ✨ Key Features

| Feature | Description |
|---------|-------------|
| 🎭 **3 Characters** | Each with unique appearance, sounds, and obstacles |
| 🎵 **Custom Audio** | Background music, jump sounds, and game over sounds per character |
| 🏆 **High Scores** | Track your best performance across sessions |
| 🎮 **Smooth Gameplay** | 60 FPS with responsive controls |
| 📱 **Full Screen** | Immersive gaming experience |

---

## 🔧 Common Tasks

### **Adding a New Character:**
1. Add `character4.png` to `res/drawable/`
2. Add `obstacle4.png` to `res/drawable/`
3. Add sound files to `res/raw/`:
   - `bg_music4.mp3`
   - `jump_sound4.mp3`
   - `game_over_sound4.mp3`
4. Open `MainActivity.java`
5. Add to `initializeCharacters()` method:
   ```java
   characters.add(new Character(
       "Character 4 Name",
       R.drawable.character4,
       R.raw.bg_music4,
       R.raw.jump_sound4,
       R.drawable.obstacle4,
       R.raw.game_over_sound4
   ));
   ```
6. Update `GameActivity.java` `getCharacterByIndex()` method
7. Rebuild and run!

---

## 🛠️ Troubleshooting

### ❌ **Gradle Sync Failed**
- ✅ Check internet connection (Gradle downloads dependencies)
- ✅ Verify Android SDK is installed: `Tools → SDK Manager`
- ✅ Try: `File → Invalidate Caches / Restart`

### ❌ **Game Won't Build**
- ✅ Clean project: `Build → Clean Project`
- ✅ Rebuild: `Build → Rebuild Project`
- ✅ Check for red error messages in the code

### ❌ **Assets Not Showing**
- ✅ Verify files are in correct folders (`drawable/` or `raw/`)
- ✅ Check file names match **exactly** (case-sensitive!)
- ✅ Delete placeholder `.xml` files if adding `.png` files
- ✅ Rebuild project after adding assets

### ❌ **Sounds Not Playing**
- ✅ Check device volume (not muted)
- ✅ Verify sound files are in `res/raw/` directory
- ✅ Check file format (MP3 or OGG recommended)
- ✅ Game continues without sounds (no crash) if files missing

### ❌ **App Crashes on Launch**
- ✅ Check AndroidManifest.xml for errors
- ✅ Verify minimum SDK is 24 or higher
- ✅ Check logcat for error messages: `View → Tool Windows → Logcat`

### ❌ **Can't Find Device/Emulator**
- ✅ Enable USB debugging on physical device
- ✅ For emulator: Create AVD in Device Manager
- ✅ Check device is recognized: `adb devices` in terminal

---

## 📚 Additional Resources

| Document | Purpose |
|----------|---------|
| 📖 `README.md` | General project information |
| 🎨 `ASSETS_README.md` | Detailed asset specifications |
| 🔨 `BUILD_INSTRUCTIONS.md` | Comprehensive build guide |
| 📊 `PROJECT_SUMMARY.md` | Complete project overview |

---

## ✅ Checklist: Ready to Play?

- [ ] Android Studio installed
- [ ] Project opened and Gradle synced
- [ ] Device/Emulator connected
- [ ] Game runs with placeholder graphics
- [ ] (Optional) Character images added
- [ ] (Optional) Obstacle images added
- [ ] (Optional) Sound files added
- [ ] Game runs successfully!

---

## 🎉 You're All Set!

Your Flappy Phoolmati game is ready to play! Start with placeholder graphics and add your custom assets when ready.

**Happy Gaming!** 🎮🚀

---

*Need help? Check the other documentation files or review the code comments for implementation details.*

