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
public class FindSavedArtRTO implements Serializable {
    private Long id;
    private FindUserRTO user;
    private FindArtRTO art;
    private int[][] map;
    private boolean isComplete;
    private ZonedDateTime modifiedDate;
}
