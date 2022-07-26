package dev.luckynetwork.cyclize.luckyspawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

public final class LuckySpawn extends JavaPlugin implements Listener {
    private Location spawn;

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        if (!this.getConfig().getBoolean("spawn.enabled")) {
            this.setEnabled(false);
            return;
        }

        World world = Bukkit.getWorld(this.getConfig().getString("spawn.world"));
        double x = this.getConfig().getDouble("spawn.x");
        double y = this.getConfig().getDouble("spawn.y");
        double z = this.getConfig().getDouble("spawn.z");
        float yaw = (float) this.getConfig().getDouble("spawn.yaw");
        float pitch = (float) this.getConfig().getDouble("spawn.pitch");
        spawn = new Location(world, x, y, z, yaw, pitch);

        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerSpawnLocation(PlayerSpawnLocationEvent event) {
        event.setSpawnLocation(spawn);
    }
}
