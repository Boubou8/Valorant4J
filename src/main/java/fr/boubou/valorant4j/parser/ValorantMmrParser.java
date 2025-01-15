package fr.boubou.valorant4j.parser;

import com.fasterxml.jackson.databind.JsonNode;
import fr.boubou.valorant4j.model.mmr.MmrBase;

/**
 * @author Boubou
 * @date 16/11/2024 18:13
 */
public interface ValorantMmrParser {
    MmrBase parse(JsonNode data) throws Exception;
}