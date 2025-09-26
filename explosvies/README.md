# explosiveDamage plugin for paper

A Minecraft Paper plugin that caps all explosive damage at exactly 4 hearts (8.0 damage points) regardless of the explosion source.

## requirements

- Paper server version 1.20.1 or higher
- Java 21 
## Installation

1. download the latest `explosive-damage-1.0.0.jar` from the releases
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
## Permissions

- `explosivedamage.admin` - Reserved for future administrative features (default: op)
- `explosivedamage.check` - Allows using the status command (default: true)

- **Event Priority**: HIGH (ensures damage cap is applied before other plugins)
- **Performance**: Minimal overhead with efficient event handling
- **Logging Level**: INFO for damage cap notifications, FINE for explosion detection

The plugin monitors `EntityDamageEvent` and `EntityDamageByEntityEvent` to catch all possible explosion damage scenarios.
