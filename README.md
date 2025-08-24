# Minotaur Remake 🏛️

A modern remake of the classic first-person dungeon crawler where you explore a multi-level maze, battle monsters, collect treasures, and manage resources. Your ultimate goal is to find and defeat the Minotaur, who guards the legendary Treasure of Tarmin.

## 🎮 Game Overview

**Minotaur** is a first-person dungeon crawler that captures the tension and methodical exploration of classic RPGs. Navigate through procedurally assembled mazes, engage in strategic turn-based combat, and manage your resources carefully as you descend deeper into increasingly dangerous levels.

### Target Audience
- Fans of classic dungeon crawlers
- Retro gaming enthusiasts  
- Players who enjoy challenging, exploration-based games

### Core Gameplay Loop
1. **Exploring** - Navigate mazes, discover rooms, find hidden doors
2. **Fighting** - Turn-based combat with various monsters
3. **Looting** - Collect weapons, armor, magical items, and treasures
4. **Managing** - Organize inventory, monitor health and spiritual power
5. **Descending** - Progress to deeper, more dangerous dungeon levels

## 🛠️ Technical Specifications

- **Language:** Java 17 LTS
- **Framework:** libGDX (cross-platform game development)
- **Package Structure:** `com.bpm.minotaur`
- **Data Storage:** File-based (JSON for static data, serialization for saves)
- **Maze Generation:** Quadrant-based Assembly algorithm

### Platform Support
- Windows
- macOS  
- Linux

## 🏗️ Architecture

The game follows a clean Model-View-Controller architecture with the following core components:

### Core Classes
- `Game` - Main game controller and session management
- `Player` - Player state, attributes, and actions
- `Maze` - Level structure and tile management
- `Monster` - Enemy entities and combat behavior
- `Item` - Equipment, weapons, and consumables
- `Inventory` - Item management system
- `MazeGenerator` - Procedural level creation

### Key Systems
- **Settings Manager** - Handles user preferences and configuration
- **Debug Manager** - Development tools and debugging overlay
- **Combat System** - Turn-based battle mechanics
- **Inventory System** - Item management and equipment

## 🚀 Development Roadmap

### ✅ Phase 1: Core Engine Foundation
- [x] Main game class implementation
- [x] Screen management system
- [x] Main menu and game screens
- [x] Basic input handling
- [ ] Settings management
- [ ] Debug overlay system

### ✅ Phase 2: Game World & Player  
- [x] Player, Maze, and Tile data structures
- [x] Quadrant-based maze generation
- [x] First-person maze renderer
- [x] Player movement system
- [ ] Debug maze view
- [x] Unit tests for core classes

### 🔄 Phase 3: Items & Inventory
- [ ] Item data structures and subclasses
- [ ] Inventory management system
- [ ] Item pickup and drop mechanics
- [ ] Item rendering (world and UI)
- [ ] Inventory unit tests

### 📋 Phase 4: Monsters & Combat
- [ ] Monster data structures
- [ ] Turn-based combat logic
- [ ] Monster world rendering
- [ ] Attack sequences
- [ ] Combat debug information
- [ ] Combat unit tests

### 🎨 Phase 5: UI & HUD
- [ ] Main game HUD implementation
- [ ] Inventory user interface
- [ ] Castle and Maze map screens
- [ ] Settings screen

### ✨ Phase 6: Polish & Finalization
- [ ] Audio implementation
- [ ] Save/load functionality
- [ ] Skill level balancing
- [ ] Bug fixes and optimization

### 🤖 Phase 7: Process Automation (Research)
- [ ] AI agent framework evaluation
- [ ] Automated workflow development
- [ ] Proof-of-concept implementation

## 🎯 Game Mechanics

### Player Attributes
- **War Strength** - Physical combat power
- **Spiritual Strength** - Magical combat power  
- **Armor/Ring Defenses** - Damage reduction
- **Food & Arrows** - Consumable resources

### Player Actions
- **Movement** - Forward, turn left/right, glance, retreat
- **Combat** - Attack with equipped weapons
- **Inventory** - Swap items, rotate pack, pickup/drop
- **Exploration** - Open doors/containers, use items, rest
- **Progression** - Descend ladders to deeper levels

### Combat System
- Turn-based combat mechanics
- War and Spiritual weapon types
- Defensive equipment (shields, armor, rings)
- Resource management (consumable items)

## 🎨 Visual Style

### Aesthetic Philosophy
**Keywords:** Claustrophobic, Tense, Mysterious, Methodical, Isolating, Unforgiving, Retro, Minimalist

