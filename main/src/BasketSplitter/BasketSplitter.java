package BasketSplitter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class BasketSplitter {
    ObjectMapper objectMapper;
    Map<String, List<String>> configMap;

    public BasketSplitter(String absolutePathToConfigFile){
        objectMapper = new ObjectMapper();
        try {
            configMap = objectMapper.readValue(new File(absolutePathToConfigFile),new TypeReference<Map<String, List<String>>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Map<String, List<String>> split(List<String> items){
        return null;
    }
}
