package org.skylar.minecraftp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.skylar.minecraftp.Main;

public class WeatherChangeListener implements Listener {

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e){

        if(!Main.getInstance().getConfig().getBoolean("world.weather.changeable")){
            e.setCancelled(true);
        }

    }

}
