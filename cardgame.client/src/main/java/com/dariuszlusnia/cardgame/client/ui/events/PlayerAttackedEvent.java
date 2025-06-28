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
public class PlayerAttackedEvent extends CombatEvent {
    public static final String EventId = "player-attacked";
    
    public final String AttackingPlayerId;
    public final String AttackingCardId;
    public final String DefendingPlayerId;
    public final String DefendingCardId;
    public final int Damage;

    public PlayerAttackedEvent(
            String combatId,
            String attackingPlayerId,
            String attackingCardName,
            String defendingPlayerId,
            String defendingCardName, 
            int damage) {
        super(combatId);
        this.AttackingPlayerId = attackingPlayerId;
        this.AttackingCardId = attackingCardName;
        this.DefendingPlayerId = defendingPlayerId;
        this.DefendingCardId = defendingCardName;
        Damage = damage;
    }

    public static PlayerAttackedEvent fromString(String str) {
        var map = CollectionFunctions.parseToMap(str);
        return new PlayerAttackedEvent(
            map.get("combat-id"),             
            map.get("attacking-player-id"), 
            map.get("attacking-card-id"),
            map.get("defending-player-id"),
            map.get("defending-card-id"),
            Integer.parseInt(map.get("damage")));
    }
}