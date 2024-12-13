package surofu.pixelart.savedArt;

public class SavedArtNotFoundException extends RuntimeException {
    public SavedArtNotFoundException(Long id) {
        super(String.format("SavedArt with id `%s` not found", id));
    }
}
