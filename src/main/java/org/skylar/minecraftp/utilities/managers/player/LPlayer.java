package org.skylar.minecraftp.utilities.managers.player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.apache.commons.lang.NullArgumentException;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.skylar.minecraftp.Main;
import org.skylar.minecraftp.utilities.ItemStackBuilder;
import org.skylar.minecraftp.utilities.board.FastBoard;
import org.skylar.minecraftp.utilities.managers.inventory.InventoryType;
import org.skylar.minecraftp.utilities.assets.TitleType;

import java.util.Objects;

public abstract class LPlayer {

    public abstract Player getPlayer();

    public void prepareInventory(){

        getPlayer().getInventory().clear();
        //getPlayer().getInventory().setContents(getContents());

        ItemStack[] contents = getContents();
        getPlayer().getInventory().setItem(4, contents[0]);
        getPlayer().getInventory().setItem(1, contents[1]);
        getPlayer().getInventory().setItem(7, contents[2]);

    }

    public void openInventory(InventoryType type){
        //Inventory inv = Main.getInstance().getInventoryManager().getInventoryOf(type); // same as below
        getPlayer().openInventory(preparedInv());
    }

    private Inventory preparedInv(){return Main.getInstance().getInventoryManager().getInventoryOf(InventoryType.GAMES);}
    private ItemStack[] getContents(){
        ItemStack[] ee = new ItemStack[3];

        ee[0] = new ItemStackBuilder(Material.NETHER_STAR).
                setDisplayName("§cGames").build();
        ee[1] = new ItemStackBuilder(Material.BLAZE_ROD).
                setDisplayName("§6Fun Stick").build();
        ee[2] = new ItemStackBuilder(351, 1, (short) 10).
                setDisplayName("§aShown Players").build();

        return ee;
    }

    public void sendTitle(TitleType type, String ln, String ln1){
        if(ln.isEmpty())
            throw new NullArgumentException("Title cannot be null");

        if(type == TitleType.TITLE)
            getPlayer().sendTitle(ln, ln1);

        if(type == TitleType.SUBTITLE){
            ln1 = null;
            IChatBaseComponent component = IChatBaseComponent.ChatSerializer.a("{\"text\":\""+ln+"\"}");
            PacketPlayOutChat packet = new PacketPlayOutChat(component, (byte)2);
            ((CraftPlayer)getPlayer()).getHandle().playerConnection.sendPacket(packet);
        }

    }

    public void prepareScoreBoard(){
        FileConfiguration cfg = Main.getInstance().getConfig();
        int sbScores = cfg.getInt("server.scoreboard.lines");
        String coloredSBTitle = Main.getInstance().colorWith(cfg.getString("server.scoreboard.title"));

        FastBoard fastBoard = new FastBoard(getPlayer());fastBoard.updateTitle(coloredSBTitle);for(int i = 0; i<sbScores; i++)
        {fastBoard.updateLine(i, cfg.getString("server.scoreboard.line."+i));}
    }

}
