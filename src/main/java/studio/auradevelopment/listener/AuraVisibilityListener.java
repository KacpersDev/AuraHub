package studio.auradevelopment.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import studio.auradevelopment.Hub;
import studio.auradevelopment.manager.VisibleManager;
import studio.auradevelopment.util.CC;

public class AuraVisibilityListener implements Listener {

    private final Hub hub;
    private final VisibleManager visibleManager = new VisibleManager();

    public AuraVisibilityListener(Hub hub) {
        this.hub = hub;
    }

    public Hub getHub() {
        return hub;
    }

    @EventHandler
    public void onUse(PlayerInteractEvent event){

        Player player = event.getPlayer();
        Action action = event.getAction();

        if (!player.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(CC.toColor(getHub().getConfiguration().getString("ITEMS.VISIBLE.NAME")))) return;

        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {

            if (!(visibleManager.visible.contains(player))) {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    visibleManager.visible.add(player);
                    player.hidePlayer(online);
                    player.sendMessage(CC.toColor(getHub().getLanguage().getString("VISIBILITY.ENABLED")));
                }
            } else {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    visibleManager.visible.remove(player);
                    player.showPlayer(online);
                    player.sendMessage(CC.toColor(getHub().getLanguage().getString("VISIBILITY.DISABLED")));
                }
            }
        }
    }
}
