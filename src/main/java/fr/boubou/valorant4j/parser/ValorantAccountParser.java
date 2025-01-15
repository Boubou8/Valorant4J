package fr.boubou.valorant4j.parser;

import com.fasterxml.jackson.databind.JsonNode;
import fr.boubou.valorant4j.model.account.AccountBase;

/**
 * @author Boubou
 * @date 16/11/2024 18:13
 */
public interface ValorantAccountParser {
    AccountBase parse(JsonNode data) throws Exception;
}
