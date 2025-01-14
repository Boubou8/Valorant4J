package dev.boubou.valorant4j.parser.factory;

import dev.boubou.valorant4j.parser.ValorantMmrParser;
import dev.boubou.valorant4j.parser.mmr.ValorantMmrV2Parser;
import dev.boubou.valorant4j.parser.mmr.ValorantMmrV3Parser;
import dev.boubou.valorant4j.util.ApiVersion;

/**
 * @author Lubin "Boubou" B.
 * @date 16/11/2024 18:15
 */
public class ValorantMmrParserFactory {
    public static ValorantMmrParser getParser(ApiVersion version) {
        return switch (version) {
            case V2 -> new ValorantMmrV2Parser();
            case V3 -> new ValorantMmrV3Parser();
            default -> throw new UnsupportedOperationException("Version non supportée : " + version);
        };
    }
}