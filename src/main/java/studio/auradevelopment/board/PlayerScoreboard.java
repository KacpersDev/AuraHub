package studio.auradevelopment.board;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public final class PlayerScoreboard {

	public static String[] TEAM_NAMES;

	private Scoreboard scoreboard;
	private Objective objective;
	private Player player;
	private ScoreboardEntrySupplier supplier;

	protected PlayerScoreboard(Player player, ScoreboardEntrySupplier supplier) {
		this.player = player;
		this.supplier = supplier;
		Scoreboard scoreboard = player.getScoreboard();
		if (scoreboard == Bukkit.getScoreboardManager().getMainScoreboard()) {
			scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		}
		player.setScoreboard(this.scoreboard = scoreboard);
		Objective objective = scoreboard.getObjective("sidebar");
		if (objective == null) {
			objective = scoreboard.registerNewObjective("sidebar", "dummy");
		}
		objective.setDisplayName(supplier.getScoreboardTitle());
		(this.objective = objective).setDisplaySlot(DisplaySlot.SIDEBAR);
	}

	public void send() {
		List<String> entries = supplier.getScoreboardEntries(player);
		int index = 15;
		if (entries.size() != scoreboard.getEntries().size()) {
			scoreboard.getEntries().forEach(x -> scoreboard.resetScores(x));
		}
		for (String entry : entries) {
			if (index < 1) {
				continue;
			}
			String left = "", right = "";
			if (entry.length() <= 16) {
				left = entry;
			}
			else {
				String first = entry.substring(0, 16);
				String second = entry.substring(16, entry.length());
				if (first.endsWith("\u00a7")) {
					first = first.substring(0, first.length() - 1);
					second = "\u00a7" + second;
				}
				second = ChatColor.getLastColors(first) + second;
				left = first;
				right = second.substring(0, Math.min(second.length(), 16));
				if (right.endsWith("\u00a7")) {
					right = right.substring(0, right.length() - 1);
				}
			}
			Team team = scoreboard.getTeam(TEAM_NAMES[15 - index]);
			if (team == null) {
				team = scoreboard.registerNewTeam(TEAM_NAMES[15 - index]);
			}
			if (!team.hasEntry(TEAM_NAMES[15 - index])) {
				team.addEntry(TEAM_NAMES[15 - index]);
			}
			team.setPrefix(left);
			team.setSuffix(right);
			objective.getScore(TEAM_NAMES[15 - index]).setScore(index--);
		}
	}

	public void clean() {
		if (player.isOnline()) {
			player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
		}
		scoreboard.clearSlot(DisplaySlot.SIDEBAR);
		scoreboard.getTeams().forEach(x -> x.unregister());
		scoreboard.getObjectives().forEach(x -> x.unregister());
		player = null;
		objective = null;
		scoreboard = null;
	}

	static {
		TEAM_NAMES = new String[15];
		for (int i = 0; i < 15; i ++) {
			TEAM_NAMES[i] = ChatColor.RESET.toString() + ChatColor.values()[i] + ChatColor.RESET.toString();
		}
	}

}
