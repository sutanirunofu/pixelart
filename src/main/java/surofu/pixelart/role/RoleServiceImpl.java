package surofu.pixelart.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    @Override
    public Optional<Role> findRoleByName(String name) {
        return repository.findByName(name);
    }
}
