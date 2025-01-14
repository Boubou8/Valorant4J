package dev.boubou.valorant4j.model;

import dev.boubou.valorant4j.model.mmr.MmrV2;
import dev.boubou.valorant4j.model.mmr.MmrV3;
import lombok.Getter;

/**
 * @author Lubin "Boubou" B.
 * @date 14/01/2025 14:42
 */
public class ValorantMmr {

    private MmrV2 mmrV2;
    private MmrV3 mmrV3;

    @Getter private boolean hasV2;
    @Getter private boolean hasV3;

    public ValorantMmr(MmrV2 mmrV2, MmrV3 mmrV3) {
        this.mmrV2 = mmrV2;
        this.mmrV3 = mmrV3;
        this.hasV2 = (mmrV2 != null);
        this.hasV3 = (mmrV3 != null);
    }

    public MmrV2 getV2() {
        return mmrV2;
    }

    public MmrV3 getV3() {
        return mmrV3;
    }

    public String getRank() {
        return (hasV2) ? mmrV2.getCurrent_data().getCurrenttierpatched() : (hasV3) ? mmrV3.getCurrent().getTier().getName() : null;
    }

    public int getRankTier() {
        return (hasV2) ? mmrV2.getCurrent_data().getCurrenttier() : (hasV3) ? mmrV3.getCurrent().getTier().getId() : -1;
    }

    public int getRankRating() {
        return (hasV2) ? mmrV2.getCurrent_data().getRanking_in_tier() : (hasV3) ? mmrV3.getCurrent().getRr() : -1;
    }

    public int getLastRatingChange() {
        return (hasV2) ? mmrV2.getCurrent_data().getMmr_change_to_last_game() : (hasV3) ? mmrV3.getCurrent().getLast_change() : -1;
    }

    public int getElo() {
        return (hasV2) ? mmrV2.getCurrent_data().getElo() : (hasV3) ? mmrV3.getCurrent().getElo() : -1;
    }

    public String getPeakRank() {
        if (hasV2) {
            return mmrV2.getHighest_rank().getPatched_tier();
        } else {
            if (hasV3) {
                assert mmrV3.getPeak() != null;
                return mmrV3.getPeak().getTier().getName();
            } else {
                return null;
            }
        }
    }

    public int getPeakRankTier() {
        if (hasV2) {
            return mmrV2.getHighest_rank().getTier();
        } else {
            if (hasV3) {
                assert mmrV3.getPeak() != null;
                return mmrV3.getPeak().getTier().getId();
            } else {
                return -1;
            }
        }
    }

    public String getRankFormatted() {
        return "%s / %dRR (%d)".formatted(getRank(), getRankRating(), getLastRatingChange());
    }

    public String getPlayerName() {
        return (hasV2) ? mmrV2.getName() : (hasV3) ? mmrV3.getAccount().getName() : null;
    }

    public String getPlayerTag() {
        return (hasV2) ? mmrV2.getTag() : (hasV3) ? mmrV3.getAccount().getTag() : null;
    }

    public String getPlayerFullName() {
        return (hasV2) ? mmrV2.getName() + "#" + mmrV2.getTag() : (hasV3) ? mmrV3.getAccount().getName() + "#" + mmrV3.getAccount().getTag() : null;
    }

    public String getPlayerUuid() {
        return (hasV2) ? mmrV2.getPuuid() : (hasV3) ? mmrV3.getAccount().getPuuid() : null;
    }
}