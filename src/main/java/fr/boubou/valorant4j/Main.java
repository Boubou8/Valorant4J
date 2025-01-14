package fr.boubou.valorant4j;

import fr.boubou.valorant4j.enums.MatchMode;
import fr.boubou.valorant4j.model.ValorantMatch;
import fr.boubou.valorant4j.model.ValorantMmr;
import fr.boubou.valorant4j.model.ValorantPlayer;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {

        final ValorantAPI valorantAPI = new ValorantAPI("HDEV-69a40ab8-56f2-4371-ac6e-ab94c70fe3f1");


        final ValorantMmr valorantMmr = valorantAPI.mmrRequestBuilder()
                .region("EU")
                .platform("PC")
                .name("CES Evoxia")
                .tag("TIT")
                .fetch(valorantAPI);

        final ValorantMmr valorantMmr1 = valorantAPI.fetchMmrByNameTag("EU","PC","CES Evoxia","TIT", null,null);

        log.info("{} - {}", valorantMmr.getPlayerFullName(), valorantMmr.getRankFormatted());

        log.info("================================================");

        final List<ValorantMatch> valorantMatches = valorantAPI.matchHistoryBuilder()
                        .region("EU")
                        .platform("PC")
                        .name("CES Evoxia")
                        .tag("TIT")
                        .size(20)
                        .mode(MatchMode.COMPETITIVE)
                        .fetch(valorantAPI);

        valorantMatches.forEach(match -> {
            log.info("Map: {}, Winner: {} ({})", match.getMapName(), match.getWinner(), match.getMatchId());
            final ValorantPlayer evoxia = match.fetchByPlayerNameTag("CES Evoxia", "TIT");
            if (evoxia == null) return;
            log.info(" - Evoxia data: K:{}, D:{}, A:{} // Agent: {} // Evoxia has won? {}", evoxia.getKills(), evoxia.getDeaths(), evoxia.getAssists(), evoxia.getAgent(), evoxia.hasWon() ? "Oui" : "Non");
        });

        log.info("================================================");

        final ValorantMatch valorantMatch = valorantAPI.fetchMatch("db650992-9e5a-4b9d-b521-0673d5c012d3");
        final ValorantPlayer evoxia = valorantMatch.fetchByPlayerNameTag("CES Evoxia", "TIT");
        if (evoxia == null) return;
        log.info("Player : {} ({})", evoxia.getFullName(), evoxia.getUuid());
        log.info("Match winner: {} (Blue: {}, Red: {})", valorantMatch.getWinner(), valorantMatch.getBlueScore(), valorantMatch.getRedScore());
        log.info("Evoxia win ? {} (team: {})", evoxia.hasWon(), evoxia.getTeam());
        log.info("Agent: {}", evoxia.getAgent());
        log.info("- K: {}, D: {}, A: {}, HS: {}, BS: {}, LG: {}", evoxia.getKills(), evoxia.getDeaths(), evoxia.getAssists(), evoxia.getHeadshots(), evoxia.getBodyshots(), evoxia.getLegshots());

        /*


        final ValorantAccountV1 valorantAccount = (ValorantAccountV1) accountService.fetchByNameTag("Boubou", "BOU");

        final ValorantMatchV4 valorantMatch = (ValorantMatchV4) matchService.fetchByMatchId("0f1fd962-483e-456c-8267-65976fc62294");

        final List<ValorantMatchBase> valorantMatches = valorantAPI.getMatchlist().fetchByNameTag("EU", "PC", "Boubou", "BOU");

        final ValorantMatch valorantMatch1 = valorantAPI.fetchMatch("f7836587-d6f1-469d-b8d9-21c83caf8dbd");
        final ValorantMatch valorantMatch2 = valorantAPI.fetchMatch("9b4a7cf0-b04c-4419-92df-7273dbde7e92");
        final ValorantMatch valorantMatch3 = valorantAPI.fetchMatch("dcd4dcaa-ffe1-4134-82c8-21958e684a40");
        final ValorantMatch valorantMatch4 = valorantAPI.fetchMatch("997673a5-bed4-4b87-b001-d67b42eb2102");
        final ValorantMatch valorantMatch5 = valorantAPI.fetchMatch("e3e38524-6d30-4389-80c5-cc92bd43157a");

        log.info("Match size : {}", valorantMatches.size());

        valorantMatches.forEach(match -> {
            final ValorantMatchV4 valorantMatchV4 = (ValorantMatchV4) match;
            log.info("Map : {}", valorantMatchV4.getMetadata().getMap().getName());
        });

        //log.info("Match : {}", valorantMatch);
        
        //final ValorantMatch valorantMatch = matchService.fetchByMatchId("0f1fd962-483e-456c-8267-65976fc62294");
        //log.info("Match : {}", valorantMatch);
        */
    }
}