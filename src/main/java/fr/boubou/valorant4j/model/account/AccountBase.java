package fr.boubou.valorant4j.model.account;

import lombok.Data;

/**
 * @author Lubin "Boubou" B.
 * @date 16/11/2024 18:07
 */

@Data
public class AccountBase {

    private String puuid;
    private String region;
    private int account_level;
    private String name;
    private String tag;
}
