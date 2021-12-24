package studio.auradevelopment.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import studio.auradevelopment.util.CC;

public class AuraHub implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        player.sendMessage(CC.toColor("&f&m----------------------------------------"));
        player.sendMessage(CC.toColor("&eThis server is running &4AuraHub &cv0.0.1"));
        player.sendMessage(CC.toColor("&eDeveloped by &4AuraDevelopment"));
        player.sendMessage(CC.toColor("&f&m----------------------------------------"));

        return true;
    }
}
