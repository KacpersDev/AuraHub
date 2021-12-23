package studio.auradevelopment.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import studio.auradevelopment.Hub;
import studio.auradevelopment.manager.BuildManager;

public class AuraAntiGriefListener implements Listener {

    private final Hub hub;
    private final BuildManager manager = new BuildManager();

    public AuraAntiGriefListener(Hub hub) {

        this.hub = hub;
    }

    public Hub getHub() {
        return hub;
    }

    @EventHandler
    public void onBlock(BlockPlaceEvent event){

        Player player = event.getPlayer();

        if (!(manager.buildMod.contains(player))) { event.setCancelled(true); }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();

        if (!(manager.buildMod.contains(player))) { event.setCancelled(true); }


    }
}
