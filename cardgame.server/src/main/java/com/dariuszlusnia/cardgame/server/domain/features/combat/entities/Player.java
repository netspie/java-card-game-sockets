/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.server.domain.features.combat.entities;

import com.dariuszlusnia.cardgame.server.combat.events.CombatEvent;

import java.util.*;

/**
 *
 * @author dariu
 */
public class Player {
    public final String Id;
    public final List<Card> Cards;
    
    public Player(String id, List<Card> cards) {
        this.Id = id;
        this.Cards = cards;
    }
    
    public List<CombatEvent> Attack(
        String attackingCardName,
        Player defendingPlayer) {
        
        var attackingCard = getCardOfName(attackingCardName);
        if (attackingCard.isEmpty())
            return Collections.emptyList();

        return defendingPlayer.damageBy(attackingCard.get());
    }

    public int getCardsSpeedSum() {
        return Cards.stream()
            .mapToInt(Card::getSpeed)
            .sum();
    }

    private List<CombatEvent> damageBy(Card card) {
        var defendingCard = getRandomCard();
        return defendingCard.DamageBy(card);
    }

    private Card getRandomCard() {
        var random = new Random();
        int index = random.nextInt(Cards.size());
        return Cards.get(index);
    }

    private Optional<Card> getCardOfName(String name) {
        return this.Cards.stream()
            .filter(x -> x.getName().equals(name))
            .findFirst();
    }
}