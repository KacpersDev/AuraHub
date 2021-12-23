package studio.auradevelopment.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import studio.auradevelopment.Hub;
import studio.auradevelopment.util.CC;
import studio.auradevelopment.util.inventory.InventoryManager;

public class AuraSelectorListener implements Listener {

    private final Hub hub;

    public AuraSelectorListener(Hub hub){
        this.hub = hub;
    }

    public Hub getHub() {
        return hub;
    }

    @EventHandler
    public void onSelectorListener(PlayerInteractEvent event){

        Player player = event.getPlayer();
        Action action = event.getAction();

        if (action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
            new InventoryManager(player, CC.toColor(getHub().getInventories().getString("INVENTORY.SELECTOR.TITLE")), getHub().getInventories().getInt("INVENTORY.SELECTOR.SIZE"));
        }
    }
}
