package fr.boubou.valorant4j.model.match.v2;

import lombok.Data;

import java.util.List;

/**
 * @author Boubou
 * @date 10/01/2025 19:59
 */

@Data
public class MatchPlayersV2 {

    private List<MatchPlayerV2> all_players;
    private List<MatchPlayerV2> red;
    private List<MatchPlayerV2> blue;
}
