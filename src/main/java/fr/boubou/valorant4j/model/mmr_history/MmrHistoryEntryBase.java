package fr.boubou.valorant4j.model.mmr_history;

import fr.boubou.valorant4j.model.match.v4.MatchMetadataMapV4;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Lubin "Boubou" B.
 * @date 04/03/2025 19:46
 */

/**
 * Classe de base pour les entrées d'historique MMR V1 et V2.
 * Contient les champs communs pour éviter la duplication.
 * @author Lubin "Boubou" B.
 * @date 31/01/2025
 */

@Data
public abstract class MmrHistoryEntryBase {

    private String match_id;
    private MatchMetadataMapV4 map;
    private int elo;
    private String date;
}

