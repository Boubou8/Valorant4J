package fr.boubou.valorant4j.model.match.v2;

import lombok.Data;

/**
 * @author Lubin "Boubou" B.
 * @date 11/01/2025 18:23
 */
@Data
public class MatchPlayerPlatformV2 {
    private String type;
    private PlatformOSV2 os;

    @Data
    public static class PlatformOSV2 {
        private String name;
        private String version;
    }
}
