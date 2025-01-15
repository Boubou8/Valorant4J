package fr.boubou.valorant4j.parser.factory;

import fr.boubou.valorant4j.parser.account.ValorantAccountV1Parser;
import fr.boubou.valorant4j.parser.account.ValorantAccountV2Parser;
import fr.boubou.valorant4j.parser.ValorantAccountParser;
import fr.boubou.valorant4j.util.ApiVersion;

/**
 * @author Boubou
 * @date 16/11/2024 18:15
 */
public class ValorantAccountParserFactory {
    public static ValorantAccountParser getParser(ApiVersion version) {
        return switch (version) {
            case V1 -> new ValorantAccountV1Parser();
            case V2 -> new ValorantAccountV2Parser();
            default -> throw new UnsupportedOperationException("Version non supportée : " + version);
        };
    }
}
