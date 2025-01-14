package cuisinecraft.ingredientservice.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@FeignClient(name = "price-lookup", url = "${PRICE_LOOKUP_URL}")
public interface PythonMicroserviceClient {

    @GetMapping("/search")
    Map<String, Object> searchProducts(
            @RequestParam("query") String query,
            @RequestParam("size") int size,
            @RequestParam("page") int page
    );
}