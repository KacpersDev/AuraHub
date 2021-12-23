package studio.auradevelopment.board.reflection;

import org.bukkit.entity.Player;
import studio.auradevelopment.Hub;
import studio.auradevelopment.board.ScoreboardEntrySupplier;
import studio.auradevelopment.util.CC;

import java.util.ArrayList;
import java.util.List;

public class BoardListener implements ScoreboardEntrySupplier {

    private final Hub hub;

    public BoardListener(Hub hub){
        this.hub = hub;
    }

    public Hub getHub() {
        return hub;
    }

    @Override
    public String getScoreboardTitle() {
        return CC.toColor(getHub().getScoreboard().getString("SCOREBOARD-TITLE"));
    }

    @Override
    public List<String> getScoreboardEntries(Player player) {
        List<String> lines = new ArrayList<>();
        String rank = getHub().chat.getPrimaryGroup(player);
        int online = getHub().getOnlineCount("ALL");
        String onlinePlayers = String.valueOf(online);
        for (final String l : getHub().getScoreboard().getStringList("SCOREBOARD.NORMAL")) {
            lines.add(CC.toColor(l)
                    .replace("%rank%", rank)
                    .replace("%player%", player.getName())
                    .replace("%online_players%", onlinePlayers));
        }
        return lines;
    }
}
