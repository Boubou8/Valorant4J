package dev.boubou.valorant4j.parser.match;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.boubou.valorant4j.model.match.MatchBase;
import dev.boubou.valorant4j.model.match.MatchV4;
import dev.boubou.valorant4j.parser.ValorantMatchParser;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author Lubin "Boubou" B.
 * @date 16/11/2024 18:14
 */
@Slf4j
public class ValorantMatchV4Parser implements ValorantMatchParser {

    @Override
    public MatchBase parse(JsonNode data) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.treeToValue(data, MatchV4.class);
    }
}
