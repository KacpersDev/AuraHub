package studio.auradevelopment;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;
import studio.auradevelopment.board.ScoreboardManager;
import studio.auradevelopment.board.reflection.BoardListener;
import studio.auradevelopment.command.AuraHub;
import studio.auradevelopment.command.BuildModeCommand;
import studio.auradevelopment.command.SetSpawnCommand;
import studio.auradevelopment.command.SpawnCommand;
import studio.auradevelopment.config.Config;
import studio.auradevelopment.listener.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class Hub extends JavaPlugin implements PluginMessageListener {

    private static Hub instance;
    public Map<String,Integer> playerCount;
    public Chat chat = null;
    public File file = new File(getDataFolder(), "config.yml");
    public FileConfiguration configuration = new YamlConfiguration();
    public File scoreboard = new File(getDataFolder(),"scoreboard.yml");
    public FileConfiguration scoreboardConfiguration = new YamlConfiguration();
    public File language = new File(getDataFolder(), "language.yml");
    public FileConfiguration languageConfiguration = new YamlConfiguration();
    public File inventories = new File(getDataFolder(), "inventories.yml");
    public FileConfiguration inventoriesConfiguration = new YamlConfiguration();


    @Override
    public void onEnable() {
        instance = this;
        setupChat();
        loadConfigs();
        this.playerCount = new HashMap<>();
        this.bungeecord();
        command();
        listener();
        board();
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    public FileConfiguration getScoreboard() {
        return scoreboardConfiguration;
    }

    public FileConfiguration getInventories(){return inventoriesConfiguration; }

    public FileConfiguration getLanguage(){ return this.languageConfiguration; }

    public static Hub getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private void board(){

        new ScoreboardManager(this, new BoardListener(this));
    }

    private void command(){
        getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("buildmode").setExecutor(new BuildModeCommand(this));
        getCommand("aurahub").setExecutor(new AuraHub());
    }

    private void listener(){

        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new AuraJoinListener(this), this);
        manager.registerEvents(new AuraEnderButt(this), this);
        manager.registerEvents(new AuraJumpBoost(), this);
        manager.registerEvents(new AuraDamageListener(this), this);
        manager.registerEvents(new AuraFoodListener(), this);
        manager.registerEvents(new AuraWeatherListener(), this);
        manager.registerEvents(new AuraVoidListener(this), this);
        manager.registerEvents(new AuraSelectorListener(this), this);
        manager.registerEvents(new AuraAntiGriefListener(this),this);
        manager.registerEvents(new AuraVisibilityListener(this), this);
    }

    private void loadConfigs(){

        //config
        new Config(file, configuration, "config.yml");
        new Config(scoreboard, scoreboardConfiguration, "scoreboard.yml");
        new Config(language, languageConfiguration, "language.yml");
        new Config(inventories, inventoriesConfiguration, "inventories.yml");
    }

    private boolean setupChat() {
        RegisteredServiceProvider chatProvider = this.getServer().getServicesManager().getRegistration(Chat.class);
        if (chatProvider != null) {
            chat = (Chat) chatProvider.getProvider();
        }
        return chat != null;
    }

    private void bungeecord() {
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
    }

    private final void updateCount() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (Bukkit.getServer().getOnlinePlayers().size() > 0) {
                    Hub.this.getCount((Player)Bukkit.getServer().getOnlinePlayers().toArray()[0], null);
                }
            }
        }.runTaskTimerAsynchronously(this, 20L, 20L);
    }

    @Override
    public void onPluginMessageReceived(final String channel, final Player player, final byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        try {
            if (message.length == 0) {
                return;
            }
            final ByteArrayDataInput in = ByteStreams.newDataInput(message);
            final String subchannel = in.readUTF();
            if (subchannel.equals("PlayerCount")) {
                final String server = in.readUTF();
                final int playerCount = in.readInt();
                this.playerCount.put(server, playerCount);
            }
        }
        catch (Exception ex) {}
    }

    public void getCount(final Player player, String server) {
        if (server == null) {
            server = "ALL";
        }
        final ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF(server);
        player.sendPluginMessage(this, "BungeeCord", out.toByteArray());
    }

    public int getOnlineCount(String server) {
        if (server == null) {
            server = "ALL";
            int online = 0;
            for (final int next : this.playerCount.values()) {
                if (next <= 0) {
                    continue;
                }
                online += next;
            }
            return online;
        }
        this.playerCount.putIfAbsent(server, -1);
        return this.playerCount.get(server);
    }

}
