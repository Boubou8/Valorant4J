package fr.boubou.valorant4j.model.match.v4;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author Boubou
 * @date 20/11/2024 16:46
 */
@Data
public class MatchMetadataV4 {

    private String match_id;
    private MetadataMapV4 map;
    private String game_version;
    private int game_length_in_ms;
    private String started_at;
    @JsonProperty("is_completed") private boolean isCompleted;
    private MetadataQueueV4 queue;
    private MetadataSeasonV4 season;
    private String platform;
    @Nullable private Object premier;
    private List<MetadataPartyRrPenaltysV4> party_rr_penaltys;
    private String region;
    private String cluster;

    @Data
    public static class MetadataMapV4 {
        private String id;
        private String name;
    }

    @Data
    public static class MetadataQueueV4 {
        private String id;
        private String name;
        private String mode_type;
    }

    @Data
    public static class MetadataSeasonV4 {
        private String id;
        @JsonProperty("short") private String shortz;
    }

    @Data
    public static class MetadataPartyRrPenaltysV4 {
        private String party_id;
        private int penalty;
    }
}
