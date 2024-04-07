import BasketSplitter.BasketSplitter;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        BasketSplitter basketSplitter = new BasketSplitter("C:\\Users\\Vecza\\IntelliJ_Projects\\ShopingLibrary\\config\\config.json");

        List<String> Basket1 = null;
        try{
            Basket1 = basketSplitter.getBasket("C:\\Users\\Vecza\\IntelliJ_Projects\\ShopingLibrary\\config\\basket-1.json");
        }catch (IOException e){
            throw new RuntimeException(e);
        } finally {
            System.out.println(Basket1.toString());
        }

        Map<String, List<String>> map = basketSplitter.split(Basket1);
        System.out.println(map.toString());
        map = basketSplitter.chooseTransport(map);
        System.out.println(map.toString());


    }
}