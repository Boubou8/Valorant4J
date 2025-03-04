package fr.boubou.valorant4j.model;

import fr.boubou.valorant4j.model.mmr_history.MmrHistoryCombined;
import fr.boubou.valorant4j.model.mmr_history.MmrHistoryEntryBase;
import fr.boubou.valorant4j.model.mmr_history.MmrHistoryV1;
import fr.boubou.valorant4j.model.mmr_history.MmrHistoryV2;
import fr.boubou.valorant4j.model.mmr_history.v1.MmrHistoryV1Entry;
import fr.boubou.valorant4j.model.mmr_history.v2.MmrHistoryV2Entry;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Lubin "Boubou" B.
 * @date 31/01/2025 18:52
 */
public class ValorantMmrHistory {

    private final MmrHistoryV1 mmrV1;
    private final MmrHistoryV2 mmrV2;

    @Getter private boolean hasV1;
    @Getter private boolean hasV2;

    public ValorantMmrHistory(MmrHistoryV1 mmrV1, MmrHistoryV2 mmrV2) {
        this.mmrV1 = mmrV1;
        this.mmrV2 = mmrV2;
        this.hasV1 = (mmrV1 != null);
        this.hasV2 = (mmrV2 != null);
    }

    public MmrHistoryV1 getV1() {
        return mmrV1;
    }

    public MmrHistoryV2 getV2() {
        return mmrV2;
    }

    public List<MmrHistoryCombined> getHistoryList() {
        // Utiliser un LinkedHashMap pour garder un seul objet par match_id (évite les doublons)
        Map<String, MmrHistoryCombined> historyMap = new LinkedHashMap<>();

        if (hasV1) {
            mmrV1.getData().stream()
                    .map(entry -> convertEntryToHistory(entry, mmrV1.getName(), mmrV1.getTag()))
                    .forEach(combined -> historyMap.put(combined.getMatch_id(), combined));
        }

        if (hasV2) {
            mmrV2.getHistory().stream()
                    .map(entry -> convertEntryToHistory(entry, mmrV2.getAccount().getName(), mmrV2.getAccount().getTag()))
                    .forEach(combined -> historyMap.put(combined.getMatch_id(), combined));
        }

        // Transformer la map en liste et trier une seule fois
        List<MmrHistoryCombined> historyList = new ArrayList<>(historyMap.values());
        historyList.sort(Comparator.comparing(MmrHistoryCombined::getDateAsLocalDateTime).reversed());

        return historyList.isEmpty() ? Collections.emptyList() : historyList;
    }

    // Méthode générique pour convertir les entrées en MmrHistoryCombined
    private MmrHistoryCombined convertEntryToHistory(MmrHistoryEntryBase entry, String name, String tag) {
        MmrHistoryCombined combined = new MmrHistoryCombined();
        combined.setName(name);
        combined.setTag(tag);
        combined.setMatch_id(entry.getMatch_id());
        combined.setMap(entry.getMap());
        combined.setElo(entry.getElo());

        if (entry instanceof MmrHistoryV1Entry) {
            combined.setSeasonId(((MmrHistoryV1Entry) entry).getSeason_id());
            combined.setRr(((MmrHistoryV1Entry) entry).getRanking_in_tier());
            combined.setCurrentTier(((MmrHistoryV1Entry) entry).getCurrenttier());
            combined.setCurrentRank(((MmrHistoryV1Entry) entry).getCurrenttierpatched());
            combined.setLastChange(((MmrHistoryV1Entry) entry).getMmr_change_to_last_game());
        } else if (entry instanceof MmrHistoryV2Entry) {
            combined.setSeasonId(((MmrHistoryV2Entry) entry).getSeason().getId());
            combined.setRr(((MmrHistoryV2Entry) entry).getRr());
            combined.setCurrentTier(((MmrHistoryV2Entry) entry).getTier().getId());
            combined.setCurrentRank(((MmrHistoryV2Entry) entry).getTier().getName());
            combined.setLastChange(((MmrHistoryV2Entry) entry).getLast_change());
        }

        combined.setDate(entry.getDate());
        return combined;
    }
}