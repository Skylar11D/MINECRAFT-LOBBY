package org.skylar.minecraftp.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.skylar.minecraftp.Main;

public class InventoryClickListener implements Listener {


    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        String gamesTitle = Main.getInstance().getConfig().getString("inventories.games.title");

        if(e.getClickedInventory().getType() == InventoryType.PLAYER){
            if(!Main.getInstance().getBuilders().contains(e.getWhoClicked())){
                e.setCancelled(true);
            }
        }

        if(e.getClickedInventory().getTitle() == gamesTitle){
            e.setCancelled(true);
            if(e.getCurrentItem().getItemMeta().getDisplayName() == "§c§lTNT§f§lRUN"){
                Main.getInstance().send((Player) e.getWhoClicked(), "tntrun");
            }
        }

    }

}
