package be.htf.spellbackend.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestDto {
    private boolean finished;
    private List<PrivateProblemDto> problems;
}
