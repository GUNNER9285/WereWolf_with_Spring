package com.training.werewolf.services;

import com.training.werewolf.entities.Card;
import com.training.werewolf.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ExecutorService;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Page<Card> findAll(PageRequest pageRequest) {
        Page<Card> cards = cardRepository.findAll(pageRequest);
        return cards;
    }

    @Override
    public Card save(Map<String,String> inputs) throws Exception {
        try {
            Card card = new Card();
            card.setName(inputs.get("name"));
            card.setEffect(inputs.get("effect"));
            card.setPoint(Integer.parseInt(inputs.get("point")));
            card.setImage(inputs.get("image"));
            return cardRepository.save(card);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public Optional<Card> findById(Integer id) {
        return cardRepository.findById(id);
    }

    @Override
    public Card update(Optional<Card> card, Map<String,String> inputs) throws Exception {
        try {
            card.get().setName(inputs.get("name"));
            card.get().setEffect(inputs.get("effect"));
            card.get().setPoint(Integer.parseInt(inputs.get("point")));
            card.get().setImage(inputs.get("image"));
            return cardRepository.save(card.get());
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        cardRepository.deleteById(id);
    }
}
