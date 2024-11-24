package cuisinecraft.ingredientservice.service;

import cuisinecraft.ingredientservice.client.PythonMicroserviceClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class IngredientService {

    private final PythonMicroserviceClient pythonMicroserviceClient;

    public IngredientService(PythonMicroserviceClient pythonMicroserviceClient) {
        this.pythonMicroserviceClient = pythonMicroserviceClient;
    }

    public Map<String, Object> getIngredientPricing(String query, int size, int page) {
        return pythonMicroserviceClient.searchProducts(query, size, page);
    }
}
