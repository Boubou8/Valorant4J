package fr.boubou.valorant4j.model;

import fr.boubou.valorant4j.model.mmr.MmrV2;
import fr.boubou.valorant4j.model.mmr.MmrV3;
import lombok.Getter;

/**
 * @author Boubou
 * @date 14/01/2025 14:42
 */
public class ValorantMmr {

    private final MmrV2 mmrV2;
    private final MmrV3 mmrV3;

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

    public int getPeakRating() {
        if (hasV3) {
            assert mmrV3.getPeak() != null;
            return mmrV3.getPeak().getRr();
        } else {
            return -1;
        }
    }

    public boolean hasProtectionShield() {
        return hasV3 && mmrV3.getCurrent().getRank_protection_shields() > 0;
    }

    public int getProtectionShield() {
        return hasV3 ? mmrV3.getCurrent().getRank_protection_shields() : 0;
    }

    public int getPeakRankTier() {
        if (hasV3) {
            assert mmrV3.getPeak() != null;
            return mmrV3.getPeak().getRr();
        } else {
            return -1;
        }
    }

    public String getPeakRankFormatted() {
        return "%s / %dRR".formatted(getPeakRank(), getPeakRating());
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