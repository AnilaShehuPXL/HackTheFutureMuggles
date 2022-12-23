package be.htf.spellbackend.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class PrivateProblemDto {
    private String badgeUrl;
    private String description;
    private String name;
    private int score;
    private boolean Solved;
    private List<PrivateSpellDto> spells;
}
