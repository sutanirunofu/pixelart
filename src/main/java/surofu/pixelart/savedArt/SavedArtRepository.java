package surofu.pixelart.savedArt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SavedArtRepository extends JpaRepository<SavedArt, Long> {
    List<SavedArt> findAllByUserUsername(String username);

    @Query("FROM SavedArt s WHERE s.user.username = :username AND s.art.id = :id")
    Optional<SavedArt> findByUsernameAndArtId(String username, Long id);
}
