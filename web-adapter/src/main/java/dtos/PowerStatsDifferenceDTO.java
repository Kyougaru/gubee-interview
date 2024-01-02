package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PowerStatsDifferenceDTO {
    private Integer strength;
    private Integer agility;
    private Integer dexterity;
    private Integer intelligence;
}
