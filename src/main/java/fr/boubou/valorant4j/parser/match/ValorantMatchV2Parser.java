package fr.boubou.valorant4j.parser.match;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.boubou.valorant4j.model.match.MatchBase;
import fr.boubou.valorant4j.model.match.MatchV2;
import fr.boubou.valorant4j.parser.ValorantMatchParser;

import java.io.IOException;

/**
 * @author Boubou
 * @date 16/11/2024 18:14
 */
public class ValorantMatchV2Parser implements ValorantMatchParser {

    @Override
    public MatchBase parse(JsonNode data) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.treeToValue(data, MatchV2.class);
    }
}