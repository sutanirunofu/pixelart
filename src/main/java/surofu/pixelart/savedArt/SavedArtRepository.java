package surofu.pixelart.savedArt;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface SavedArtRepository extends JpaRepository<SavedArt, Long> {
    @Query("SELECT new surofu.pixelart.savedArt.SavedArt(sa.id, sa.map, sa.isComplete, sa.art, sa.lastModified) " +
            "FROM SavedArt sa JOIN sa.user u WHERE u.id = :id")
    List<SavedArt> findAllByUserId(@Param("id") Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE SavedArt art SET art.map = :#{#dto.map}, art.isComplete = :#{#dto.isComplete}, art.lastModified = :zonedDateTime WHERE art.id = :id")
    void updateById(Long id, @Param("dto") UpdateSavedArtDTO updateSavedArtDTO, ZonedDateTime zonedDateTime);
}
