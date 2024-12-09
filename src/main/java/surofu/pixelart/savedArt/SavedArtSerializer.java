package surofu.pixelart.savedArt;

public interface SavedArtSerializer {
    FindSavedArtRTO artToFind(SavedArt art);
    FindSavedArtWithUserRTO artToFindWithUser(SavedArt art);
}
