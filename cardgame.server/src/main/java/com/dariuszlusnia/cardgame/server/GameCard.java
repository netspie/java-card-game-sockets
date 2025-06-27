/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.server;

/**
 *
 * @author dariu
 */
public class GameCard {
    public final String name;
    public int attack;
    public int speed;
    public int health;

    public GameCard(String name, int attack, int speed, int health) {
        this.name = name;
        this.attack = attack;
        this.speed = speed;
        this.health = health;
    }
}
