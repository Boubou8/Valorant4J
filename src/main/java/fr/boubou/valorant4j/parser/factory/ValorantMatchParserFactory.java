package fr.boubou.valorant4j.parser.factory;

import fr.boubou.valorant4j.parser.ValorantMatchParser;
import fr.boubou.valorant4j.parser.match.ValorantMatchV2Parser;
import fr.boubou.valorant4j.parser.match.ValorantMatchV4Parser;
import fr.boubou.valorant4j.util.ApiVersion;

/**
 * @author Boubou
 * @date 16/11/2024 18:15
 */
public class ValorantMatchParserFactory {
    public static ValorantMatchParser getParser(ApiVersion version) {
        return switch (version) {
            case V2 -> new ValorantMatchV2Parser();
            case V4 -> new ValorantMatchV4Parser();
            default -> throw new UnsupportedOperationException("Version non supportée : " + version);
        };
    }
}