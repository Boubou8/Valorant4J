package fr.boubou.valorant4j.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Boubou
 * @date 10/11/2024 22:33
 */
@Getter
@AllArgsConstructor
public enum ApiVersion {

    V1("v1"),
    V2("v2"),
    V3("v3"),
    V4("v4");

    private final String version;
}
