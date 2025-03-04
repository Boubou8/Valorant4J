package fr.boubou.valorant4j.model.mmr_history;

import fr.boubou.valorant4j.model.match.v4.MatchMetadataMapV4;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Lubin "Boubou" B.
 * @date 03/03/2025 00:46
 */

@Slf4j
@Getter
@Setter
public class MmrHistoryCombined {

    private String name;
    private String tag;

    private String match_id;
    private MatchMetadataMapV4 map;


    private int currentTier;
    private String currentRank;

    private String seasonId;

    private int rr;
    private int lastChange;
    private int elo;
    private String date;

    public LocalDateTime getDateAsLocalDateTime() {
        if (date == null || date.isEmpty()) {
            return LocalDateTime.MIN; // Valeur minimale pour éviter les erreurs
        }

        try {
            // Essai de parser le format ISO 8601
            return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
        } catch (Exception ignored) {}

        try {
            // Essai de parser le format "Monday, March 03, 2025 10:18 PM"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy hh:mm a", Locale.ENGLISH);
            return LocalDateTime.parse(date, formatter);
        } catch (Exception e) {
            return LocalDateTime.MIN; // Valeur minimale pour éviter les erreurs
        }
    }
}
