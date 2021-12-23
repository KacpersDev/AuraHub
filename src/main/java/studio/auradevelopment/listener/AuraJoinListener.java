package studio.auradevelopment.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import studio.auradevelopment.Hub;
import studio.auradevelopment.util.CC;
import studio.auradevelopment.util.item.JoinEffects;

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
    }

    private void joinMessage(Player player){

        for (final String m : getHub().getConfiguration().getStringList("JOIN-MESSAGE")) {
            player.sendMessage(CC.toColor(m));
        }
    }
}
