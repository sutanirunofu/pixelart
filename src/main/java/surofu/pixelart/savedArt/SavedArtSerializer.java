package surofu.pixelart.savedArt;

public interface SavedArtSerializer {
    FindSavedArtRTO artToFind(SavedArt art);
    SavedArt compareArtWithUpdate(SavedArt art, UpdateSavedArtDTO updateSavedArtDTO);
}
