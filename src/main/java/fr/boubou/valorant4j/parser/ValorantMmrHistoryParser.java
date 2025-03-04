package fr.boubou.valorant4j.parser;

import com.fasterxml.jackson.databind.JsonNode;
import fr.boubou.valorant4j.model.mmr_history.MmrHistoryBase;

import java.util.List;

/**
 * @author Lubin "Boubou" B.
 * @date 31/01/2025 18:36
 */
public interface ValorantMmrHistoryParser {

    MmrHistoryBase parse(JsonNode data) throws Exception;
}
