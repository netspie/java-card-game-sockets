/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.client.ui.events;

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
}