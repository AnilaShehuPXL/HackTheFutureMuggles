package be.htf.spellbackend.controllers;

import be.htf.spellbackend.domain.dto.QuestDto;
import be.htf.spellbackend.services.ISpellService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/spellsolver")
@CrossOrigin
public class SpellController {

    private final ISpellService spellService;

    @PostMapping("/{spellId}")
    public ResponseEntity<String> solveSpell(@PathVariable String spellId, @RequestBody String ingredient) {
        String response = spellService.findFormula(spellId,ingredient);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/setquest")
    public ResponseEntity<HttpStatus> setQuest(@RequestBody QuestDto quest) {
        spellService.setQuest(quest);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
