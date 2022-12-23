package be.htf.spellbackend.domain.dto;

import lombok.Data;

@Data
public class PrivateSpellDto {
    private int difficulty;
    private String effect;
    private String id;
    private String ingredients;
    private String name;
    private String recipe;
    private String remainingAttempts;
    private boolean solved;
}
