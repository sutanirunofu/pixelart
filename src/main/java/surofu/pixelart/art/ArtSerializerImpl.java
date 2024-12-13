package surofu.pixelart.art;

import org.springframework.stereotype.Service;
import surofu.pixelart.utils.StringConverter;

@Service
public class ArtSerializerImpl implements ArtSerializer {
    private final StringConverter converter = new StringConverter();

    @Override
    public FindArtRTO artToFind(Art art) {
        return FindArtRTO.builder()
                .id(art.getId())
                .title(art.getTitle())
                .map(converter.stringToMatrix(art.getMap()))
                .colors(converter.hexToInt(art.getColors()))
                .publicationDate(art.getPublicationDate())
                .build();
    }


}
