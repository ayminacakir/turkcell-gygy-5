package com.turkcell.spring_starter.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

public class ProductForCreateDto {

    @NotBlank
    @Length(min = 2)
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$")
    private String name;

    @PositiveOrZero
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
