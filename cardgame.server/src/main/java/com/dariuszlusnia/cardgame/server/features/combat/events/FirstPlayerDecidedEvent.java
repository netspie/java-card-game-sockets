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
public class FirstPlayerDecidedEvent extends CombatEvent {
    public final String EventId = "first-player-decided";
    public final String PlayerId;

    public FirstPlayerDecidedEvent(String combatId, String playerId) {
        super(combatId);
        PlayerId = playerId;
    }

    @Override
    public String toString() {
        return new StringJoiner("&")
                .add("combat-id=" + CombatId)
                .add("event-id=" + EventId)
                .add("player-id=" + PlayerId)
                .toString();
    }
}
