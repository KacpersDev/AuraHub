package studio.auradevelopment.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import studio.auradevelopment.Hub;
import studio.auradevelopment.util.CC;

public class SetSpawnCommand implements CommandExecutor {

    private final Hub hub;

    public SetSpawnCommand(Hub hub){
        this.hub=hub;
    }

    public Hub getHub() {
        return hub;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        if (!(sender.hasPermission("hub.command.setspawn"))) {
            return false;
        }

        Player player = (Player) sender;
        getHub().getConfiguration().set("SPAWN.X", player.getLocation().getBlockX());
        getHub().getConfiguration().set("SPAWN.Y", player.getLocation().getBlockY());
        getHub().getConfiguration().set("SPAWN.Z", player.getLocation().getBlockZ());
        player.sendMessage(CC.toColor(getHub().getLanguage().getString("SETSPAWN")));


        return true;
    }
}
