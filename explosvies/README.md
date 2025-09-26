# explosivedamage plugin for paper

a minecraft paper plugin that caps all explosive damage at exactly 4 hearts (8.0 damage points) regardless of the explosion source.

## requirements

- paper server version 1.20.1 or higher
- java 21 
## Installation

1. download the latest `explosive-damage-1.0.0.jar` from the releases
2. place the JAR file in your Paper server's `plugins` directory
3. restart your server
4. the plugin will automatically activate and begin capping explosive damage
   
### prerequisites
- maven 3.6+
- java 17+

### build steps
```bash
git clone <repository-url>
cd explosvies
./build.sh
```

or manually with maven:
```bash
mvn clean package
```

| command | aliases | permission | description |
|---------|---------|------------|-------------|
| `/explosivedamage` | `/expdmg`, `/explosions` | `explosivedamage.check` | shows plugin status and confirms it's operational |

### usage examples:
```
/explosivedamage
/expdmg
/explosions
```

**sample output:**
```
=== ExplosiveDamage Plugin Status ===
✓ Plugin is loaded and operational
✓ Explosive damage capped at 4 hearts (8.0 damage)
✓ Covers: Creepers, TNT, Beds (Nether/End), Fireballs, Wither
Version: 1.0.0 | API: 1.21 | Paper Compatible
Aliases: /expdmg, /explosions
```
## permissions

- `explosivedamage.admin` - reserved for future administrative features (default: op)
- `explosivedamage.check` - allows using the status command (default: true)

- **event priority**: HIGH (ensures damage cap is applied before other plugins)
- **performance**: minimal overhead with efficient event handling
- **logging level**: INFO for damage cap notifications, FINE for explosion detection

the plugin monitors `EntityDamageEvent` and `EntityDamageByEntityEvent` to catch all possible explosion damage scenarios.
