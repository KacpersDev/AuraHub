package studio.auradevelopment.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import studio.auradevelopment.Hub;
import studio.auradevelopment.manager.BuildManager;
import studio.auradevelopment.util.CC;

public class BuildModeCommand implements CommandExecutor {

    private final Hub hub;
    private final BuildManager buildManager = new BuildManager();

    public BuildModeCommand(Hub hub) {
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

        if (!(sender.hasPermission("hub.command.buildmode"))) {
            return false;
        }

        Player player = (Player) sender;
        if (!buildManager.buildMod.contains(player)) {
            player.sendMessage(CC.toColor(getHub().getLanguage().getString("BUILDMODE.ENABLED")));
            buildManager.buildMod.add(player);
        } else {
            player.sendMessage(CC.toColor(getHub().getLanguage().getString("BUILDMODE.DISABLED")));
             buildManager.buildMod.remove(player);
        }

        return true;
    }
}
