# ExplosiveDamage Plugin for Paper

A Minecraft Paper plugin that caps all explosive damage at exactly 4 hearts (8.0 damage points) regardless of the explosion source.

## Features

- **Universal Explosive Damage Cap**: Limits all explosive damage to 4 hearts maximum
- **Comprehensive Coverage**: Handles all types of explosions including:
  - Creeper explosions
  - TNT explosions (including TNT minecarts)
  - Bed explosions in the Nether and End
  - Wither skull explosions
  - Fireball explosions (Ghast, Blaze, Dragon)
  - End Crystal explosions
  - Block explosions
  - Entity explosions
- **Paper Optimized**: Built specifically for Paper servers with high-performance event handling
- **Detailed Logging**: Tracks when damage is capped for debugging and monitoring

## Requirements

- Paper server version 1.20.1 or higher
- Java 17 or higher

## Installation

1. Download the latest `explosive-damage-1.0.0.jar` from the releases
2. Place the JAR file in your Paper server's `plugins` directory
3. Restart your server
4. The plugin will automatically activate and begin capping explosive damage

## Building from Source

### Prerequisites
- Maven 3.6+
- Java 17+

### Build Steps
```bash
git clone <repository-url>
cd explosvies
./build.sh
```

Or manually with Maven:
```bash
mvn clean package
```

The compiled plugin will be available at `target/explosive-damage-1.0.0.jar`.

## How It Works

The plugin uses Bukkit's event system to intercept damage events before they are applied to players and entities. When explosive damage exceeds 8.0 (4 hearts), it is automatically reduced to exactly 8.0.

### Supported Explosion Types

| Explosion Source | Coverage |
|-----------------|----------|
| Creepers | ✅ |
| TNT Blocks | ✅ |
| TNT Minecarts | ✅ |
| Bed Explosions (Nether/End) | ✅ |
| Wither Skulls | ✅ |
| Ghast Fireballs | ✅ |
| Blaze Fireballs | ✅ |
| Dragon Fireballs | ✅ |
| End Crystals | ✅ |
| Wither Explosions | ✅ |

## Commands

The plugin includes a status command to verify it's working properly:

| Command | Aliases | Permission | Description |
|---------|---------|------------|-------------|
| `/explosivedamage` | `/expdmg`, `/explosions` | `explosivedamage.check` | Shows plugin status and confirms it's operational |

### Usage Examples:
```
/explosivedamage
/expdmg
/explosions
```

**Sample Output:**
```
=== ExplosiveDamage Plugin Status ===
✓ Plugin is loaded and operational
✓ Explosive damage capped at 4 hearts (8.0 damage)
✓ Covers: Creepers, TNT, Beds (Nether/End), Fireballs, Wither
Version: 1.0.0 | API: 1.21 | Paper Compatible
Aliases: /expdmg, /explosions
```

## Configuration

This plugin works out of the box with no configuration required. The damage cap is hardcoded to 8.0 (4 hearts) for maximum reliability and performance.

## Permissions

- `explosivedamage.admin` - Reserved for future administrative features (default: op)
- `explosivedamage.check` - Allows using the status command (default: true)

## Compatibility

- **Server Software**: Paper 1.20.1+
- **Minecraft Version**: 1.20.1+
- **Plugin Conflicts**: Should be compatible with most other plugins

## Support

If you encounter any issues or have questions about the plugin, please check that:
1. You're running Paper (not Spigot or Bukkit)
2. Your server version is 1.21.1 or higher
3. The plugin loaded successfully (check server console for confirmation messages)
4. Use `/explosivedamage` in-game to verify the plugin is operational

## Technical Details

- **Event Priority**: HIGH (ensures damage cap is applied before other plugins)
- **Performance**: Minimal overhead with efficient event handling
- **Logging Level**: INFO for damage cap notifications, FINE for explosion detection

The plugin monitors `EntityDamageEvent` and `EntityDamageByEntityEvent` to catch all possible explosion damage scenarios.