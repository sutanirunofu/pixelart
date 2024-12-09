package surofu.pixelart.savedArt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import surofu.pixelart.art.FindArtRTO;
import surofu.pixelart.user.FindUserRTO;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindSavedArtWithUserRTO implements Serializable {
    private Long id;
    private String map;
    private Boolean isComplete;
    private FindArtRTO art;
    private FindUserRTO user;
    private ZonedDateTime lastModified;
}
