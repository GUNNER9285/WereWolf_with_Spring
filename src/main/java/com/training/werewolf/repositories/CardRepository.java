package com.training.werewolf.repositories;

import com.training.werewolf.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardRepository extends JpaRepository<Card,Integer> {

}
