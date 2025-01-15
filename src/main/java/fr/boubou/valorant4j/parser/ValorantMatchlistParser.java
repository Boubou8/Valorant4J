package fr.boubou.valorant4j.parser;

import com.fasterxml.jackson.databind.JsonNode;
import fr.boubou.valorant4j.model.match.MatchBase;

import java.util.List;

/**
 * @author Boubou
 * @date 16/11/2024 18:13
 */
public interface ValorantMatchlistParser {
    List<MatchBase> parse(JsonNode data) throws Exception;
}
