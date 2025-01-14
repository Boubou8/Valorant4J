package dev.boubou.valorant4j.parser.account;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.boubou.valorant4j.model.account.AccountBase;
import dev.boubou.valorant4j.model.account.AccountV1;
import dev.boubou.valorant4j.parser.ValorantAccountParser;

import java.io.IOException;

/**
 * @author Lubin "Boubou" B.
 * @date 16/11/2024 18:14
 */
public class ValorantAccountV1Parser implements ValorantAccountParser {

    @Override
    public AccountBase parse(JsonNode data) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.treeToValue(data, AccountV1.class);
    }
}
