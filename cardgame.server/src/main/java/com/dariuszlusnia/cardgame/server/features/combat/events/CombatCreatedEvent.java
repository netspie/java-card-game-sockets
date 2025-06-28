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
public class CombatCreatedEvent extends CombatEvent {
    public final String EventId = "combat-created";

    public CombatCreatedEvent(String combatId) {
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
