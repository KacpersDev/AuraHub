package studio.auradevelopment.board;

import java.util.List;

import org.bukkit.entity.Player;

public interface ScoreboardEntrySupplier {

	String getScoreboardTitle();

	List<String> getScoreboardEntries(Player player);

}
