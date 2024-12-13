package surofu.pixelart.art;

public class ArtNotFoundException extends RuntimeException {
    public ArtNotFoundException(Long id) {
        super(String.format("Art with id `%s` not found", id));
    }
}
