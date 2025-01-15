package fr.boubou.valorant4j.parser;

import com.fasterxml.jackson.databind.JsonNode;
import fr.boubou.valorant4j.model.match.MatchBase;

/**
 * @author Lubin "Boubou" B.
 * @date 16/11/2024 18:13
 */
public interface ValorantMatchParser {
    MatchBase parse(JsonNode data) throws Exception;
}