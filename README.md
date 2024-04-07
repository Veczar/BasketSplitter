## BasketSplitter

The `BasketSplitter` package provides functionality to split a list of items from a shopping basket based on predefined delivery methods.

## Features

- **Split Basket**: Split a list of items from a shopping basket into groups based on predefined delivery methods.
- **Efficient Allocation**: Allocate items to delivery methods efficiently based on the number of items and available delivery methods.
- **Configuration**: Load configuration from a JSON file defining the mapping between products and delivery methods.

## Usage

1. **Initialization**: Create an instance of `BasketSplitter` by providing the absolute path to the configuration JSON file.

    ```java
    BasketSplitter splitter = new BasketSplitter("/path/to/config.json");
    ```

2. **Split Basket**: Call the `split` method with a list of items from the shopping basket to split them based on predefined delivery methods.

    ```java
    List<String> items = Arrays.asList("Numi - Assorted Teas", "Garlic - Peeled", "Cake - Miini Cheesecake Cherry"); // etc
    Map<String, List<String>> result = splitter.split(items);
    ```

3. **Result**: The `split` method returns a map containing the split items grouped by delivery methods.

    ```java
    // Example result:
    // {
    //   Same day delivery=[Numi - Assorted Teas, Garlic - Peeled, Sauce - Mint], 
    //   Courier=[Cake - Miini Cheesecake Cherry], 
    //   Express Collection=[Chocolate - Unsweetened, Fond - Chocolate, Haggis, Longan, Apples - Spartan, Tea - Apple Green Tea, Nut - Almond, Blanched, Whole, Bag Clear 10 Lb, Mushroom - Porcini Frozen, Bagel - Whole White Sesame, Cabbage - Nappa, Nantucket - Pomegranate Pear, Puree - Strawberry]
    // }
    ```

## Configuration File

The configuration JSON file should follow the format where each product is mapped to a list of delivery methods:

```json
{
  "Numi - Assorted Teas": ["Same day delivery", "Next day shipping", "Mailbox delivery", "In-store pick-up", "Pick-up point"],
  "Cake - Miini Cheesecake Cherry": ["Courier"],
  "Chocolate - Unsweetened": ["In-store pick-up", "Parcel locker", "Same day delivery", "Pick-up point", "Courier", "Express Collection", "Mailbox delivery", "Next day shipping"]
}
```

## Dependencies
- **org.json**: This package depends on the org.json library to parse JSON files. Make sure to include this library in your project dependencies.

## Contributors
- **Filip Jędrzejek** ([@Veczar](https://github.com/Veczar))
