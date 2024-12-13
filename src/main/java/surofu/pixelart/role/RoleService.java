package surofu.pixelart.role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findRoleByName(String name);
}
