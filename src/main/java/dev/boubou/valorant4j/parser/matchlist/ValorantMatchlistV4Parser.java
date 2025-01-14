package dev.boubou.valorant4j.parser.matchlist;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.boubou.valorant4j.model.match.MatchBase;
import dev.boubou.valorant4j.model.match.MatchV4;
import dev.boubou.valorant4j.parser.ValorantMatchlistParser;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lubin "Boubou" B.
 * @date 16/11/2024 18:14
 */
@Slf4j
public class ValorantMatchlistV4Parser implements ValorantMatchlistParser {

    @Override
    public List<MatchBase> parse(JsonNode data) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        List<MatchBase> matches = new ArrayList<>();

        for (JsonNode matchNode : data) {
            matches.add(mapper.treeToValue(matchNode, MatchV4.class));
        }

        return matches;
    }
}