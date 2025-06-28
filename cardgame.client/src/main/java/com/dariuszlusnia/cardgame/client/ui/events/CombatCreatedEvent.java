/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.client.ui.events;

import com.dariuszlusnia.cardgame.client.common.CollectionFunctions;

/**
 *
 * @author dariu
 */
public class CombatCreatedEvent extends CombatEvent {
    public static final String EventId = "combat-created";
    
    private CombatCreatedEvent(String combatId) {
        super(combatId);
    }

    public static CombatCreatedEvent fromString(String str) {
        var map = CollectionFunctions.parseToMap(str);
        return new CombatCreatedEvent(map.get("combat-id"));
    }
}
