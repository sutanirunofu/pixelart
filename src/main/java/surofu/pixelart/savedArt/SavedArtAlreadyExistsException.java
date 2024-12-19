package surofu.pixelart.savedArt;

public class SavedArtAlreadyExistsException extends RuntimeException {
    public SavedArtAlreadyExistsException(Long id) {
        super(String.format("Saved Art with Art id `%s` already exists", id));
    }
}
