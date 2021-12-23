package studio.auradevelopment.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import studio.auradevelopment.Hub;

public class AuraDamageListener implements Listener {

    private final Hub hub;

    public AuraDamageListener(Hub hub){
        this.hub = hub;
    }

    public Hub getHub() {
        return hub;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event){

        event.setDamage(0);
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){

        event.setDamage(0);
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByBlockEvent event){

        event.setDamage(0);
        event.setCancelled(true);
    }
}
