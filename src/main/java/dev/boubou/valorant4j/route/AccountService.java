package dev.boubou.valorant4j.route;

import com.fasterxml.jackson.databind.JsonNode;
import dev.boubou.valorant4j.parser.ValorantAccountParser;
import dev.boubou.valorant4j.parser.factory.ValorantAccountParserFactory;
import dev.boubou.valorant4j.model.account.AccountBase;
import dev.boubou.valorant4j.util.ApiVersion;
import dev.boubou.valorant4j.util.Endpoint;
import dev.boubou.valorant4j.ValorantAPI;
import dev.boubou.valorant4j.exceptions.ApiException;
import dev.boubou.valorant4j.util.HttpService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Set;

/**
 * @author Lubin "Boubou" B.
 * @date 10/11/2024 20:08
 */

@Slf4j
public class AccountService extends Endpoint {

    private static final Set<ApiVersion> SUPPORTED_VERSIONS = Set.of(ApiVersion.V1, ApiVersion.V2);

    private final HttpService httpService;


    public AccountService(ValorantAPI api, ApiVersion version) {
        super(api, version, SUPPORTED_VERSIONS, "valorant/account");
        this.httpService = new HttpService(api.getMaxRequestsPerMinute(), api.isRateLimitEnabled());
    }

    public AccountBase fetchByNameTag(String name, String tag) throws ApiException {
        return fetchAccount(String.format("%s/account/%s/%s", version.getVersion(), name, tag));
    }

    public AccountBase fetchByPUuid(String puuid) throws ApiException {
        return fetchAccount(String.format("%s/by-puuid/account/%s", version.getVersion(), puuid));
    }

    private AccountBase fetchAccount(String endpoint) throws ApiException {
        final String url = buildUrl(endpoint);

        try {
            final JsonNode response = httpService.get(url, api.getApiKey());

            if (!response.has("data")) handleApiError(response);

            return parseAccount(response.get("data"));
        } catch (IOException e) {
            throw new ApiException("Erreur de parsing du compte : " + e.getMessage(), e);
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la récupération du compte : " + e.getMessage(), e);
        }
    }

    private AccountBase parseAccount(JsonNode data) throws Exception {
        final ValorantAccountParser parser = ValorantAccountParserFactory.getParser(version);
        return parser.parse(data);
    }
}