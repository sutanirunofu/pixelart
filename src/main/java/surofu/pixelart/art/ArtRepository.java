package surofu.pixelart.art;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtRepository extends JpaRepository<Art, Long> {

    @Query("SELECT a FROM Art a JOIN Star s ON a.id = s.artId WHERE s.userId = :userId")
    List<Art> findWithStar(@Param("userId") Long userId);
}
