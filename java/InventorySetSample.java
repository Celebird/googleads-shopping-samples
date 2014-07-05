import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.services.content.model.InventorySetRequest;
import com.google.api.services.content.model.InventorySetResponse;

import java.io.IOException;
import java.util.List;

/**
 * Sample that sets an inventory value. The book123 product is used.
 */
public class InventorySetSample extends BaseSample {
  @Override
  public void execute() throws IOException {

    // Set an Inventory update with ID 'online:en:GB:book123'
    
    InventorySetRequest isr = ExampleInventoryFactory.
    create("online", "out of stock", "9876.54", null, "GBP", null, null);

    try {

      InventorySetResponse result = content.inventory().
      set(merchantId, "online", "online:en:GB:book123", isr).execute();

      System.out.println(isr);
      System.out.println(result);

    } catch (GoogleJsonResponseException e) {
      GoogleJsonError error = e.getDetails();
      System.err.println("Error code: " + error.getCode());
      System.err.println("Error message: " + error.getMessage());
      System.err.println("Error details: " + error.getErrors());
      throw e;
    }

  }

  public static void main(String[] args) throws IOException {
    new InventorySetSample().execute();
  }
}
