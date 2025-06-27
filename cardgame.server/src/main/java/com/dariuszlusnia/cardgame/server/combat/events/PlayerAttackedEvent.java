/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.server.combat.events;

/**
 *
 * @author dariu
 */
public class PlayerAttackedEvent extends CombatEvent {
    public final String EventId = "player-attacked";

    public final String AttackingPlayerId;
    public final String AttackingCardName;
    public final String DefendingPlayerId;
    public final String DefendingCardName;

    public PlayerAttackedEvent(
        String attackingPlayerId,
        String attackingCardName,
        String defendingPlayerId,
        String defendingCardName) {
        this.AttackingPlayerId = attackingPlayerId;
        this.AttackingCardName = attackingCardName;
        this.DefendingPlayerId = defendingPlayerId;
        this.DefendingCardName = defendingCardName;
    }
}