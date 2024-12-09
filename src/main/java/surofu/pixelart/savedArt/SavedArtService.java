package surofu.pixelart.savedArt;

import java.util.List;

public interface SavedArtService {
    List<FindSavedArtRTO> findAll();
    List<FindSavedArtRTO> findAllByUsername(String username);
    List<FindSavedArtRTO> findUncompleted();
}
