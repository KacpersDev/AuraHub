package studio.auradevelopment.util.item;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import studio.auradevelopment.Hub;
import studio.auradevelopment.util.CC;

import java.util.ArrayList;

public class JoinItem {

    public JoinItem(Player player, ItemStack item, String name, ArrayList<String> lore, int amount, int slot, String loreConfig, boolean enabled) {

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.toColor(name));
        for (final String loreM : Hub.getInstance().getConfiguration().getStringList(loreConfig)) {
            lore.add(CC.toColor(loreM));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);
        item.setAmount(amount);
        if (enabled) {
            player.getInventory().setItem(slot, item);
        }
    }
}
