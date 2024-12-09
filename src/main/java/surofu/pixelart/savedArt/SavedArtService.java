package surofu.pixelart.savedArt;

import surofu.pixelart.user.UserNotFoundException;

import java.util.List;

public interface SavedArtService {
    List<FindSavedArtRTO> findAll();
    List<FindSavedArtRTO> findAllByUsername(String username);
    List<FindSavedArtRTO> findUncompleted();

    boolean isUserOwnedArt(String username, Long savedArtId) throws UserNotFoundException;

    void updateById(Long id, UpdateSavedArtDTO updateSavedArtDTO) throws SavedArtNotFoundException;
}
