package fr.boubou.valorant4j.parser.account;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.boubou.valorant4j.model.account.AccountBase;
import fr.boubou.valorant4j.model.account.AccountV2;
import fr.boubou.valorant4j.parser.ValorantAccountParser;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author Lubin "Boubou" B.
 * @date 16/11/2024 18:14
 */
@Slf4j
public class ValorantAccountV2Parser implements ValorantAccountParser {

    @Override
    public AccountBase parse(JsonNode data) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.treeToValue(data, AccountV2.class);
    }
}
