package com.shopping.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CartHistory {
    private List<String> history;

    public CartHistory() {
        this.history = new ArrayList<>();
    }

    public void addEvent(String event) {
        history.add(String.format("%s: %s", LocalDateTime.now(), event));
    }

    public void display() {
        System.out.println("\n====== Cart History ======");
        history.forEach(System.out::println);
    }

    public List<String> getHistory() {
        return new ArrayList<>(history);
    }
}