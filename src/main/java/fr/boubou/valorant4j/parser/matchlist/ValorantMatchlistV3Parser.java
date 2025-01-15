package fr.boubou.valorant4j.parser.matchlist;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.boubou.valorant4j.model.match.MatchBase;
import fr.boubou.valorant4j.model.match.MatchV2;
import fr.boubou.valorant4j.parser.ValorantMatchlistParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Boubou
 * @date 16/11/2024 18:14
 */
public class ValorantMatchlistV3Parser implements ValorantMatchlistParser {

    @Override
    public List<MatchBase> parse(JsonNode data) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        List<MatchBase> matches = new ArrayList<>();

        for (JsonNode matchNode : data) {
            matches.add(mapper.treeToValue(matchNode, MatchV2.class));
        }

        return matches;
    }
}