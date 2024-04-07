package BasketSplitter;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BasketSplitter {
    Map<String, List<String>> configMap;

    public BasketSplitter(String absolutePathToConfigFile){
        String jsonContent = null;
        try {
             jsonContent = Files.readString(Paths.get(absolutePathToConfigFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JSONObject jsonObject = new JSONObject(jsonContent);
        Map<String, List<String>> map = new HashMap<>();

        for (String key : jsonObject.keySet()) {
            JSONArray jsonArray = jsonObject.getJSONArray(key);
            List<String> values = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                values.add(jsonArray.getString(i));
            }
            map.put(key, values);
        }

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Values: " + entry.getValue());
        }
    }
    public Map<String, List<String>> split(List<String> items){
        return null;
    }

    @Override
    public String toString() {
        return "BasketSplitter{" +
                "configMap=" + configMap +
                '}';
    }
}
