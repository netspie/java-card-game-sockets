package com.dariuszlusnia.cardgame.server.features.combat.useCases;

import com.dariuszlusnia.cardgame.server.features.cards.Card;
import com.dariuszlusnia.cardgame.server.features.cards.CardRepository;
import com.dariuszlusnia.cardgame.server.features.combat.entities.Combat;
import com.dariuszlusnia.cardgame.server.features.combat.entities.CombatCard;
import com.dariuszlusnia.cardgame.server.features.combat.entities.CombatRepository;
import com.dariuszlusnia.cardgame.server.features.combat.entities.CombatPlayer;
import com.dariuszlusnia.cardgame.server.features.combat.events.CombatEvent;

import java.util.*;

public class JoinRandomCombatCommandHandler {

    public static final int PlayerCardCount = 5;

    private final CombatRepository combatRepository;
    private final CardRepository cardRepository;
    private final CombatEventsPublisher publisher;

    public JoinRandomCombatCommandHandler(
        CombatRepository combatRepository,
        CardRepository cardRepository,
        CombatEventsPublisher publisher) {
        this.combatRepository = combatRepository;
        this.cardRepository = cardRepository;
        this.publisher = publisher;
    }

    public void handle(JoinRandomCombatCommand command) {
        var combatOpt = this.combatRepository.getFree();
        if (combatOpt.isEmpty())
            combatOpt = Optional.of(new Combat(UUID.randomUUID().toString()));

        var combat = combatOpt.get();

        var cards = this.cardRepository.getAll();
        if (cards.size() < PlayerCardCount)
            return;

        Collections.shuffle(cards);
        var cardsShuffled = cards.stream().limit(PlayerCardCount).toList();
        var combatCards = cardsToCombatCards(cardsShuffled);

        var player = new CombatPlayer(UUID.randomUUID().toString(), combatCards);
        var events = combat.AddPlayer(player);

        publisher.publish(combat, events);
    }

    private static List<CombatCard> cardsToCombatCards(List<Card> cards) {
        return  cards.stream().map(JoinRandomCombatCommandHandler::cardToCombatCard).toList();
    }

    private static CombatCard cardToCombatCard(Card card) {
        return new CombatCard(
            UUID.randomUUID().toString(),
            card.Id,
            card.Name,
            card.Attack,
            card.Speed,
            card.Health
        );
    }
}
















