package surofu.pixelart.art;

import java.util.List;
import java.util.Optional;

public interface ArtService {
    List<FindArtRTO> findAll();
    Optional<FindArtRTO> findById(Long id);
}
