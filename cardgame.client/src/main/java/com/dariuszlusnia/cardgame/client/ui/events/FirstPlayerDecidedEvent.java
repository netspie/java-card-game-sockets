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
public class FirstPlayerDecidedEvent extends CombatEvent {
    public static final String EventId = "first-player-decided";
    public final String PlayerId;

    public FirstPlayerDecidedEvent(String combatId, String playerId) {
        super(combatId);
        PlayerId = playerId;
    }

    public static FirstPlayerDecidedEvent fromString(String str) {
        var map = CollectionFunctions.parseToMap(str);
        return new FirstPlayerDecidedEvent(
            map.get("combat-id"), map.get("player-id"));
    }
}
