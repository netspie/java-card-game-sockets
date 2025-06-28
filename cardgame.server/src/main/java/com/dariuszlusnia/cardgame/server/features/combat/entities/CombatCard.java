/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.server.features.combat.entities;

import com.dariuszlusnia.cardgame.server.features.combat.events.CombatEvent;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dariu
 */
public final class CombatCard {
    public final String id;
    public final String originalId;
    private final String name;
    private int attack;
    private int speed;
    private int health;

    public CombatCard(String id, String originalId, String name, int attack, int speed, int health) {
        this.id = id;
        this.originalId = originalId;
        this.name = name;
        this.setAttack(attack);
        this.setSpeed(speed);
        this.setHealth(health);
    }

    public List<CombatEvent> DamageBy(CombatCard card) {
        var events = new ArrayList<CombatEvent>();

        this.setHealth(this.getHealth() - card.getAttack());

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