/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.server.domain.features.combat.entities;

import com.dariuszlusnia.cardgame.server.combat.events.CardDamagedEvent;
import com.dariuszlusnia.cardgame.server.combat.events.CombatEvent;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dariu
 */
public final class Card {
    private final String name;
    private int attack;
    private int speed;
    private int health;

    public Card(String name, int attack, int speed, int health) {
        this.name = name;
        this.setAttack(attack);
        this.setSpeed(speed);
        this.setHealth(health);
    }

    public List<CombatEvent> DamageBy(Card card) {
        var events = new ArrayList<CombatEvent>();

        this.setHealth(this.getHealth() - card.getAttack());
        events.add(new CardDamagedEvent());

        if (this.getHealth() < 0)
            this.setHealth(0);

        return events;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}