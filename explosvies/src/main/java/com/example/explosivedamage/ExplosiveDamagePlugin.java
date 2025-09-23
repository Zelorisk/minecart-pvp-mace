package com.example.explosivedamage;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ExplosiveDamagePlugin extends JavaPlugin implements Listener {

    private static final double TARGET_DAMAGE = 8.0;
    private static final double EMPIRICAL_MULTIPLIER = 4.0;

    @Override
    public void onEnable() {
        getLogger().info(
            "ExplosiveDamage v2.4.0 - Starting plugin initialization..."
        );
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info(
            "ExplosiveDamage v2.4.0 - Event listeners registered successfully"
        );
        getLogger().info(
            "ExplosiveDamage plugin enabled - explosives set to 4 hearts, maces capped at 4 hearts max"
        );
    }

    @Override
    public void onDisable() {
        getLogger().info("ExplosiveDamage v2.4.0 plugin disabled");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamage(EntityDamageEvent event) {
        EntityDamageEvent.DamageCause cause = event.getCause();

        if (isExplosiveDamage(cause)) {
            double calculatedDamage = TARGET_DAMAGE * EMPIRICAL_MULTIPLIER;
            event.setDamage(calculatedDamage);

            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                String damageType = isBedExplosion(player)
                    ? "bed explosion"
                    : cause.toString().toLowerCase();
                getLogger().info(
                    "[EXPLOSIVE] Set " +
                    damageType +
                    " to " +
                    String.format("%.1f", calculatedDamage) +
                    " (empirical 4x multiplier for Protection III) for player " +
                    player.getName() +
                    " (original: " +
                    event.getDamage() +
                    ")"
                );
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageForMace(EntityDamageEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            if (event instanceof EntityDamageByEntityEvent) {
                EntityDamageByEntityEvent entityEvent =
                    (EntityDamageByEntityEvent) event;
                if (isMaceWeapon(entityEvent)) {
                    double originalDamage = event.getDamage();
                    double maxDamage = TARGET_DAMAGE * EMPIRICAL_MULTIPLIER;

                    if (originalDamage > maxDamage) {
                        event.setDamage(maxDamage);

                        if (event.getEntity() instanceof Player) {
                            Player player = (Player) event.getEntity();
                            getLogger().info(
                                "[MACE] Capped mace damage to " +
                                String.format("%.1f", maxDamage) +
                                " (4 hearts max for Protection III) for player " +
                                player.getName() +
                                " (original: " +
                                originalDamage +
                                ")"
                            );
                        }
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        EntityType damagerType = event.getDamager().getType();

        if (isExplosiveEntity(damagerType)) {
            double calculatedDamage = TARGET_DAMAGE * EMPIRICAL_MULTIPLIER;
            event.setDamage(calculatedDamage);

            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                getLogger().info(
                    "[EXPLOSIVE ENTITY] Set damage to " +
                    String.format("%.1f", calculatedDamage) +
                    " (empirical 4x multiplier for Protection III) for player " +
                    player.getName() +
                    " (source: " +
                    damagerType +
                    ", original: " +
                    event.getDamage() +
                    ")"
                );
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityExplode(EntityExplodeEvent event) {
        EntityType entityType = event.getEntityType();
        if (isExplosiveEntity(entityType)) {
            getLogger().fine(
                "Explosion detected from " +
                entityType +
                " at " +
                event.getLocation().getBlockX() +
                ", " +
                event.getLocation().getBlockY() +
                ", " +
                event.getLocation().getBlockZ()
            );
        }
    }

    private boolean isExplosiveDamage(EntityDamageEvent.DamageCause cause) {
        return (
            cause == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION ||
            cause == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION
        );
    }

    private boolean isMaceWeapon(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player attacker = (Player) event.getDamager();
            if (
                attacker.getInventory().getItemInMainHand().getType() ==
                Material.MACE
            ) {
                return true;
            }
        }
        return false;
    }

    private boolean isExplosiveEntity(EntityType entityType) {
        return (
            entityType == EntityType.CREEPER ||
            entityType == EntityType.WITHER_SKULL ||
            entityType == EntityType.FIREBALL ||
            entityType == EntityType.DRAGON_FIREBALL ||
            entityType == EntityType.SMALL_FIREBALL ||
            entityType == EntityType.WITHER
        );
    }

    private boolean isBedExplosion(Player player) {
        if (
            player.getWorld().getEnvironment() !=
            org.bukkit.World.Environment.NORMAL
        ) {
            Block block = player.getLocation().getBlock();
            for (int x = -2; x <= 2; x++) {
                for (int y = -2; y <= 2; y++) {
                    for (int z = -2; z <= 2; z++) {
                        Block nearby = block.getRelative(x, y, z);
                        if (isBedMaterial(nearby.getType())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isBedMaterial(Material material) {
        return (
            material == Material.WHITE_BED ||
            material == Material.ORANGE_BED ||
            material == Material.MAGENTA_BED ||
            material == Material.LIGHT_BLUE_BED ||
            material == Material.YELLOW_BED ||
            material == Material.LIME_BED ||
            material == Material.PINK_BED ||
            material == Material.GRAY_BED ||
            material == Material.LIGHT_GRAY_BED ||
            material == Material.CYAN_BED ||
            material == Material.PURPLE_BED ||
            material == Material.BLUE_BED ||
            material == Material.BROWN_BED ||
            material == Material.GREEN_BED ||
            material == Material.RED_BED ||
            material == Material.BLACK_BED
        );
    }
}
