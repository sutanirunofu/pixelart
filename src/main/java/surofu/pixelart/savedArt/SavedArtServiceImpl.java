package surofu.pixelart.savedArt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import surofu.pixelart.user.FindUserRTO;
import surofu.pixelart.user.UserNotFoundException;
import surofu.pixelart.user.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SavedArtServiceImpl implements SavedArtService {
    private final SavedArtRepository repository;
    private final SavedArtSerializer serializer;
    private final UserService userService;

    @Override
    public List<FindSavedArtRTO> findAll() {
        return repository.findAll().stream().map(serializer::artToFind).toList();
    }

    @Override
    public List<FindSavedArtRTO> findAllByUsername(String username) throws UserNotFoundException {
        Optional<FindUserRTO> user = userService.findByUsername(username);
        if (user.isEmpty()) throw new UserNotFoundException(username);
        return repository.findAllByUserId(user.get().getId()).stream().map(serializer::artToFind).toList();
    }

    @Override
    public List<FindSavedArtRTO> findUncompleted() {
        return List.of();
    }
}
