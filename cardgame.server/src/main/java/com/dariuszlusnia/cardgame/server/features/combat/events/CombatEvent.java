/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.server.features.combat.events;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author dariu
 */
public abstract class CombatEvent {
    public final String CombatId;

    protected CombatEvent(String combatId) {
        CombatId = combatId;
    }

    public abstract String toString();

    public static String eventsToString(List<CombatEvent> events) {
        return events.stream()
            .map(CombatEvent::toString)
            .collect(Collectors.joining(";"));
    }
}