/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.client.ui.events;

import com.dariuszlusnia.cardgame.client.common.CollectionFunctions;
import java.util.StringJoiner;

/**
 *
 * @author dariu
 */
public class PlayerJoinedEvent extends CombatEvent {
    public static final String EventId = "player-joined";
    
    public final String PlayerId;

    public PlayerJoinedEvent(String combatId, String playerId) {
        super(combatId);
        PlayerId = playerId;
    }

    public static PlayerJoinedEvent fromString(String str) {
        var map = CollectionFunctions.parseToMap(str);
        return new PlayerJoinedEvent(
            map.get("combat-id"),
            map.get("player-id"));
    }
}
