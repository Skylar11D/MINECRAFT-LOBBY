package org.skylar.minecraftp;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.skylar.minecraftp.executors.Build;
import org.skylar.minecraftp.executors.SetSpawn;
import org.skylar.minecraftp.executors.Spawn;
import org.skylar.minecraftp.listeners.*;
import org.skylar.minecraftp.utilities.ICommand.ICommand;
import org.skylar.minecraftp.utilities.plugin.PluginManager;
import org.skylar.minecraftp.utilities.plugin.Provider;

public class MainProvider implements Provider {

    private Listener[] listeners = {
            new ConnectionsListener(), new WeatherChangeListener(), new DamageListener(),
            new SpawnListener(), new HungerListener(), new InteractListener(),
            new PlaceAndBreakListener(), new PlayerDropAndPickUpListener(), new DeathListener(),
            new InventoryClickListener(), new ProjectileListener()
    };
    private ICommand[] iCommands = {new SetSpawn(), new Spawn(), new Build()};

    public void onEnable() {
        PluginManager.getPluginManager().initListeners(Main.getInstance(), listeners);
        PluginManager.getPluginManager().initExecutors(iCommands);
    }

    public void onDisable(){
        Bukkit.getServer().getMessenger().unregisterIncomingPluginChannel(Main.getPlugin(Main.class));
        Bukkit.getServer().getMessenger().unregisterOutgoingPluginChannel(Main.getPlugin(Main.class));
    }
}
