package surofu.pixelart.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;
    private final RoleSerializer serializer;

    @Override
    public Optional<FindRoleRTO> findRoleByName(String name) {
        Optional<Role> role = repository.findByName(name);
        return role.map(serializer::roleToFind);
    }
}
