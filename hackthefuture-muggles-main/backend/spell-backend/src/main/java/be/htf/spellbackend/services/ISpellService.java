package be.htf.spellbackend.services;

import be.htf.spellbackend.domain.dto.QuestDto;

public interface ISpellService {
    void setQuest(QuestDto quest);
    String findFormula(String spellId, String ingredient);
}
