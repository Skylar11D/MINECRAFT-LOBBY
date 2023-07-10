package org.skylar.minecraftp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.skylar.minecraftp.Main;

public class PlaceAndBreakListener implements Listener {


    @EventHandler
    public void onPlace(BlockPlaceEvent e){

        if(!Main.getInstance().getBuilders().contains(e.getPlayer())){
            e.getPlayer().sendMessage(ChatColor.RED + ("type /build to enable the building mode"));
            e.setCancelled(true);
            Bukkit.getWorld(e.getPlayer().getWorld().getName()).playEffect(e.getBlockPlaced().getLocation().add(0, 0, 1), Effect.SMOKE, 1);
        }

    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){

        if(!Main.getInstance().getBuilders().contains(e.getPlayer())){
            e.getPlayer().sendMessage(ChatColor.RED + ("type /build to enable the building mode"));
            e.setCancelled(true);
            Bukkit.getWorld(e.getPlayer().getWorld().getName()).playEffect(e.getBlock().getLocation().add(0, 1, 1), Effect.SMOKE, 1);
        }

    }

}
