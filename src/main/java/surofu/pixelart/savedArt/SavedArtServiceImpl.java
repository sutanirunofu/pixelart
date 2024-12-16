package surofu.pixelart.savedArt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import surofu.pixelart.art.*;
import surofu.pixelart.user.User;
import surofu.pixelart.user.UserNotFoundException;
import surofu.pixelart.user.UserRepository;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SavedArtServiceImpl implements SavedArtService {
    private final SavedArtRepository repository;
    private final SavedArtSerializer serializer;
    private final UserRepository userRepository;
    private final ArtRepository artRepository;

    @Override
    public List<FindSavedArtRTO> findAll(String username) {
        return repository.findAllByUserUsername(username).stream().map(serializer::artToFind).toList();
    }

    @Override
    public Optional<FindSavedArtRTO> findById(String username, Long artId) {
        return repository.findByUsernameAndArtId(username, artId).map(serializer::artToFind);
    }

    @Override
    public void save(String username, Long artId) throws UserNotFoundException, ArtNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        Art art = artRepository.findById(artId).orElseThrow(() -> new ArtNotFoundException(artId));

        SavedArt savedArt = new SavedArt();
        savedArt.setUser(user);
        savedArt.setArt(art);
        savedArt.setModifiedDate(ZonedDateTime.now());

        repository.save(savedArt);
    }

    @Override
    public FindSavedArtRTO updateByArtId(String username, Long artId, UpdateSavedArtDTO updateSavedArtDTO) throws SavedArtNotFoundException {
        SavedArt art = repository.findById(artId).orElseThrow(() -> new SavedArtNotFoundException(artId));
        art = serializer.compareArtWithUpdate(art, updateSavedArtDTO);
        repository.save(art);
        return serializer.artToFind(art);
    }
}
