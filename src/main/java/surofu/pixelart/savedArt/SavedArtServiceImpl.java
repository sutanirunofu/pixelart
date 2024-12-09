package surofu.pixelart.savedArt;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import surofu.pixelart.user.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SavedArtServiceImpl implements SavedArtService {
    private final SavedArtRepository repository;
    private final SavedArtSerializer serializer;
    private final UserService userService;
    private final UserRepository userRepository;

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

    @Override
    public boolean isUserOwnedArt(String username, Long savedArtId) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) throw new UserNotFoundException(username);
        List<SavedArt> arts = user.get().getSavedArts().stream().filter(art -> art.getId().equals(savedArtId)).toList();
        return !arts.isEmpty();
    }

    @Override
    @Transactional
    public void updateById(Long id, UpdateSavedArtDTO updateSavedArtDTO) throws SavedArtNotFoundException {
        Optional<SavedArt> savedArt = repository.findById(id);
        if (savedArt.isEmpty()) throw new SavedArtNotFoundException(id);
        updateSavedArtDTO = serializer.compareUpdateWithArt(updateSavedArtDTO, savedArt.get());
        System.out.println(updateSavedArtDTO);
        repository.updateById(id, updateSavedArtDTO, ZonedDateTime.now());
    }
}
