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
public class PlayerDisconnectedEvent extends CombatEvent {
    public static final String EventId = "player-disconnected";
    
    public PlayerDisconnectedEvent(String combatId) {
        super(combatId);
    }

    public static PlayerDisconnectedEvent fromString(String str) {
        var map = CollectionFunctions.parseToMap(str);
        return new PlayerDisconnectedEvent(
            map.get("combat-id"));
    }
}
