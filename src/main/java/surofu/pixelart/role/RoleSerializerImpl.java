package surofu.pixelart.role;

import org.springframework.stereotype.Service;

@Service
public class RoleSerializerImpl implements RoleSerializer {

    @Override
    public FindRoleRTO roleToFind(Role role) {
        return FindRoleRTO.builder()
                .id(role.getId())
                .name(role.getName().split("_")[1])
                .build();
    }
}
