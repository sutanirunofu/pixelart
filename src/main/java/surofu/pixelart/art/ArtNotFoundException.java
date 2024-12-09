package surofu.pixelart.art;

public class ArtNotFoundException extends RuntimeException {
    public ArtNotFoundException(Long artId) {
        super(String.format("Art with ID `%d` was not found", artId));
    }
}
