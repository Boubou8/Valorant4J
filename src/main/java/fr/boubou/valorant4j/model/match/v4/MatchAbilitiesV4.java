package fr.boubou.valorant4j.model.match.v4;

import lombok.Data;

import javax.annotation.Nullable;

/**
 * @author Boubou
 * @date 20/11/2024 18:18
 */

@Data
public class MatchAbilitiesV4 {

    @Nullable private Integer grenade;
    @Nullable private Integer ability1;
    @Nullable private Integer ability_1;
    @Nullable private Integer ability2;
    @Nullable private Integer ability_2;
    @Nullable private Integer ultimate;
}
