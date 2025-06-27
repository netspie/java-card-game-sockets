package com.dariuszlusnia.cardgame.server.features.cards;

import java.util.UUID;

public final class CardsService {

    private final CardRepository cardRepository;

    public CardsService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void AddCard(String name, int attack, int speed, int health) {
        this.cardRepository.add(
            new Card(UUID.randomUUID().toString(), name, attack, speed, health));
    }

    public void UpdateCard(String id, String name, int attack, int speed, int health) {
        var card = this.cardRepository.get(id);

        card.Name = name;
        card.Attack = attack;
        card.Speed = speed;
        card.Health = health;

        this.cardRepository.update(card);
    }

    public void RemoveCard(String id) {
        this.cardRepository.delete(id);
    }
}
