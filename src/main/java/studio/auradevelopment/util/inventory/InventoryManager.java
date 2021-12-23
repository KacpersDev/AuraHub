package studio.auradevelopment.util.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import studio.auradevelopment.Hub;
import studio.auradevelopment.util.CC;

public class InventoryManager {

    public Inventory inventory;

    public InventoryManager(Player player, String title, int size){


        // Hub.getInstance().getInventories().getInt("INVENTORY.SELECTOR.SIZE")
        // CC.toColor(Hub.getInstance().getInventories().getString("INVENTORY.SELECTOR.TITLE"))
        inventory = Bukkit.createInventory(player, size, title);
    }
}