The game emphasizes atmospheric tension over visual spectacle. Simple graphics force players to use their imagination, making unseen threats more menacing.

### Color Palette
- **UI Background:** Pure black (#000000)
- **Maze Floor/Ceiling:** Muted olive green (#5B602F)
- **Walls:** Dark teal-green (#005945) and bright green (#00A95A)
- **Minotaur:** Shocking magenta (#D73B93)
- **UI Elements:** White text (#FFFFFF), War score (#45BBDC), Spiritual score (#F1E03C)

### Sound Design
- **No background music** during exploration
- **Footstep audio** creates baseline tension
- **Distinct monster cues** for each enemy type
- **8-bit style** sound effects for actions

## 🗺️ World Design

### Castle Structure
- **256 total levels** of increasing difficulty
- **12x12 grid** maze layout per level
- **Quadrant-based generation** using pre-designed 5x5 templates

### Level Features
- **Rooms and Corridors** - Basic maze structure
- **Doors** - Standard and hidden entrances  
- **Gates** - Inter-level transport with stat effects
- **Ladders** - Descent to deeper levels
- **Eyeball Murals** - Maze type indicators

## 📊 Items & Equipment

### Weapon Categories
- **War Weapons** - Physical combat (bows, axes, spears, etc.)
- **Spiritual Weapons** - Magical combat (scrolls, fireballs, etc.)
- **Defensive Gear** - Armor, shields, rings

### Item Quality System
Items are color-coded by material and power:
- **Tan** - Wood/Leather (Regular)
- **Orange** - Rusty Iron (Greater)  
- **Blue** - Steel (Fair)
- **Gray** - Silver (Medium)
- **Yellow** - Gold (High)
- **White** - Platinum (Super)

### Special Items
- **Containers** - Money belts, bags, chests (may contain bombs)
- **Treasures** - Coins, necklaces, crowns (varying values)
- **Useful Items** - Keys, potions, magical books

## 👹 Enemies

### Monster Categories
- **Bad Monsters** - Use spiritual weapons only
- **Nasty Monsters** - Use war weapons only  
- **Horrible Monsters** - Use either weapon type

### Enemy Roster
**Bad:** Giant Ants, Dwarfs, Giant Scorpions, Giant Snakes  
**Nasty:** Skeletons, Cloaked Skeletons, Giants, Wraiths  
**Horrible:** Alligators, Ghouls, Dragons  
**Boss:** The Minotaur (guards the Tarmin treasure)

## ⚙️ Skill Levels

Four difficulty settings that affect:
- Number of maze levels
- Starting player vulnerability
- Initial War/Spiritual Strength
- Starting food and arrow counts

## 🧪 Development Standards

### Code Quality
- **Unit Test Coverage:** >80% for core game logic
- **Cyclomatic Complexity:** <10 per method
- **Code Duplication:** <5%
- **Documentation:** Complete Javadoc for public APIs

### Architectural Principles
- **SOLID** object-oriented design principles
- **Separation of Concerns** - Model/View/Controller separation
- **Data-Driven Design** - External JSON configuration
- **Singleton Pattern** - For global managers

## 🚀 Getting Started

### Prerequisites
- Java 17 LTS
- Gradle build system
- libGDX framework

### Building and Running
```bash
# Clone the repository
git clone https://github.com/your-username/minotaur-remake.git
cd minotaur-remake

# Build the project
./gradlew build

# Run the game
./gradlew run
```

### Development Setup
```bash
# Run tests
./gradlew test

# Enable debug mode
./gradlew run -Pdebug=true
```

## 🤝 Contributing

We welcome contributions! Please see our [Contributing Guidelines](CONTRIBUTING.md) for details on:
- Code style and formatting
- Pull request process
- Issue reporting
- Development workflow

## 📜 License

This project is licensed under the [MIT License](LICENSE) - see the LICENSE file for details.

## 🙏 Acknowledgments

- Inspired by the classic Minotaur dungeon crawler
- Built with the excellent [libGDX](https://libgdx.com/) framework
- Special thanks to the retro gaming community

## 📞 Contact

- **Project Maintainer:** [Your Name](mailto:your.email@example.com)
- **Issues:** Use the [GitHub Issues](https://github.com/your-username/minotaur-remake/issues) page
- **Discussions:** Join our [community discussions](https://github.com/your-username/minotaur-remake/discussions)

---

**Ready to face the Minotaur?** Dive into the maze and claim the Treasure of Tarmin! 🏆
