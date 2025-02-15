package fr.boubou.valorant4j.parser.mmr;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.boubou.valorant4j.model.mmr.MmrBase;
import fr.boubou.valorant4j.model.mmr.MmrV3;
import fr.boubou.valorant4j.parser.ValorantMmrParser;

import java.io.IOException;

/**
 * @author Boubou
 * @date 16/11/2024 18:14
 */
public class ValorantMmrV3Parser implements ValorantMmrParser {

    @Override
    public MmrBase parse(JsonNode data) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.treeToValue(data, MmrV3.class);
    }
}