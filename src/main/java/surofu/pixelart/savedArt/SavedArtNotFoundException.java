package surofu.pixelart.savedArt;

public class SavedArtNotFoundException extends RuntimeException {
    public SavedArtNotFoundException(Long artId) {
        super(String.format("SavedArt with ID `%d` was not found", artId));
    }
}
