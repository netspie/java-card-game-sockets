/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.server.features.combat.entities;

import com.dariuszlusnia.cardgame.server.features.combat.events.CombatEvent;

import java.util.*;

/**
 *
 * @author dariu
 */
public class CombatPlayer {
    public final String Id;
    public final List<CombatCard> Cards;
    
    public CombatPlayer(String id, List<CombatCard> cards) {
        this.Id = id;
        this.Cards = cards;
    }
    
    public List<CombatEvent> Attack(
        String attackingCardName,
        CombatPlayer defendingPlayer) {
        
        var attackingCard = getCardOfName(attackingCardName);
        if (attackingCard.isEmpty())
            return Collections.emptyList();

        return defendingPlayer.damageBy(attackingCard.get());
    }

    public int getCardsSpeedSum() {
        return Cards.stream()
            .mapToInt(CombatCard::getSpeed)
            .sum();
    }

    private List<CombatEvent> damageBy(CombatCard card) {
        var defendingCard = getRandomCard();
        return defendingCard.DamageBy(card);
    }

    private CombatCard getRandomCard() {
        var random = new Random();
        int index = random.nextInt(Cards.size());
        return Cards.get(index);
    }

    private Optional<CombatCard> getCardOfName(String name) {
        return this.Cards.stream()
            .filter(x -> x.getName().equals(name))
            .findFirst();
    }
}