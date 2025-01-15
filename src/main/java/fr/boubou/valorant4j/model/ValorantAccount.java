package fr.boubou.valorant4j.model;

import fr.boubou.valorant4j.model.account.AccountV1;
import fr.boubou.valorant4j.model.account.AccountV2;
import lombok.Getter;

/**
 * @author Boubou
 * @date 15/01/2025 08:54
 */


public class ValorantAccount {

    private final AccountV1 accountV1;
    private final AccountV2 accountV2;

    @Getter private boolean hasV1;
    @Getter private boolean hasV2;

    public ValorantAccount(AccountV1 accountV1, AccountV2 accountV2) {
        this.accountV1 = accountV1;
        this.accountV2 = accountV2;
        this.hasV1 = accountV1 != null;
        this.hasV2 = accountV2 != null;
    }

    public AccountV1 getV1() {
        return accountV1;
    }

    public AccountV2 getV2() {
        return accountV2;
    }

    public boolean hasV1() {
        return hasV1;
    }

    public boolean hasV2() {
        return hasV2;
    }

    public String getDisplayName() {
        return (hasV1) ? accountV1.getName() : (hasV2) ? accountV2.getName() : null;
    }

    public String getTag() {
        return (hasV1) ? accountV1.getTag() : (hasV2) ? accountV2.getTag() : null;
    }

    public String getPUUID() {
        return (hasV1) ? accountV1.getPuuid() : (hasV2) ? accountV2.getPuuid() : null;
    }

    public String getRegion() {
        return (hasV1) ? accountV1.getRegion() : (hasV2) ? accountV2.getRegion() : null;
    }

    public String getFullTag() {
        return (hasV1) ? accountV1.getName() + "#" + accountV1.getTag() : (hasV2) ? accountV2.getName() + "#" + accountV2.getTag() : null;
    }
}
