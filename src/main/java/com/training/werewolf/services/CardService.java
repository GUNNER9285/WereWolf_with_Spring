package com.training.werewolf.services;

import com.training.werewolf.entities.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.*;

public interface CardService {
    Card save(Map<String,String> inputs) throws Exception;

    Page<Card> findAll(PageRequest pageRequest);

    Optional<Card> findById(Integer id);

    Card update(Optional<Card> card, Map<String,String> inputs) throws Exception;

    void deleteById(Integer id) throws Exception;
}
