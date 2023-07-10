package org.skylar.minecraftp.utilities.managers.inventory;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.plugin.Plugin;
import org.skylar.minecraftp.Main;
import org.skylar.minecraftp.utilities.ItemStackBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InventoryManager {

    private Plugin plugin;
    private Inventory inv;

    public InventoryManager(){}
    public InventoryManager(Plugin plugin){this.plugin=plugin;}


    public Inventory getInventoryOf(InventoryType type){

        if(type == InventoryType.GAMES){
            this.inv = Bukkit.createInventory(null, 54, Main.getInstance().getConfig().getString("inventories.games.title"));

            for(int i=0;i<54;i++){

                if(i == 22){
                    inv.setItem(i, new ItemStackBuilder(Material.TNT).
                            setDisplayName("§c§lTNT§f§lRUN").
                            addLores("§7Dodge being falled into the void", "§7from trapped sand blocks of §ctnts§7!").
                            build());
                } else if (i == 21) {
                    inv.setItem(i, new ItemStackBuilder(Material.BEDROCK).
                            setDisplayName("§7soon").
                            build());
                } else if (i == 23) {
                    inv.setItem(i, new ItemStackBuilder(Material.BEDROCK).
                            setDisplayName("§7soon").
                            build());
                } else {
                    inv.setItem(i, new ItemStackBuilder(Material.STAINED_GLASS_PANE, 1, (short) 7).
                            addItemFlags(ItemFlag.HIDE_ENCHANTS).
                            setDisplayName(".").
                            build());
                }
            }

            return this.inv;
        }

        return Objects.requireNonNull(null); //just to know if it actually returns null lol
    }

}
