/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.server.features.combat.events;

import java.util.StringJoiner;

/**
 *
 * @author dariu
 */
public class PlayerDisconnectedEvent extends CombatEvent {
    public final String EventId = "player-disconnected";

    public PlayerDisconnectedEvent(String combatId) {
        super(combatId);
    }

    @Override
    public String toString() {
        return new StringJoiner("&")
            .add("combat-id=" + CombatId)
            .add("event-id=" + EventId)
            .toString();
    }
}
