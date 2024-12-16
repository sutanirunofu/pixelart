package surofu.pixelart.savedArt;

import surofu.pixelart.art.ArtNotFoundException;
import surofu.pixelart.user.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface SavedArtService {
    List<FindSavedArtRTO> findAll(String username);
    Optional<FindSavedArtRTO> findById(String username, Long artId);
    void save(String username, Long artId) throws UserNotFoundException, ArtNotFoundException;
    FindSavedArtRTO updateByArtId(String username, Long artId, UpdateSavedArtDTO updateSavedArtDTO) throws SavedArtNotFoundException;
}
