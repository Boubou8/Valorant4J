package fr.boubou.valorant4j.model.account;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Lubin "Boubou" B.
 * @date 16/11/2024 18:08
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountV2 extends AccountBase {

    private String card;
    private String title;
    private List<String> platforms;
    private String updated_at;
}
