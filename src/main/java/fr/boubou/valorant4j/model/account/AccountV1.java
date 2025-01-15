package fr.boubou.valorant4j.model.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Boubou
 * @date 16/11/2024 18:07
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountV1 extends AccountBase {

    private Card card;
    private String last_update;
    private int last_update_raw;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Card {

        private String small;
        private String large;
        private String wide;
        private String id;
    }
}
