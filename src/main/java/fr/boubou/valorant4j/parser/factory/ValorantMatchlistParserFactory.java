package fr.boubou.valorant4j.parser.factory;

import fr.boubou.valorant4j.parser.ValorantMatchlistParser;
import fr.boubou.valorant4j.parser.matchlist.ValorantMatchlistV3Parser;
import fr.boubou.valorant4j.parser.matchlist.ValorantMatchlistV4Parser;
import fr.boubou.valorant4j.util.ApiVersion;

/**
 * @author Lubin "Boubou" B.
 * @date 16/11/2024 18:15
 */
public class ValorantMatchlistParserFactory {
    public static ValorantMatchlistParser getParser(ApiVersion version) {
        return switch (version) {
            case V3 -> new ValorantMatchlistV3Parser();
            case V4 -> new ValorantMatchlistV4Parser();
            default -> throw new UnsupportedOperationException("Version non supportée : " + version);
        };
    }
}
