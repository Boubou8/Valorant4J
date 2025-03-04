package fr.boubou.valorant4j.parser.mmr_history;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.boubou.valorant4j.model.match.MatchBase;
import fr.boubou.valorant4j.model.match.MatchV4;
import fr.boubou.valorant4j.model.mmr_history.MmrHistoryBase;
import fr.boubou.valorant4j.model.mmr_history.MmrHistoryV2;
import fr.boubou.valorant4j.parser.ValorantMatchlistParser;
import fr.boubou.valorant4j.parser.ValorantMmrHistoryParser;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Boubou
 * @date 16/11/2024 18:14
 */
public class ValorantMmrHistoryV2Parser implements ValorantMmrHistoryParser {

    @Override
    public MmrHistoryBase parse(@NotNull JsonNode data) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.treeToValue(data, MmrHistoryV2.class);
    }
}