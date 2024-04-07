import BasketSplitter.BasketSplitter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
class BasketSplitterTest {
    @Test
    public void testSplitWithSingleProduct() {
        BasketSplitter splitter = new BasketSplitter("config/config.json");

        List<String> items = Arrays.asList("Flower - Daisies", "Table Cloth 54x72 White", "Cookies - Englishbay Wht");
        Map<String, List<String>> result = splitter.split(items);

        Assertions.assertEquals(1, result.size());
        Assertions.assertTrue(result.containsKey("Courier"));
        Assertions.assertEquals(3, result.get("Courier").size());
    }
    @Test
    public void testSplitWithSpaceBeforeProductName() {
        BasketSplitter splitter = new BasketSplitter("config/config.json");

        List<String> items = Arrays.asList(" Flower - Daisies", "Table Cloth 54x72 White", "Cookies - Englishbay Wht");
        Map<String, List<String>> result = splitter.split(items);

        Assertions.assertEquals(1, result.size());
        Assertions.assertTrue(result.containsKey("Courier"));
        Assertions.assertEquals(3, result.get("Courier").size());
    }

    @Test
    public void testSplitWithSpaceAfterProductName() {
        BasketSplitter splitter = new BasketSplitter("config/config.json");

        List<String> items = Arrays.asList("Flower - Daisies ", "Table Cloth 54x72 White ", "Cookies - Englishbay Wht ");
        Map<String, List<String>> result = splitter.split(items);

        Assertions.assertEquals(1, result.size());
        Assertions.assertTrue(result.containsKey("Courier"));
        Assertions.assertEquals(3, result.get("Courier").size());
    }

    @Test
    public void testSplitWithNoMatchingProducts() {
        BasketSplitter splitter = new BasketSplitter("config/config.json");

        List<String> items = Arrays.asList("Socks", "Shoes", "Towel");
        Map<String, List<String>> result = splitter.split(items);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testSplitWithEmptyBasket() {
        BasketSplitter splitter = new BasketSplitter("config/config.json");

        List<String> items = Arrays.asList();
        Map<String, List<String>> result = splitter.split(items);

        Assertions.assertTrue(result.isEmpty());
    }

}