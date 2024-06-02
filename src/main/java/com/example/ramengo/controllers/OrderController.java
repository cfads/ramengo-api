package com.example.ramengo.controllers;

import com.example.ramengo.dtos.ErrorResponse;
import com.example.ramengo.dtos.OrderRequest;
import com.example.ramengo.dtos.OrderResponse;
import com.example.ramengo.entities.Broth;
import com.example.ramengo.entities.Protein;
import com.example.ramengo.services.DefaultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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


    @Operation(
            summary = "List all available broths",
            description = "This endpoint returns a list of all available broths.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A list of broths",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema (implementation = Broth.class)),
                                    examples = @ExampleObject(
                                            value = "[\n  {\n    \"id\": \"3\",\n    \"imageInactive\": \"https://tech.redventures.com.br/icons/miso/inactive.svg\",\n    \"imageActive\": \"https://tech.redventures.com.br/icons/miso/active.svg\",\n    \"name\": \"Miso\",\n    \"description\": \"Paste made of fermented soybeans\",\n    \"price\": 12\n  }\n]"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = "{\n  \"error\": \"x-api-key header missing\"\n}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = "{\n  \"error\": \"unable to list broths\"\n}"
                                    )
                            )
                    )
            }
    )
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

    @Operation(
            summary = "Place an order",
            description = "This endpoint allows users to place a new order.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Order details",
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    value = "{\n  \"brothId\": \"1\",\n  \"proteinId\": \"2\"\n}"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Order placed successfully",
                            content = @Content(
                                    schema = @Schema(implementation = OrderResponse.class),
                                    examples = @ExampleObject(
                                            value = "{\n  \"id\": \"51530\",\n  \"description\": \"Salt and Yasai Vegetable Ramen\",\n  \"image\": \"https://s3-alpha-sig.figma.com/img/3ae2/df67/13696c1c0b4f6cd8aedd12e24d921723?Expires=1717977600&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=LQzlEMdgI4YVhXXti9MtPwc3kHaZ7OxEsKApfqYF5PoNxIf-vJOhMuy4j~34k3RCZo77lEVhiY7lFw7W93-Amed329bryHtDJX~8h16o-7h2TSssqQzHGlU22Ocpuj2oL09EE8tSDkTleC8t124kufxjdIueMMnXvlmTGGv29f5fYK8FvVKq3de6k2A3uNY0YATNbbYw7DGWgG4uy9WDl6mmLMXvkipzfob1NopmAOxalP2z5fh0YyyBCOwHANt5GpNaaS~bL~~XRoxhp12zr7hOmohj5Ur1tUgY81ZT-lVMOqfx~Q0vBPsjJVjl6AUCCiW7DL-fT2JDHsjcnaT9tA__\"\n}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = "{\n  \"error\": \"both brothId and proteinId are required\"\n}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = "{\n  \"error\": \"x-api-key header missing\"\n}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = "{\n  \"error\": \"could not place order\"\n}"
                                    )
                            )
                    )
            }
    )
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

    @Operation(
            summary = "List all available proteins",
            description = "This endpoint returns a list of all available proteins.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A list of proteins",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema (implementation = Protein.class)),
                                    examples = @ExampleObject(
                                            value = "[\n  {\n    \"id\": 3,\n    \"imageInactive\": \"https://tech.redventures.com.br/icons/chicken/inactive.svg\",\n    \"imageActive\": \"https://tech.redventures.com.br/icons/chicken/active.svg\",\n    \"name\": \"Karaague\",\n    \"description\": \"Paste made of fermented soybeans\",\n    \"price\": 12\n  }\n]"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = "{\n  \"error\": \"x-api-key header missing\"\n}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = "{\n  \"error\": \"unable to list proteins\"\n}"
                                    )
                            )
                    )
            }
    )
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
