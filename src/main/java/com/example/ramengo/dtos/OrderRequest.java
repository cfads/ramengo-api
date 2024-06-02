package com.example.ramengo.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @NotBlank(message = "brothId is required")
    private String brothId;

    @NotBlank(message = "proteinId is required")
    private String proteinId;
}
