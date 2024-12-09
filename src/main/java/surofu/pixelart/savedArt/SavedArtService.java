package surofu.pixelart.savedArt;

import surofu.pixelart.art.ArtNotFoundException;
import surofu.pixelart.user.UserNotFoundException;

import java.util.List;

public interface SavedArtService {
    boolean isUserOwnedArt(String username, Long savedArtId) throws UserNotFoundException;

    List<FindSavedArtRTO> findAllByUsername(String username);

    void save(Long id, String username) throws ArtNotFoundException, UserNotFoundException;

    void updateById(Long id, UpdateSavedArtDTO updateSavedArtDTO) throws SavedArtNotFoundException;
}
