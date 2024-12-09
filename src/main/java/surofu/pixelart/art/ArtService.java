package surofu.pixelart.art;

import java.util.List;

public interface ArtService {
    List<FindArtRTO> findAll();
    List<FindArtRTO> findWithStar(Long userId);
}
