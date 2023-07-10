package org.skylar.minecraftp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.skylar.minecraftp.Main;
import org.skylar.minecraftp.utilities.ItemStackBuilder;
import org.skylar.minecraftp.utilities.managers.player.LPlayer;
import org.skylar.minecraftp.utilities.managers.inventory.InventoryType;

public class InteractListener implements Listener {

    private static long duration = System.currentTimeMillis();

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        LPlayer lPlayer = Main.getInstance().getPlayerManager().getPlayer(e.getPlayer());
        Player p = e.getPlayer();
        Action action0 = Action.RIGHT_CLICK_AIR;
        Action action1 = Action.RIGHT_CLICK_BLOCK;
        String clickedItemName = e.getItem().getItemMeta().getDisplayName();

        if(e.getAction() == action0 || e.getAction() == action1){

            if (clickedItemName == "§cGames") {
                lPlayer.openInventory(InventoryType.GAMES);
            }

            if (clickedItemName == "§6Fun Stick") {
                if(duration >= System.currentTimeMillis()) {
                    int time = Math.round((duration-System.currentTimeMillis())/1000L);
                    p.sendMessage(ChatColor.RED + "wait for " + time + " before you can shoot again");
                } else {
                    p.launchProjectile(Snowball.class);
                    duration = System.currentTimeMillis() + 5000L;
                }

            }

            if (clickedItemName == "§aShown Players") {
                Bukkit.getServer().getOnlinePlayers().forEach(p::hidePlayer);
                ItemStack item = new ItemStackBuilder(351, 1, (short)8).
                        setDisplayName("§7Hidden Players").build();
                e.getItem().setData(null);
                e.getPlayer().getInventory().setItem(7, item);
            }

            if (clickedItemName == "§7Hidden Players") {
                Bukkit.getServer().getOnlinePlayers().forEach(p::showPlayer);
                ItemStack item = new ItemStackBuilder(351, 1, (short)10).
                        setDisplayName("§aShown Players").build();
                e.getItem().setData(null);
                e.getPlayer().getInventory().setItem(7, item);
            }

        }

    }

}
