package com.example.ramengo.controllers;

import com.example.ramengo.dtos.ErrorResponse;
import com.example.ramengo.dtos.OrderRequest;
import com.example.ramengo.dtos.OrderResponse;
import com.example.ramengo.entities.Broth;
import com.example.ramengo.entities.Protein;
import com.example.ramengo.services.DefaultService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "default")
public class OrderController {
    @Autowired
    private DefaultService defaultService;

    @GetMapping("/broths")
    public ResponseEntity<?> getBroths(@Parameter(required = true) @RequestHeader("x-api-key") String apiKey){
        try {
            List<Broth> response = defaultService.listBroths();

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception e) {
            ErrorResponse error = new ErrorResponse();
            error.setError("unable to list broths");

            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<?> placeOrder(@Parameter(required = true) @RequestHeader("x-api-key") String apiKey,
                                        @RequestBody OrderRequest orderRequest){
        ErrorResponse error = new ErrorResponse();

        if(orderRequest.getBrothId() == null || orderRequest.getProteinId() == null) {
            error.setError("both brothId and proteinId are required");

            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        if(orderRequest.getBrothId().isBlank() || orderRequest.getProteinId().isBlank()){
            error.setError("both brothId and proteinId are required");

            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        try{
            OrderResponse response = defaultService.placeOrder(orderRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            error.setError("could not place order");

            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/proteins")
    public ResponseEntity<?> getProteins(@Parameter(required = true) @RequestHeader("x-api-key") String apiKey){
        try {
            List<Protein> response = defaultService.listProteins();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            ErrorResponse error = new ErrorResponse();
            error.setError("unable to list proteins");

            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
