package ibf2022.ssfassessment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import ibf2022.ssfassessment.model.Quotation;
import ibf2022.ssfassessment.model.Item;
import org.springframework.http.RequestEntity;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.UUID;

@Service
public class QuotationService {

    public Quotation getQuotations(List<String> items) throws Exception {

        // Create json Object
        JsonObject json = Json.createObjectBuilder()
                .add("quotations", items.toString())
                .build();

        // POST /post
        // Conent-Type: application/json
        // Accept: application/json
        RequestEntity<String> req = RequestEntity
                .post("https://quotation.chuklee.com")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(json.toString(), String.class);

        // Make the request
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);
        System.out.printf("Status code: %s\n", resp.getStatusCode());
        String payload = resp.getBody();
        System.out.printf("Payload: %s\n", payload);
        
        Quotation quotation = createQuotation();
        quotation.setQuotations(null);
        return quotation;
    }

    public Quotation createQuotation() {
		String quoteId = UUID.randomUUID().toString().substring(0, 8);
		Quotation quotation = new Quotation();
		quotation.setQuoteId(quoteId);
		return quotation;
	}

    public float calculateCost(Item item) {
        float total = 0f;
        switch (item.getName()) {
            case "apple":
                total += 0.3 * item.getQuantity();
                break;
            case "bread":
                total += 2.5 * item.getQuantity();
                break;
            case "chicken":
                total += 5 * item.getQuantity();
                break;
            default:
        }
        return total;
    }
}
