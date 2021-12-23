package studio.auradevelopment.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class AuraFoodListener implements Listener {

    @EventHandler
    public void onFood(FoodLevelChangeEvent event){

        event.setCancelled(true);
    }
}
