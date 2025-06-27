/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.server.features.combat.events;

/**
 *
 * @author dariu
 */
public class FirstPlayerDecidedEvent extends CombatEvent {
    public final String EventId = "first-player-decided";
    public final String PlayerId;

    public FirstPlayerDecidedEvent(String playerId) {
        PlayerId = playerId;
    }
}
