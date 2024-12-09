package surofu.pixelart.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("FROM Role r WHERE r.name = :name")
    Optional<Role> findByName(String name);
}
