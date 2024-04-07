package BasketSplitter;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


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
            configMap  = map;

        /*for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Values: " + entry.getValue());
        }*/
    }
    public Map<String, List<String>> split(List<String> items){
        Map<String, List<String>> result = new HashMap<>();

        for (String item : items) {
            for (Map.Entry<String, List<String>> entry : configMap.entrySet()) {
                String productName = entry.getKey();
                List<String> deliveryMethods = entry.getValue();

                if (item.contains(productName)) {
                    result.put(productName, deliveryMethods);
                    break; // Przeszukujemy tylko jedną mapę, więc można przerwać pętlę
                }
            }
        }

        return result;
    }
    public Map<String, List<String>> chooseTransport(Map<String, List<String>> productsWithDeliveryMethods){
        Map<String, List<String>> result = new HashMap<>();

        // Zbieranie wszystkich dostępnych metod dostawy
        Set<String> allDeliveryMethods = new HashSet<>();
        for (List<String> deliveryMethods : productsWithDeliveryMethods.values()) {
            allDeliveryMethods.addAll(deliveryMethods);
        }

        // Przejście przez każdą metodę dostawy i sprawdzenie, czy jest używana przez wszystkie produkty
        for (String deliveryMethod : allDeliveryMethods) {
            List<String> productsWithCommonDeliveryMethod = new ArrayList<>();
            for (Map.Entry<String, List<String>> entry : productsWithDeliveryMethods.entrySet()) {
                if (entry.getValue().contains(deliveryMethod)) {
                    productsWithCommonDeliveryMethod.add(entry.getKey());
                }
            }
            result.put(deliveryMethod, productsWithCommonDeliveryMethod);
        }

        return result;
    }


    public List<String> getBasket(String path) throws IOException {
        String jsonContent = new String(Files.readString(Paths.get(path)));

        JSONArray jsonArray = new JSONArray(jsonContent);

        List<String> result =  new ArrayList<>();
        for(int i = 0;i < jsonArray.length(); i++){
            result.add(jsonArray.getString(i));
        }
        return result;
    }

    @Override
    public String toString() {
        return "BasketSplitter{" +
                "configMap=" + configMap +
                '}';
    }
}
