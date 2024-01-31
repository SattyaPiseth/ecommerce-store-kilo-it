package com.example.demo.model.request.Category;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
@Getter

public class CategoryRQ {
    @NotEmpty(message = "Please filled name")
    private String name;
    @NotEmpty(message = "Please filled description")
    private String description;

}
