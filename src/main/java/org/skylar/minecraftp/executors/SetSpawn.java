package org.skylar.minecraftp.executors;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.skylar.minecraftp.Main;
import org.skylar.minecraftp.utilities.ICommand.ACommand;
import org.skylar.minecraftp.utilities.ICommand.ICommand;

@ACommand(name = "setspawn", permissions = "lobby.admin", requiresPlayer = true)
public class SetSpawn extends ICommand {

    @Override
    public void execute(Player p, String[] args) {

        if(args.length > 0)showSyntax(p);
        Main.getInstance().getLocationAPI().saveLocation(p.getLocation(), "spawn");
        p.sendMessage(Main.getInstance().colorWith("&aSpawn &ahas successfully been created!"));
        p.playSound(p.getLocation(), Sound.LEVEL_UP, 10.0f, 10.0f);

    }

    private void showSyntax(Player p){
        p.sendMessage(ChatColor.RED + "/"+getACommand().name());
    }

}
