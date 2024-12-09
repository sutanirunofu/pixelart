package surofu.pixelart.art;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindArtRTO implements Serializable {
    private Long id;
    private String title;
    private String map;
    private String colors;
    private ZonedDateTime publicationDate;
}
