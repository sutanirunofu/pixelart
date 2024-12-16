package surofu.pixelart.savedArt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import surofu.pixelart.art.ArtSerializer;
import surofu.pixelart.user.UserSerializer;
import surofu.pixelart.utils.StringConverter;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class SavedArtSerializerImpl implements SavedArtSerializer {
    private final StringConverter converter;
    private final UserSerializer userSerializer;
    private final ArtSerializer artSerializer;

    @Override
    public FindSavedArtRTO artToFind(SavedArt art) {
        return FindSavedArtRTO.builder()
                .id(art.getId())
                .art(artSerializer.artToFind(art.getArt()))
                .user(userSerializer.userToFind(art.getUser()))
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
