package studio.auradevelopment.util.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import studio.auradevelopment.Hub;
import studio.auradevelopment.util.CC;

import java.util.ArrayList;


public class InventoryManager {

    public Inventory inventory;

    public InventoryManager(Player player, String title, int size){


        inventory = Bukkit.createInventory(player, size, title);
        for (final String i : Hub.getInstance().getInventories().getConfigurationSection("INVENTORY.SELECTOR.ITEMS").getKeys(false)) {
            ItemStack item = new ItemStack(Material.valueOf(Hub.getInstance().getInventories().getString("INVENTORY.SELECTOR.ITEMS." + i + ".ITEM")));
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(CC.toColor(Hub.getInstance().getInventories().getString("INVENTORY.SELECTOR.ITEMS." + i + ".NAME")));
            ArrayList<String> lore = new ArrayList<>();
            for (final String m : Hub.getInstance().getConfiguration().getStringList("INVENTORY.SELECTOR.ITEMS." + i + ".LORE")) {
                lore.add(CC.toColor(m));
            }

            meta.setLore(lore);
            item.setItemMeta(meta);
            inventory.setItem(Hub.getInstance().getInventories().getInt("INVENTORY.SELECTOR.ITEMS." + i + ".SLOT"), item);
        }
        player.openInventory(inventory);
    }
}
