package surofu.pixelart.art;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
