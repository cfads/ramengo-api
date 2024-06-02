package com.example.ramengo.services;

import com.example.ramengo.dtos.OrderRequest;
import com.example.ramengo.dtos.OrderResponse;
import com.example.ramengo.entities.Broth;
import com.example.ramengo.entities.Order;
import com.example.ramengo.entities.Protein;
import com.example.ramengo.enums.BrothEnum;
import com.example.ramengo.enums.ImagesOrderEnum;
import com.example.ramengo.enums.ProteinEnum;
import com.example.ramengo.repository.BrothRepository;
import com.example.ramengo.repository.OrderRepository;
import com.example.ramengo.repository.ProteinRepository;
import com.example.ramengo.utils.OrderImages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DefaultService {

    @Autowired
    private BrothRepository brothRepository;

    @Autowired
    private ProteinRepository proteinRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Value("${api.redventures.url}")
    private String apiRVUrl;

    @Value("${api.redventures.key}")
    private String apiRVKey;

    public List<Broth> listBroths(){
        List<Broth> broths = brothRepository.findAll();

        return broths;
    }

    public List<Protein> listProteins(){
        List<Protein> proteins = proteinRepository.findAll();

        return proteins;
    }

    public OrderResponse placeOrder(OrderRequest request) throws IOException {
        try {
            String id = generateOrderId();
            String brothName = brothRepository.findById(request.getBrothId()).get().getName();
            String proteinName = proteinRepository.findById(request.getProteinId()).get().getName();
            OrderResponse response = new OrderResponse();

            BrothEnum broth = BrothEnum.valueOf(brothName.toUpperCase());
            ProteinEnum protein = ProteinEnum.valueOf(proteinName.toUpperCase().replace(" ","_"));

            response.setId(id);
            response.setDescription(broth + " and " + protein + " Ramen");
            response.setImage(OrderImages.getOrderImage(broth,protein));

            Order order = new Order();
            BeanUtils.copyProperties(response, order);
            orderRepository.save(order);

            return response;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateOrderId() throws IOException {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiRVUrl + "/orders/generate-id"))
                .header("x-api-key",apiRVKey)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.body());
            String orderId = jsonNode.get("orderId").asText();

            return orderId;
        } catch (IOException e) {
            throw new IOException("Falha na geração do ID do pedido");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
