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
                .isComplete(art.getIsComplete())
                .lastModified(art.getLastModified())
                .build();
    }

    @Override
    public FindSavedArtWithUserRTO artToFindWithUser(SavedArt art) {
        return FindSavedArtWithUserRTO.builder()
                .id(art.getId())
                .art(artSerializer.artToFind(art.getArt()))
                .user(userSerializer.userToFind(art.getUser()))
                .isComplete(art.getIsComplete())
                .lastModified(art.getLastModified())
                .build();
    }
}
