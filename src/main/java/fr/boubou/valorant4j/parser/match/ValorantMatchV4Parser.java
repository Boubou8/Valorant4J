package fr.boubou.valorant4j.parser.match;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.boubou.valorant4j.model.match.MatchBase;
import fr.boubou.valorant4j.model.match.MatchV4;
import fr.boubou.valorant4j.parser.ValorantMatchParser;
import fr.boubou.valorant4j.util.ObjectMapperProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author Boubou
 * @date 16/11/2024 18:14
 */
@Slf4j
public class ValorantMatchV4Parser implements ValorantMatchParser {

    @Override
    public MatchBase parse(JsonNode data) throws IOException {
        final ObjectMapper mapper = ObjectMapperProvider.getObjectMapper();
        return mapper.treeToValue(data, MatchV4.class);
    }
}
