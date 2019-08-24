package com.rogelioorts.talks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {

    private String email;

    private List<Line> lines = new ArrayList<>();

    @Data
    @AllArgsConstructor
    public static class Line {

        private String productId;

    }

}
