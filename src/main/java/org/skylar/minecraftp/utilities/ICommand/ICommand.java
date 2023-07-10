package org.skylar.minecraftp.utilities.ICommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public abstract class ICommand implements CommandExecutor {

    public ACommand aCommand;


    public ICommand(){
        aCommand = getClass().getDeclaredAnnotation(ACommand.class);
        Objects.requireNonNull(aCommand, "Command must not be left without annotations");
    }

    public ACommand getACommand() {
        return aCommand;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!aCommand.permissions().isEmpty()){
            if (!sender.hasPermission(aCommand.permissions())){
                sender.sendMessage(ChatColor.RED + "You're not qualified to access this command");
                return true;
            }
        }

        if(aCommand.requiresPlayer()){
            if(!(sender instanceof Player)){
                sender.sendMessage("Only players may access this command");
                return false;
            }
            execute((Player) sender, args);
        }

        execute(sender, args);
        return true;
    }

    public void execute(Player player, String[] args){}
    public void execute(CommandSender sender, String[] args){}

}
