package fr.boubou.valorant4j.model.match;

import fr.boubou.valorant4j.model.match.v4.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

/**
 * @author Boubou
 * @date 16/11/2024 19:23
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MatchV4 extends MatchBase {

    private MatchMetadataV4 metadata;
    private List<MatchPlayerV4> players;
    private List<MatchObserverV4> observers;
    private List<MatchCoachV4> coaches;
    private List<MatchTeamV4> teams;
    private List<MatchRoundV4> rounds;
    private List<MatchKillV4> kills;
}
