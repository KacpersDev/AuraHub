package studio.auradevelopment.util.item;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import studio.auradevelopment.Hub;

public class JoinEffects {

    public JoinEffects(Player player){

        player.addPotionEffect(PotionEffectType.SPEED.createEffect(999999,
                Hub.getInstance().getConfiguration().getInt("JOIN.SPEED")));
    }
}
