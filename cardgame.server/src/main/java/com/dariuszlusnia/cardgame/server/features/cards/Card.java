package com.dariuszlusnia.cardgame.server.features.cards;

public class Card {
    public final String Id;
    public String Name;
    public int Attack;
    public int Speed;
    public int Health;

    public Card(String id, String name, int attack, int speed, int health) {
        this.Id = id;
        this.Name = name;
        this.Attack = attack;
        this.Speed = speed;
        this.Health = health;
    }

    public Card() {
        this.Id = "";
        this.Name = "";
        this.Attack = 0;
        this.Speed = 0;
        this.Health = 0;
    }
}