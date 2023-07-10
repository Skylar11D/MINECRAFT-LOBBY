package org.skylar.minecraftp.executors;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.skylar.minecraftp.Main;
import org.skylar.minecraftp.utilities.ICommand.ACommand;
import org.skylar.minecraftp.utilities.ICommand.ICommand;

@ACommand(name = "spawn", requiresPlayer = true)
public class Spawn extends ICommand {

    @Override
    public void execute(Player p, String[] args) {

        if(args.length > 0)showSyntax(p);
        try {
            p.teleport(Main.getInstance().getLocationAPI().fromName("spawn"));
            p.sendMessage(Main.getInstance().colorWith("&fYou have returned to the &aspawn"));
            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
        } catch (Exception e){
            e.printStackTrace();
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
            p.sendMessage(ChatColor.RED + "Error: spawn not found");
        }
    }

    private void showSyntax(Player p){
        p.sendMessage(ChatColor.RED + "/"+getACommand().name());
    }
}
