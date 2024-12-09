package surofu.pixelart.art;

import org.springframework.stereotype.Service;

@Service
public class ArtSerializerImpl implements ArtSerializer {

    @Override
    public FindArtRTO artToFind(Art art) {
        return FindArtRTO.builder()
                .id(art.getId())
                .title(art.getTitle())
                .map(art.getMap())
                .colors(art.getColors())
                .publicationDate(art.getPublicationDate())
                .build();
    }
}
