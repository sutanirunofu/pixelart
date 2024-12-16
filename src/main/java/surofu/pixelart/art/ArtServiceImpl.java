package surofu.pixelart.art;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
@RequiredArgsConstructor
public class ArtServiceImpl implements ArtService {
    private final ArtRepository repository;
    private final ArtSerializer serializer;

    @Override
    public List<FindArtRTO> findAll() {
        return repository.findAll().stream().map(serializer::artToFind).toList();
    }

    @Override
    public Optional<FindArtRTO> findById(Long id) {
        return repository.findById(id).map(serializer::artToFind);
    }
}
