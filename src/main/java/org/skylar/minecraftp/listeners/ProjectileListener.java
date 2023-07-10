package org.skylar.minecraftp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileListener implements Listener {

    @EventHandler
    public void onHit(ProjectileHitEvent e){
        Player p = (Player) e.getEntity().getShooter();
        Entity pj = e.getEntity();

        if(pj.getType() == EntityType.SNOWBALL){
            if(p.getItemInHand().getType() == Material.BLAZE_ROD){
                p.playSound(p.getLocation(), Sound.CAT_MEOW, 5f, 5f);
                Bukkit.getWorld(p.getWorld().getName()).playEffect(e.getEntity().getLocation(), Effect.HEART, 10);
                Bukkit.getWorld(p.getWorld().getName()).playEffect(e.getEntity().getLocation().subtract(1, 0, 0), Effect.HEART, 10);
                Bukkit.getWorld(p.getWorld().getName()).playEffect(e.getEntity().getLocation().subtract(0, 0, 1), Effect.HEART, 10);
                Bukkit.getWorld(p.getWorld().getName()).playEffect(e.getEntity().getLocation().subtract(-1, 0, 0), Effect.HEART, 10);
                Bukkit.getWorld(p.getWorld().getName()).playEffect(e.getEntity().getLocation().subtract(0, 0, 1), Effect.HEART, 10);
                //Bukkit.getWorld(p.getWorld().getName()).playEffect(e.getEntity().getLocation().add(1, 0, 0), Effect.HEART, 10);
                //Bukkit.getWorld(p.getWorld().getName()).playEffect(e.getEntity().getLocation().add(0, 0, 1), Effect.HEART, 10);
                Bukkit.getWorld(p.getWorld().getName()).playEffect(e.getEntity().getLocation(), Effect.ENDER_SIGNAL, 10);
            }
        }
    }

}
