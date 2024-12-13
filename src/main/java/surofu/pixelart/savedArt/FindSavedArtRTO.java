package surofu.pixelart.savedArt;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import surofu.pixelart.art.Art;
import surofu.pixelart.user.User;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindSavedArtRTO implements Serializable {
    private Long id;
    private User user;
    private Art art;
    private int[][] map;
    private boolean isComplete;
    private ZonedDateTime modifiedDate;
}
