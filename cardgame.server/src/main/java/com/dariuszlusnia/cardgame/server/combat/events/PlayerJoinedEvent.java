/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.server.combat.events;

/**
 *
 * @author dariu
 */
public class PlayerJoinedEvent extends CombatEvent {
    public final String EventId = "player-joined";
    public final String PlayerId;

    public PlayerJoinedEvent(String playerId) {
        PlayerId = playerId;
    }
}
