package surofu.pixelart.role;

import java.util.Optional;

public interface RoleService {
    Optional<FindRoleRTO> findRoleByName(String name);
}
