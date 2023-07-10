package org.skylar.minecraftp.listeners;

import org.apache.logging.log4j.core.net.Priority;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.skylar.minecraftp.Main;
import org.skylar.minecraftp.utilities.managers.player.LPlayer;

import java.util.Arrays;

public class SpawnListener implements Listener {

    //private EntityType[] entities = {EntityType.ARROW, EntityType.PLAYER, EntityType.ARMOR_STAND, EntityType.DROPPED_ITEM, EntityType.UNKNOWN, EntityType.PAINTING};

    @EventHandler
    public void onEntitiesSpawning(CreatureSpawnEvent e){

        if(!Main.getInstance().getConfig().getBoolean("world.otherEntitiesSpawning")){
            /*Arrays.stream(entities).forEach(ent -> {
                if(e.getEntityType() == ent){
                    e.setCancelled(false);
                } else {e.setCancelled(true);}
            });*/

            if(e.getEntityType() == EntityType.ARMOR_STAND ||
                    e.getEntityType() == EntityType.PAINTING ||
                    e.getEntityType() == EntityType.ITEM_FRAME ||
                    e.getEntityType() == EntityType.DROPPED_ITEM ||
                    e.getEntityType() == EntityType.PLAYER ||
                    e.getEntityType() == EntityType.UNKNOWN ||
                    e.getEntityType() == EntityType.ARROW ||
                    e.getEntityType() == EntityType.FIREBALL ||
                    e.getEntityType() == EntityType.SMALL_FIREBALL ||
                    e.getEntityType() == EntityType.FIREWORK ||
            e.getEntityType() == EntityType.VILLAGER ||
                    e.getEntityType() == EntityType.THROWN_EXP_BOTTLE ||
                    e.getEntityType() == EntityType.COMPLEX_PART ||
                    e.getEntityType() == EntityType.FALLING_BLOCK ||
                    e.getEntityType() == EntityType.EXPERIENCE_ORB ||
                    e.getEntityType() == EntityType.SPLASH_POTION ||
                    e.getEntityType() == EntityType.MINECART ||
                    e.getEntityType() == EntityType.MINECART_CHEST ||
                    e.getEntityType() == EntityType.MINECART_TNT ||
                    e.getEntityType() == EntityType.MINECART_COMMAND ||
                    e.getEntityType() == EntityType.MINECART_FURNACE ||
                    e.getEntityType() == EntityType.MINECART_HOPPER ||
                    e.getEntityType() == EntityType.MINECART_MOB_SPAWNER ||
                    e.getEntityType() == EntityType.PRIMED_TNT ||
                    e.getEntityType() == EntityType.ENDER_CRYSTAL
            ){
                e.setCancelled(false);
            }
            else{
                e.setCancelled(true);
            }


        }

    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        e.setRespawnLocation(Main.getInstance().getLocationAPI().fromName("spawn"));
        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 10);
        //Main.getInstance().getPlayerManager().getPlayer(e.getPlayer()).prepareProperties();
    }

}
