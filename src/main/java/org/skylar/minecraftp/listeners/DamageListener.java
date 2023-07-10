package org.skylar.minecraftp.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.skylar.minecraftp.Main;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e){

        if(!Main.getInstance().getConfig().getBoolean("world.fall-damage")){
            if(e.getCause() == EntityDamageEvent.DamageCause.FALL){
                e.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void onDamagee(EntityDamageByEntityEvent e){

        if(e.getEntity() instanceof Player)
            e.setCancelled(true);
    }

}
