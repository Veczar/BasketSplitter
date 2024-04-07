import BasketSplitter.BasketSplitter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        BasketSplitter basketSplitter = new BasketSplitter("C:\\Users\\Vecza\\IntelliJ_Projects\\ShopingLibrary\\config\\config.json");

        List<String> basket1 = null;
        List<String> basket2 = null;
         try{
            basket1 = basketSplitter.getBasket("C:\\Users\\Vecza\\IntelliJ_Projects\\ShopingLibrary\\config\\basket-1.json");
            basket2 = basketSplitter.getBasket("C:\\Users\\Vecza\\IntelliJ_Projects\\ShopingLibrary\\config\\basket-2.json");
        }catch (IOException e){
            throw new RuntimeException(e);
        } finally {
            System.out.println(basket1.toString());
        }

        Map<String, List<String>> map = basketSplitter.split(basket1);
        System.out.println(map.toString());

        map = basketSplitter.split(basket2);
        System.out.println(map);

        BasketSplitter splitter = new BasketSplitter("config/config.json");

        List<String> items = Arrays.asList("Flower - Daisies", "Table Cloth 54x72 White", "Cookies - Englishbay Wht", "Tart - Raisin And Pecan", "Cocoa Butter");
        Map<String, List<String>> result = splitter.split(items);
        System.out.println(result);

    }
}