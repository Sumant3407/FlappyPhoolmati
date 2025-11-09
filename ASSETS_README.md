# Assets Required for Flappy Phoolmati

## Important: Replace Placeholder Files

The game currently uses XML drawable placeholders. You **MUST** replace these with actual image files (.png, .jpg, or .jpeg) for the game to work properly.

## Required Assets

### Character Images
Location: `app/src/main/res/drawable/`
- `character1.png` (or .jpg/.jpeg) - Replace `character1.xml`
- `character2.png` (or .jpg/.jpeg) - Replace `character2.xml`
- `character3.png` (or .jpg/.jpeg) - Replace `character3.xml`

**Recommended specifications:**
- Size: 200x200 pixels
- Format: PNG with transparency (preferred)
- Style: Cute bird/character facing right

### Obstacle Images
Location: `app/src/main/res/drawable/`
- `obstacle1.png` (or .jpg/.jpeg) - Replace `obstacle1.xml`
- `obstacle2.png` (or .jpg/.jpeg) - Replace `obstacle2.xml`
- `obstacle3.png` (or .jpg/.jpeg) - Replace `obstacle3.xml`

**Recommended specifications:**
- Size: 150x1000 pixels (tall vertical obstacle)
- Format: PNG
- Style: Pipe-like obstacles matching character theme

### Sound Files
Location: `app/src/main/res/raw/`

#### Background Music (MP3 or OGG)
- `bg_music1.mp3` - Background music for character 1
- `bg_music2.mp3` - Background music for character 2
- `bg_music3.mp3` - Background music for character 3

**Recommended specifications:**
- Format: MP3 or OGG
- Duration: 30-60 seconds (will loop)
- Volume: Medium (will be adjusted in game)

#### Jump Sound Effects (MP3 or OGG)
- `jump_sound1.mp3` - Jump sound for character 1
- `jump_sound2.mp3` - Jump sound for character 2
- `jump_sound3.mp3` - Jump sound for character 3

**Recommended specifications:**
- Format: MP3 or OGG
- Duration: 0.3-0.5 seconds
- Style: Whoosh, flap, or wing sound

#### Game Over Sound Effects (MP3 or OGG)
- `game_over_sound1.mp3` - Game over sound for character 1
- `game_over_sound2.mp3` - Game over sound for character 2
- `game_over_sound3.mp3` - Game over sound for character 3

**Recommended specifications:**
- Format: MP3 or OGG
- Duration: 1-2 seconds
- Style: Crash, impact, or failure sound

#### High Score Sound Effect (MP3 or OGG)
- `high_score_sound.mp3` - Universal high score sound

**Recommended specifications:**
- Format: MP3 or OGG
- Duration: 1-2 seconds
- Style: Achievement, success, or celebration sound

## How to Add Assets

1. **For Images:**
   - Delete the placeholder XML files (character1.xml, obstacle1.xml, etc.)
   - Add your PNG/JPG/JPEG files with the same names
   - Ensure they are in `app/src/main/res/drawable/` directory

2. **For Sounds:**
   - Create the `raw` directory if it doesn't exist: `app/src/main/res/raw/`
   - Add all sound files with exact names as listed above
   - Supported formats: MP3, OGG, WAV

## Testing Without Assets

The game will run with placeholder shapes, but:
- Characters will appear as colored circles
- Obstacles will appear as colored rectangles
- Sound effects will be silent (no errors, just no sound)

## Notes

- File names are case-sensitive
- Resource names cannot contain dashes, use underscores
- All assets must be in the correct directories
- Rebuild the project after adding new assets

