package org.skylar.minecraftp.utilities;

import com.google.common.collect.Maps;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ItemStackBuilder {

    private ItemStack stack;


    public ItemStackBuilder(int itemStackID){
        this.stack = new ItemStack(itemStackID);
    }
    public ItemStackBuilder(int itemStackID, int amount){
        this.stack = new ItemStack(itemStackID, amount);
    }
    public ItemStackBuilder(int itemStackID, int amount, short damage){
        this.stack = new ItemStack(itemStackID, amount, damage);
    }
    public ItemStackBuilder(int itemStackID, int amount, short damage, byte data){
        this.stack = new ItemStack(itemStackID, amount, damage, data);
    }

    public ItemStackBuilder(Material material){
     this.stack = new ItemStack(material);
    }
    public ItemStackBuilder(Material material, int amount){
        this.stack = new ItemStack(material, amount);
    }
    public ItemStackBuilder(Material material, int amount, short damage){
        this.stack = new ItemStack(material, amount, damage);
    }
    public ItemStackBuilder(Material material, int amount, short damage, byte data){
        this.stack = new ItemStack(material, amount, damage, data);
    }

    public ItemStackBuilder setDisplayName(String name){
        ItemMeta meta = this.stack.getItemMeta();
        meta.setDisplayName(name);
        this.stack.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder addLores(String... lores){
        ItemMeta meta = this.stack.getItemMeta();
        meta.setLore(Arrays.asList(lores));
        this.stack.setItemMeta(meta);

        return this;
    }

    public ItemStackBuilder addItemFlags(ItemFlag itemFlag){
        ItemMeta meta = this.stack.getItemMeta();
        meta.addItemFlags(itemFlag);
        this.stack.setItemMeta(meta);

        return this;
    }

    public ItemStackBuilder addEnchanet(Enchantment enchantment, int level){
        this.stack.addEnchantment(enchantment, level);

        return this;
    }

    public ItemStackBuilder addEnchanets(Map<?, ?> enchantmentsWithTheirLevels){
        this.stack.addEnchantments((Map<Enchantment, Integer>) enchantmentsWithTheirLevels);

        return this;
    }

    public ItemStackBuilder setDyeColor(DyeColor color){
        this.stack.setDurability((short) color.getData());
        return this;
    }


    public ItemStack build(){

        return this.stack;
    }

}
