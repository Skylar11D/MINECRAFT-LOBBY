package org.skylar.minecraftp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.skylar.minecraftp.Main;
import org.skylar.minecraftp.utilities.board.FastBoard;
import org.skylar.minecraftp.utilities.managers.inventory.InventoryManager;
import org.skylar.minecraftp.utilities.managers.inventory.InventoryType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionsListener implements Listener {

    public ExecutorService pool = Executors.newCachedThreadPool();

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        FileConfiguration cfg = Main.getInstance().getConfig();
        String coloredJoinMsg = Main.getInstance().colorWith(cfg.getString("connection.join.message"));
        String coloredSBTitle = Main.getInstance().colorWith(cfg.getString("server.scoreboard.title"));
        boolean cjst = cfg.getBoolean("connection.join.spawn-teleportation");
        boolean cjme = cfg.getBoolean("connection.join.message-enabled");
        int sbScores = cfg.getInt("server.scoreboard.lines");

       pool.execute(() -> {
            if(cjst)
                p.teleport(Main.getInstance().getLocationAPI().fromName("spawn"));
            if(cjme){
                e.setJoinMessage(coloredJoinMsg.replace("{player}", p.getName()));
            }else{e.setJoinMessage(null);}

           //Main.getInstance().getPlayerManager().getPlayer(p).prepareEnvironment();
           p.setGameMode(GameMode.ADVENTURE);

           Main.getInstance().getPlayerManager().registerPlayer(p);

        });

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();

        Main.getInstance().getPlayerManager().unregisterPlayer(p);

    }

}
