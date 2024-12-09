package surofu.pixelart.savedArt;

public interface SavedArtSerializer {
    FindSavedArtRTO artToFind(SavedArt art);
    UpdateSavedArtDTO compareUpdateWithArt(UpdateSavedArtDTO updateSavedArtDTO, SavedArt savedArt);
}
