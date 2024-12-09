package surofu.pixelart.savedArt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import surofu.pixelart.art.ArtSerializer;
import surofu.pixelart.user.UserSerializer;

@Service
@RequiredArgsConstructor
public class SavedArtSerializerImpl implements SavedArtSerializer {
    private final ArtSerializer artSerializer;
    private final UserSerializer userSerializer;

    @Override
    public FindSavedArtRTO artToFind(SavedArt art) {
        return FindSavedArtRTO.builder()
                .id(art.getId())
                .art(artSerializer.artToFind(art.getArt()))
                .map(art.getMap())
                .isComplete(art.getIsComplete())
                .lastModified(art.getLastModified())
                .build();
    }

    @Override
    public UpdateSavedArtDTO compareUpdateWithArt(UpdateSavedArtDTO updateSavedArtDTO, SavedArt savedArt) {
        if (updateSavedArtDTO.getMap() == null) {
            updateSavedArtDTO.setMap(savedArt.getMap());
        }

        if (updateSavedArtDTO.getIsComplete() == null) {
            updateSavedArtDTO.setIsComplete(savedArt.getIsComplete());
        }

        return updateSavedArtDTO;
    }
}
