package surofu.pixelart.savedArt;

import org.springframework.stereotype.Service;
import surofu.pixelart.utils.StringConverter;

import java.util.Arrays;

@Service
public class SavedArtSerializerImpl implements SavedArtSerializer {
    private final StringConverter converter = new StringConverter();

    @Override
    public FindSavedArtRTO artToFind(SavedArt art) {
        return FindSavedArtRTO.builder()
                .id(art.getId())
                .art(art.getArt())
                .user(art.getUser())
                .map(converter.stringToMatrix(art.getMap()))
                .isComplete(art.isComplete())
                .build();
    }

    @Override
    public SavedArt compareArtWithUpdate(SavedArt art, UpdateSavedArtDTO updateSavedArtDTO) {
        art.setComplete(updateSavedArtDTO.isComplete());

        if (updateSavedArtDTO.getMap() != null) {
            art.setMap(Arrays.deepToString(updateSavedArtDTO.getMap()));
        }

        return art;
    }
}
