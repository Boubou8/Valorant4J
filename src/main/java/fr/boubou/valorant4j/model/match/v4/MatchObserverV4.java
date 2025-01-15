package fr.boubou.valorant4j.model.match.v4;

import lombok.Data;

/**
 * @author Boubou
 * @date 20/11/2024 17:42
 */

@Data
public class MatchObserverV4 {

    private String puuid;
    private String name;
    private String tag;
    private int account_level;
    private int session_playtime_in_ms;
    private String card_id;
    private String title_id;
    private String party_id;
}
