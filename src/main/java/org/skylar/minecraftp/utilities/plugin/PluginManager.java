package org.skylar.minecraftp.utilities.plugin;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.reflections.Reflections;
import org.skylar.minecraftp.Main;
import org.skylar.minecraftp.utilities.ICommand.ICommand;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.logging.Level;

public class PluginManager {

    public static PluginManager pm;
    static {pm = new PluginManager();}

    public void initListeners(Plugin instance, Listener... listeners){

        for(Listener listener : listeners){
            Bukkit.getPluginManager().registerEvents(listener, instance);
            Bukkit.getLogger().log(Level.INFO, "[PluginManager] loaded listener " + listener.getClass().getSimpleName());
        }

    }

    public void initExecutors(ICommand... iCommands) {

        for (ICommand iCommand : iCommands) {
            Main.getInstance().getCommand(iCommand.getACommand().name()).setExecutor(iCommand);
            Bukkit.getLogger().log(Level.INFO, "[PluginManager] loaded executor /" + iCommand.getACommand().name());
        }

        //using reflections(not used for dependency issues)
        /*for (Class<?> clazz : new Reflections("org.skylar.minecraftp.executors").getSubTypesOf(ICommand.class)) {
            try {
                ICommand command = (ICommand) clazz.getDeclaredConstructor().newInstance();
                Main.getInstance().getCommand(command.getACommand().name()).setExecutor(command);
            } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }

        }*/
    }


    public static PluginManager getPluginManager() {
        return pm;
    }
}
