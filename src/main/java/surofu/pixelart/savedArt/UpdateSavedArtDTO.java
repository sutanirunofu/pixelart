package surofu.pixelart.savedArt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSavedArtDTO implements Serializable {
    private int[][] map;
    private boolean isComplete;
}
