package fr.boubou.valorant4j.util;

import lombok.Getter;

/**
 * @author Lubin "Boubou" B.
 * @date 10/11/2024 22:33
 */
@Getter
public enum ApiVersion {

    V1("v1"),
    V2("v2"),
    V3("v3"),
    V4("v4");

    private final String version;

    ApiVersion(String version) {
        this.version = version;
    }
}
