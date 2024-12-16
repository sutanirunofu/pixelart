package surofu.pixelart.utils;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

@Component
public class StringConverter {
    private final Gson gson = new Gson();

    public int[][] stringToMatrix(String str) {
        return gson.fromJson(str, int[][].class);
    }

    public int[] hexToInt(String str) {
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
