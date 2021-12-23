package studio.auradevelopment.listener;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import studio.auradevelopment.Hub;

public class AuraEnderButt implements Listener {

    private final Hub hub;

    public AuraEnderButt(Hub hub){
        this.hub = hub;
    }

    public Hub getHub() {
        return hub;
    }

    @EventHandler
    public void on(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getItemInHand();
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (itemInHand.getType() == Material.valueOf(getHub().getConfiguration().getString("ITEMS.ENDERBUTT.ITEM"))) {
                player.setVelocity(player.getLocation().getDirection().normalize().multiply(4));
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 10F);
                player.updateInventory();
                event.setCancelled(true);
            }
        }
    }
}
