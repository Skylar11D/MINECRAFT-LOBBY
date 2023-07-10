package org.skylar.minecraftp.utilities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.skylar.minecraftp.Main;

public class LocationAPI {

    protected Main main;

    public LocationAPI(Main main){
        this.main = main;
    }


    public Location fromName(String locationName){
        String uncapitalizedName = locationName.toLowerCase();
        if(!main.getConfig().contains("locations."+uncapitalizedName))return new Location(Bukkit.getWorld("world"), 0, 5, 0);

        World world = Bukkit.getWorld(main.getConfig().getString("locations."+uncapitalizedName+".world"));
        double x = main.getConfig().getDouble("locations."+uncapitalizedName+".x");
        double y = main.getConfig().getDouble("locations."+uncapitalizedName+".y");
        double z = main.getConfig().getDouble("locations."+uncapitalizedName+".z");
        float yaw = (float)main.getConfig().getInt("locations."+uncapitalizedName+".yaw");
        float pitch = (float)main.getConfig().getInt("locations."+uncapitalizedName+".pitch");

        return new Location(world, x, y, z, yaw, pitch);
    }

    public void saveLocation(Location location, String locationName){
        String uncapitalizedName = locationName.toLowerCase();
        main.getConfig().set("locations."+uncapitalizedName+".x", location.getX());
        main.getConfig().set("locations."+uncapitalizedName+".y", location.getY());
        main.getConfig().set("locations."+uncapitalizedName+".z", location.getZ());
        main.getConfig().set("locations."+uncapitalizedName+".yaw", location.getYaw());
        main.getConfig().set("locations."+uncapitalizedName+".pitch", location.getPitch());
        try {
            main.getConfig().set("locations."+uncapitalizedName+".world", location.getWorld().getName());
        } catch (Exception e){
            e.printStackTrace();
        }


        main.saveConfig();
    }

}
