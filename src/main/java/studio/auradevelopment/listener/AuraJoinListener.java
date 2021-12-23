package studio.auradevelopment.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import studio.auradevelopment.Hub;
import studio.auradevelopment.util.CC;
import studio.auradevelopment.util.item.JoinEffects;
import studio.auradevelopment.util.item.JoinItem;

import java.util.ArrayList;

public class AuraJoinListener implements Listener {

    private final Hub hub;

    public AuraJoinListener(Hub hub){
        this.hub = hub;
    }

    public Hub getHub() {
        return hub;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        joinMessage(player);
        new JoinEffects(player);
        player.setFoodLevel(20);
        player.setHealth(20);
        player.teleport(new Location(Bukkit.getWorld("World"),
                getHub().getConfiguration().getDouble("SPAWN.X"),
                getHub().getConfiguration().getDouble("SPAWN.Y"),
                getHub().getConfiguration().getDouble("SPAWN.Z")));
        ArrayList<String> selectorLore = new ArrayList<>();
        ArrayList<String> enderButtLore = new ArrayList<>();
        ArrayList<String> visibleLore = new ArrayList<>();
        ArrayList<String> informationLore = new ArrayList<>();
        ArrayList<String> lobbyLore = new ArrayList<>();
        ArrayList<String> cosmeticLore = new ArrayList<>();
        new JoinItem(player, new ItemStack(Material.valueOf(getHub().getConfiguration().getString("ITEMS.SELECTOR.ITEM"))),
                getHub().getConfiguration().getString("ITEMS.SELECTOR.NAME"),
                selectorLore,
                getHub().getConfiguration().getInt("ITEMS.SELECTOR.AMOUNT"), getHub().getConfiguration().getInt("ITEMS.SELECTOR.SLOT"), "ITEMS.SELECTOR.LORE",
                getHub().getConfiguration().getBoolean("ITEMS.SELECTOR.ENABLED"));
        new JoinItem(player, new ItemStack(Material.valueOf(getHub().getConfiguration().getString("ITEMS.ENDERBUTT.ITEM"))),
                getHub().getConfiguration().getString("ITEMS.ENDERBUTT.NAME"),
                enderButtLore,
                getHub().getConfiguration().getInt("ITEMS.ENDERBUTT.AMOUNT"),
                getHub().getConfiguration().getInt("ITEMS.ENDERBUTT.SLOT"),
                "ITEMS.SELECTOR.LORE",
                getHub().getConfiguration().getBoolean("ITEMS.ENDERBUTT.ENABLED"));
        new JoinItem(player, new ItemStack(Material.valueOf(getHub().getConfiguration().getString("ITEMS.VISIBLE.ITEM"))),
                getHub().getConfiguration().getString("ITEMS.VISIBLE.NAME"),
                visibleLore,
                getHub().getConfiguration().getInt("ITEMS.VISIBLE.AMOUNT"),
                getHub().getConfiguration().getInt("ITEMS.VISIBLE.SLOT"),
                "ITEMS.VISIBLE.LORE",
                getHub().getConfiguration().getBoolean("ITEMS.VISIBLE.ENABLED"));
        new JoinItem(player, new ItemStack(Material.valueOf(getHub().getConfiguration().getString("ITEMS.INFORMATION.ITEM"))),
                getHub().getConfiguration().getString("ITEMS.INFORMATION.NAME"),
                informationLore,
                getHub().getConfiguration().getInt("ITEMS.INFORMATION.AMOUNT"),
                getHub().getConfiguration().getInt("ITEMS.INFORMATION.SLOT"),
                "ITEMS.INFORMATION.LORE",
                getHub().getConfiguration().getBoolean("ITEMS.INFORMATION.ENABLED"));
        new JoinItem(player, new ItemStack(Material.valueOf(getHub().getConfiguration().getString("ITEMS.LOBBY.ITEM"))),
                getHub().getConfiguration().getString("ITEMS.LOBBY.NAME"),
                lobbyLore,
                getHub().getConfiguration().getInt("ITEMS.LOBBY.AMOUNT"),
                getHub().getConfiguration().getInt("ITEMS.LOBBY.SLOT"),
                "ITEMS.LOBBY.LORE",
                getHub().getConfiguration().getBoolean("ITEMS.LOBBY.ENABLED"));
        new JoinItem(player, new ItemStack(Material.valueOf(getHub().getConfiguration().getString("ITEMS.COSMETIC.ITEM"))),
                getHub().getConfiguration().getString("ITEMS.COSMETIC.NAME"),
                cosmeticLore,
                getHub().getConfiguration().getInt("ITEMS.COSMETIC.AMOUNT"),
                getHub().getConfiguration().getInt("ITEMS.COSMETIC.SLOT"),
                "ITEMS.COSMETIC.LORE",
                getHub().getConfiguration().getBoolean("ITEMS.COSMETIC.ENABLED"));
    }

    private void joinMessage(Player player){

        for (final String m : getHub().getConfiguration().getStringList("JOIN-MESSAGE")) {
            player.sendMessage(CC.toColor(m));
        }
    }
}
