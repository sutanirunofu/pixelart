package surofu.pixelart.art;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.json.JSONArray;

@Service
public class ArtSerializerImpl implements ArtSerializer {

    @Override
    public FindArtRTO artToFind(Art art) {
        return FindArtRTO.builder()
                .id(art.getId())
                .title(art.getTitle())
                .map(stringToMatrix(art.getMap()))
                .colors(hexToInt(art.getColors()))
                .publicationDate(art.getPublicationDate())
                .build();
    }

    private int[][] stringToMatrix(String str) {
        Gson gson = new Gson();
        return gson.fromJson(str, int[][].class);
    }

    private int[] hexToInt(String str) {
        JSONArray jsonArray = new JSONArray(str);
        int[] intArray = new int[jsonArray.length()];

        // Convert each hex string to an integer
        for (int i = 0; i < jsonArray.length(); i++) {
            String hexString = jsonArray.getString(i);
            // Remove "0x" prefix if present
            if (hexString.startsWith("0x")) {
                hexString = hexString.substring(2);
            }
            // Parse the hex string to an integer
            intArray[i] = Integer.parseInt(hexString, 16);
        }

        return intArray;
    }
}
