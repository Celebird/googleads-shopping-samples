import com.google.api.services.content.model.InventorySetRequest;
import com.google.api.services.content.model.Price;

/**
 * Factory for creating an inventory request -- used by the InventorySet sample.
 */
public class ExampleInventoryFactory {
  public static InventorySetRequest create(String channel, String availability, String priceValue, String salePriceValue, String currency, String salePriceEffectiveDate, Long quantity) {

    InventorySetRequest isr = new InventorySetRequest();

    if (channel != "local" && quantity != null) {
      System.err.println("error: quantity supported only for local products");
      System.exit(2);
    }

    if (currency == null && (priceValue != null || salePriceValue != null)) {
      System.err.println("error: currency required for price or sale price");
      System.exit(3);
    }

    if (quantity != null && quantity >= 0) {
      isr.setQuantity(quantity);
    }

    // required value
    if (availability != null) {
      isr.setAvailability(availability);
    }

    // required value
    if (priceValue != null) {
      Price price = new Price();
      price.setValue(priceValue);
      price.setCurrency(currency);
      isr.setPrice(price);
    }

    if (salePriceValue == null) {
      System.err.println("warning: deleting existing salePrice");
    }
    Price salePrice = new Price();
    salePrice.setValue(salePriceValue);
    salePrice.setCurrency(currency);
    isr.setSalePrice(salePrice);

    if (salePriceEffectiveDate == null) {
      System.err.println("warning: deleting existing salePriceEffectiveDate");
    }
    isr.setSalePriceEffectiveDate(salePriceEffectiveDate);

    return isr;
  }
}
