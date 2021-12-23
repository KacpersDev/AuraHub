package studio.auradevelopment.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import studio.auradevelopment.Hub;

public class AuraVoidListener implements Listener {

    private final Hub hub;

    public AuraVoidListener(Hub hub){
        this.hub = hub;
    }

    public Hub getHub() {
        return hub;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        if (player.getLocation().getBlockY() < -30) {
            player.teleport(new Location(Bukkit.getWorld("World"),
                    getHub().getConfiguration().getDouble("SPAWN.X"),
                    getHub().getConfiguration().getDouble("SPAWN.Y"),
                    getHub().getConfiguration().getDouble("SPAWN.Z")));
        }
    }
}
