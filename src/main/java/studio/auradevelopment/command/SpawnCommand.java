package studio.auradevelopment.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import studio.auradevelopment.Hub;
import studio.auradevelopment.util.CC;

public class SpawnCommand implements CommandExecutor {

    private final Hub hub;

    public SpawnCommand(Hub hub) {
        this.hub = hub;
    }

    public Hub getHub() {
        return hub;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        if (!(sender.hasPermission("hub.command.spawn"))) {
            return false;
        }

        Player player = (Player) sender;
        player.teleport(new Location(Bukkit.getWorld("World"),
                getHub().getConfiguration().getDouble("SPAWN.X"),
                getHub().getConfiguration().getDouble("SPAWN.Y"),
                getHub().getConfiguration().getDouble("SPAWN.Z")));
        player.sendMessage(CC.toColor(getHub().getLanguage().getString("SPAWN")));

        return true;
    }
}
