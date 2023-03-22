package org.login.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class PlayerLoginEvent extends Event implements Cancellable {

    @Getter
    private Player player;

    private final HandlerList handlers = new HandlerList();

    @Getter
    @Setter
    private boolean isCancelled;

    public PlayerLoginEvent(Player player) {
        this.player = player;
        this.isCancelled = false;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}