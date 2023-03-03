package ibf2022.ssfassessment.controller;

import org.springframework.web.bind.annotation.RestController;

import ibf2022.ssfassessment.model.Order;
import ibf2022.ssfassessment.service.QuotationService;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Reader;
import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path="checkout", produces = MediaType.APPLICATION_JSON_VALUE)
public class PurchaseRestController {

    @Autowired
    QuotationService QuoSvc;
    
    // Consume an application/json and return json
    @PostMapping(path="/quotation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postQuotation(@RequestBody String payload) {

        // Convert String (payload) into json
        Reader reader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(reader);
		JsonObject json = jsonReader.readObject();

        // Create Entity model from json object
        Order order = Order.create(json);

        // Perform business operation on Entity model
        QuoSvc.createQuotation();

        json = order.toJson();

        // Send json back to client as response
        return ResponseEntity
            .status(201)
            .body(json.toString());
    }

}
