package fr.boubou.valorant4j.parser.mmr;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.boubou.valorant4j.model.mmr.MmrBase;
import fr.boubou.valorant4j.model.mmr.MmrV2;
import fr.boubou.valorant4j.parser.ValorantMmrParser;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author Boubou
 * @date 16/11/2024 18:14
 */
@Slf4j
public class ValorantMmrV2Parser implements ValorantMmrParser {

    @Override
    public MmrBase parse(JsonNode data) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.treeToValue(data, MmrV2.class);
    }
}