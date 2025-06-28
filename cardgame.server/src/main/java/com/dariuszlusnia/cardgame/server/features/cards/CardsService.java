package com.dariuszlusnia.cardgame.server.features.cards;

import java.util.List;
import java.util.UUID;

public final class CardsService {

    private final CardRepository cardRepository;

    public CardsService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> GetAllCards() {
        return this.cardRepository.getAll();
    }

    public void AddCard(String name, int attack, int speed, int health) {
        this.cardRepository.add(
            new Card(UUID.randomUUID().toString(), name, attack, speed, health));
    }

    public void UpdateCard(String id, String name, int attack, int speed, int health) {
        var cardOpt = this.cardRepository.get(id);
        if (cardOpt.isEmpty())
            return;

        var card = cardOpt.get();

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
