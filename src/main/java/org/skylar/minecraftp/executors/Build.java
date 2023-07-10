package org.skylar.minecraftp.executors;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.skylar.minecraftp.Main;
import org.skylar.minecraftp.utilities.ICommand.ACommand;
import org.skylar.minecraftp.utilities.ICommand.ICommand;

@ACommand(name = "build", permissions = "build.admin", requiresPlayer = true)
public class Build extends ICommand {

    @Override
    public void execute(Player player, String[] args) {

        if(args.length == 0){
            if(!Main.getInstance().getBuilders().contains(player)){
                Main.getInstance().getBuilders().add(player);
                player.sendMessage(Main.getInstance().colorWith("&abuild &7mode has been turned &aon"));
            } else {
                Main.getInstance().getBuilders().remove(player);
                player.sendMessage(Main.getInstance().colorWith("&abuild &7mode has been turned &coff"));
            }
        } else {
            player.sendMessage(ChatColor.RED+"usage: /build");
        }

    }
}
