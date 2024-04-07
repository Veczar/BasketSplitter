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
        result = reverseMap(result);
        result = allocateByEfficientTransport(result);
        return result;
    }
    public Map<String, List<String>> reverseMap(Map<String, List<String>> originalMap) {
        Map<String, List<String>> reversedMap = new HashMap<>();

        for (Map.Entry<String, List<String>> entry : originalMap.entrySet()) {
            String product = entry.getKey();
            List<String> deliveryMethods = entry.getValue();

            for (String method : deliveryMethods) {
                reversedMap.computeIfAbsent(method, k -> new ArrayList<>()).add(product);
            }
        }

        return reversedMap;
    }
    public Map<String, List<String>> allocateByEfficientTransport(Map<String, List<String>> productsWithDeliveryMethods) {
        Map<String, List<String>> result = new HashMap<>();

        /*while (!productsWithDeliveryMethods.isEmpty()) {
            Map.Entry<String, List<String>> maxProductTransport = findMaxProductTransport(productsWithDeliveryMethods);
            String maxTransport = maxProductTransport.getKey();
            List<String> maxProducts = maxProductTransport.getValue();

            List<String> remainingProducts = new ArrayList<>(maxProducts);

            for (List<String> deliveryMethods : productsWithDeliveryMethods.values()) {
                deliveryMethods.removeAll(maxProducts);
            }

            result.put(maxTransport, remainingProducts);
           productsWithDeliveryMethods.entrySet().removeIf(entry -> entry.getValue().isEmpty());
        }*/
        /*while (!productsWithDeliveryMethods.isEmpty()) {
            Map.Entry<String, List<String>> maxEntry = findMaxProductTransport(productsWithDeliveryMethods);
            String efficientTransport = maxEntry.getKey();
            List<String> productsForTransport = maxEntry.getValue();

            // Dodaj produkty do wynikowej mapy dla danego transportu
            result.put(efficientTransport, productsForTransport);

            // Usuń przetworzone produkty z mapy oryginalnej
            productsWithDeliveryMethods.remove(efficientTransport);

            // Usuń przetworzone produkty z listy dostępnych produktów w oryginalnej mapie
            for (List<String> products : productsWithDeliveryMethods.values()) {
                products.removeAll(productsForTransport);
            }
        }*/
        while (!productsWithDeliveryMethods.isEmpty()) {
            Map.Entry<String, List<String>> maxEntry = findMaxProductTransport(productsWithDeliveryMethods);
            String efficientTransport = maxEntry.getKey();
            List<String> productsForTransport = maxEntry.getValue();

            // Sprawdź czy lista produktów dla danego transportu jest pusta
            if (!productsForTransport.isEmpty()) {
                // Dodaj produkty do wynikowej mapy dla danego transportu
                result.put(efficientTransport, productsForTransport);

                // Usuń przetworzone produkty z mapy oryginalnej
                productsWithDeliveryMethods.remove(efficientTransport);

                // Usuń przetworzone produkty z listy dostępnych produktów w oryginalnej mapie
                for (List<String> products : productsWithDeliveryMethods.values()) {
                    products.removeAll(productsForTransport);
                }
            } else {
                // Jeśli lista produktów dla danego transportu jest pusta, usuń ten transport z oryginalnej mapy
                productsWithDeliveryMethods.remove(efficientTransport);
            }
        }


        return result;
    }

    public Map.Entry<String, List<String>> findMaxProductTransport(Map<String, List<String>> productsWithDeliveryMethods) {
        Map.Entry<String, List<String>> maxEntry = null;
        int maxProducts = Integer.MIN_VALUE;

        for (Map.Entry<String, List<String>> entry : productsWithDeliveryMethods.entrySet()) {
            int productCount = entry.getValue().size();
            if (productCount > maxProducts) {
                maxProducts = productCount;
                maxEntry = entry;
            }
        }

        return maxEntry;
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
