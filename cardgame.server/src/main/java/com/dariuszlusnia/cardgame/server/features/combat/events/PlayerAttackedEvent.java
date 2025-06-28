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
public class PlayerAttackedEvent extends CombatEvent {
    public final String EventId = "player-attacked";

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
            String defendingCardName, int damage) {
        super(combatId);
        this.AttackingPlayerId = attackingPlayerId;
        this.AttackingCardId = attackingCardName;
        this.DefendingPlayerId = defendingPlayerId;
        this.DefendingCardId = defendingCardName;
        Damage = damage;
    }

    @Override
    public String toString() {
        return new StringJoiner("&")
                .add("combat-id=" + CombatId)
                .add("event-id=" + EventId)
                .add("attacking-player-id=" + AttackingPlayerId)
                .add("attacking-card-id=" + AttackingCardId)
                .add("defending-player-id=" + DefendingPlayerId)
                .add("attacking-player-id=" + DefendingCardId)
                .add("damage=" + Damage)
                .toString();
    }
}