package studio.auradevelopment.util;

import net.md_5.bungee.api.ChatColor;

public class CC {

    public static String toColor(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
