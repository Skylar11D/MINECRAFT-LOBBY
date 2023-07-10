package org.skylar.minecraftp.utilities.managers.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.skylar.minecraftp.utilities.assets.TitleType;
import org.skylar.minecraftp.utilities.managers.player.LPlayer;

import java.util.HashSet;
import java.util.Set;

public class PlayerManager {

    //borrowed concept

    Set<LPlayer> players = new HashSet<>();

    public Set<LPlayer> getPlayers() {
        return players;
    }

    public LPlayer getPlayer(Player p){

        return players.stream().filter(lPlayer -> lPlayer.getPlayer() == p).findFirst().orElse(null);
    }

    public void registerPlayer(Player p){

        players.add(new LPlayer(){

            @Override
            public Player getPlayer() {
                return p;
            }

        });

        //players.add(() -> {return p;});
        getPlayer(p).sendTitle(TitleType.SUBTITLE, ChatColor.RED+"preparing your properties...", "");

        getPlayer(p).prepareInventory();
        getPlayer(p).prepareScoreBoard();
    }

    public void unregisterPlayer(Player p){
        players.remove(getPlayer(p));
    }

}
