package org.skylar.minecraftp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.skylar.minecraftp.Main;

public class PlayerDropAndPickUpListener implements Listener {

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e){

        if(!Main.getInstance().getBuilders().contains(e.getPlayer())){
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if(!Main.getInstance().getBuilders().contains(e.getPlayer())){
            e.setCancelled(true);
        }
    }

}
