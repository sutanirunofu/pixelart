package surofu.pixelart.savedArt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import surofu.pixelart.art.FindArtRTO;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindSavedArtRTO implements Serializable {
    private Long id;
    private String map;
    private Boolean isComplete;
    private FindArtRTO art;
    private ZonedDateTime lastModified;
}
