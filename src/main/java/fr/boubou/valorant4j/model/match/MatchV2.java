package fr.boubou.valorant4j.model.match;

import fr.boubou.valorant4j.model.match.v2.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Lubin "Boubou" B.
 * @date 16/11/2024 19:23
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MatchV2 extends MatchBase {

    private MatchMetadataV2 metadata;
    private MatchPlayersV2 players;
    private List<MatchObserverV2> observers;
    private List<MatchCoachV2> coaches;
    private MatchTeamsV2 teams;
    private List<MatchRoundV2> rounds;
    private List<MatchPlayerKillEventV2> kills;
}
