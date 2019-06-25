package com.training.werewolf.controllers;

import com.training.werewolf.entities.Pager;
import com.training.werewolf.entities.Card;
import com.training.werewolf.repositories.CardRepository;
import com.training.werewolf.services.CardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardService cardService;

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 10;
    private static final int[] PAGE_SIZES = {5, 10, 20};

    @GetMapping(value = "")
    public String index(
            Model model,
            @RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page) throws Exception {
        // http://localhost:8081/admin/user?pageSize=10&page=2

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        // Page<User> users = userService.findAll(PageRequest.of(evalPage, evalPageSize));
        Page<Card> cards = cardService.findAll(PageRequest.of(evalPage, evalPageSize, Sort.by(Sort.Direction.DESC, "id")));
        Pager pager = new Pager(cards.getTotalPages(), cards.getNumber(), BUTTONS_TO_SHOW);

        model.addAttribute("items", cards);
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);

        return "sample/card";
    }

    @PostMapping(value = "/create")
    public String store(@Valid Card card,
                        BindingResult bindingResult,
                        @RequestParam Map<String,String> inputs,
                        RedirectAttributes redirAttrs,
                        Model model) throws Exception {

        cardService.save(inputs);
        redirAttrs.addFlashAttribute("success", "Card [" +
                inputs.get("name") + "] " +
                "created successfully.");
        return "redirect:/card";
    }

    @PutMapping(value="/{id}")
    public String update(@PathVariable String id,
                         @Valid Card card,
                         BindingResult bindingResult,
                         @RequestParam Map<String,String> inputs,
                         RedirectAttributes redirAttrs,
                         Model model) throws Exception {
        Optional<Card> obj = cardService.findById(Integer.parseInt(id));

        cardService.update(obj, inputs);
        redirAttrs.addFlashAttribute("success", "Card [" +
                inputs.get("name") + "] " +
                "updated successfully.");
        return "redirect:/card";
    }

    @DeleteMapping(value = "/{id}")
    public String destroy(@PathVariable String id, RedirectAttributes redirAttrs) throws Exception {

        Optional<Card> card = cardService.findById(Integer.parseInt(id));
        if (!card.isPresent()) {
            redirAttrs.addFlashAttribute("error", "Card ID : " + id + " not found.");
        } else {
            //Delete user by id
            cardService.deleteById(Integer.parseInt(id));
            redirAttrs.addFlashAttribute("success", "Card [" +
                    card.get().getName() + "] " +
                    "deleted successfully.");
        }
        return "redirect:/card";
    }


}

