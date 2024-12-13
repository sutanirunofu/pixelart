package surofu.pixelart.savedArt;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedArtRepository extends JpaRepository<SavedArt, Long> {
    List<SavedArt> findAllByUserUsername(String username);
}
