package org.skylar.minecraftp.utilities.tasks;

import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.skylar.minecraftp.Main;

import java.lang.reflect.Field;

public class TabList extends BukkitRunnable {


    @Override
    public void run() {

        try {
            PacketPlayOutPlayerListHeaderFooter ppoplhf = new PacketPlayOutPlayerListHeaderFooter();

            Field a = ppoplhf.getClass().getDeclaredField("a"); // variable of the header in ppolhf class
            a.setAccessible(true);
            Field b = ppoplhf.getClass().getDeclaredField("b"); // variable of footer in ppolhf class
            b.setAccessible(true);


            Object header = new ChatComponentText(Main.getInstance().getConfig().getString("server.tablist.header"));
            Object footer = new ChatComponentText(Main.getInstance().getConfig().getString("server.tablist.footer"));

            a.set(ppoplhf, header);
            b.set(ppoplhf, footer);

            if(Bukkit.getServer().getOnlinePlayers().size() == 0)return;
            for(Player p : Bukkit.getOnlinePlayers())
                ((CraftPlayer)p).getHandle().playerConnection.sendPacket(ppoplhf);

        } catch (Exception e){
            e.printStackTrace();
        }


    }

}
