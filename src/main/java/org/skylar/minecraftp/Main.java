package org.skylar.minecraftp;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.skylar.minecraftp.utilities.LocationAPI;
import org.skylar.minecraftp.utilities.managers.player.PlayerManager;
import org.skylar.minecraftp.utilities.managers.inventory.InventoryManager;
import org.skylar.minecraftp.utilities.plugin.Provider;
import org.skylar.minecraftp.utilities.tasks.TabList;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends JavaPlugin implements PluginMessageListener {

    private volatile static Main instance;
    private Provider provider;
    private LocationAPI locations;
    private InventoryManager invManager;
    private PlayerManager playerManager;
    private List<Player> builders;
    private Map<String, Integer> serverSize;


    @Override
    public void onEnable() {
        builders = new ArrayList<>();
        instance = this;
        provider = new MainProvider();
        locations = new LocationAPI(this);
        invManager = new InventoryManager(this);
        playerManager = new PlayerManager();
        serverSize = new HashMap<>();

        provider.onEnable();
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "weather clear");
        new TabList().runTaskTimer(this, 0, 20*2);
        configInitialization();

        Bukkit.getLogger().info("Registering players..");
        if(Bukkit.getServer().getOnlinePlayers().size() != 0) {
            try {
                Bukkit.getServer().getOnlinePlayers().forEach(this.getPlayerManager()::registerPlayer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDisable(){
        this.provider.onDisable();
    }

    public static Main getInstance() {return instance;}

    public LocationAPI getLocationAPI() {
        return locations;
    }

    public InventoryManager getInventoryManager() {
        return invManager;
    }

    public String colorWith(String str){return ChatColor.translateAlternateColorCodes('&', str);}


    private void configInitialization(){
        if(!getDataFolder().exists()){
            Bukkit.getLogger().warning("[FileManager] main directory not found, generating new one..");
            if(getDataFolder().mkdirs()){
                try {
                    System.out.println("[FileManager] "+ getDataFolder().getName() +" has been created");
                    this.getConfig().set("connection.join.spawn-teleportation", true);
                    this.getConfig().set("connection.join.message", "&8&l| &e+ &8&l|&r {player}");
                    this.getConfig().set("connection.join.message-enabled", true);
                    this.getConfig().set("world.weather.changeable", false);
                    this.getConfig().set("world.fall-damage", false);
                    this.getConfig().set("world.otherEntitiesSpawning", false);
                    this.getConfig().set("server.scoreboard.lines", 5);
                    this.getConfig().set("server.scoreboard.title", "§cHell§4Mc");
                    this.getConfig().set("server.scoreboard.line.0", "§cWelcome to the server!");
                    this.getConfig().set("server.scoreboard.line.1", "§r");
                    this.getConfig().set("server.scoreboard.line.2", "§cEnjoy our special games!");
                    this.getConfig().set("server.scoreboard.line.3", "§r");
                    this.getConfig().set("server.scoreboard.line.4", "§7play.HellMC.tk");
                    this.getConfig().set("inventories.games.title", "§7Where would you like to go");
                    this.getConfig().set("inventories.settings.title", "§7Settings");
                    this.getConfig().set("server.tablist.header", "§cHell§4MC\n§7Welcome to the server!");
                    this.getConfig().set("server.tablist.footer", "§7store: www.HellMc.net/store\n§7Enjoy playing");
                    saveConfig();
                } catch (Exception e){e.printStackTrace();}

            } else {
                Bukkit.getLogger().warning("[FileManager] failed to initialize the directory (Already exists?)");
            }
        }

    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public List<Player> getBuilders() {
        return builders;
    }

    public Map<String, Integer> getServerSize(){
        return this.serverSize;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if(!channel.equals("BungeeCord"))
            return;

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String cat = in.readUTF();

        if(cat.equals("PlayerCount")) {
            String server = in.readUTF();
            if(server.equalsIgnoreCase("lobby")){
                int size = in.readInt();
                getServerSize().put("lobby", size);
                return;
            }

            if(server.equalsIgnoreCase("tntrun")){
                int size = in.readInt();
                getServerSize().put("tntrun", size);
                return;
            }

        }

    }

    public void send(Player player, String server){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConnectOther");
        out.writeUTF(player.getName());
        out.writeUTF("server");
        Bukkit.getServer().sendPluginMessage(this, "BungeeCord", out.toByteArray());
    }

}
