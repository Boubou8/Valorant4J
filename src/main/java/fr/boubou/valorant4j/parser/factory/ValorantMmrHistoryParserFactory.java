package fr.boubou.valorant4j.parser.factory;

import fr.boubou.valorant4j.parser.ValorantMatchlistParser;
import fr.boubou.valorant4j.parser.ValorantMmrHistoryParser;
import fr.boubou.valorant4j.parser.matchlist.ValorantMatchlistV3Parser;
import fr.boubou.valorant4j.parser.matchlist.ValorantMatchlistV4Parser;
import fr.boubou.valorant4j.parser.mmr_history.ValorantMmrHistoryV1Parser;
import fr.boubou.valorant4j.parser.mmr_history.ValorantMmrHistoryV2Parser;
import fr.boubou.valorant4j.util.ApiVersion;

/**
 * @author Lubin "Boubou" B.
 * @date 31/01/2025 18:39
 */
public class ValorantMmrHistoryParserFactory {
    public static ValorantMmrHistoryParser getParser(ApiVersion version) {
        return switch (version) {
            case V1 -> new ValorantMmrHistoryV1Parser();
            case V2 -> new ValorantMmrHistoryV2Parser();
            default -> throw new UnsupportedOperationException("Version non supportée : " + version);
        };
    }
}
