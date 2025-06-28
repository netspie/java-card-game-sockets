package com.dariuszlusnia.cardgame.server.features.cards;

import com.dariuszlusnia.cardgame.server.CollectionFunctions;
import com.dariuszlusnia.cardgame.server.features.combat.events.CombatEvent;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

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

    public String toString() {
        return new StringJoiner("&")
                .add("id=" + Id)
                .add("name=" + Name)
                .add("attack=" + Attack)
                .add("speed=" + Speed)
                .add("health=" + Health)
                .toString();
    }

    public static String cardsToString(List<Card> cards) {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(";"));
    }
}