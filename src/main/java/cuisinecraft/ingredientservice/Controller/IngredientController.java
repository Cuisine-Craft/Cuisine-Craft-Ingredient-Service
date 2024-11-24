package cuisinecraft.ingredientservice.Controller;

import cuisinecraft.ingredientservice.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchIngredients(
            @RequestParam("query") String query,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        Map<String, Object> response = ingredientService.getIngredientPricing(query, size, page);
        return ResponseEntity.ok(response);
    }
}
