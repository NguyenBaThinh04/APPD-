package com.example.hitcapp;

import java.util.ArrayList;

public class CartManager {

    public static ArrayList<Dt> cart = new ArrayList<>();

    public static void add(Dt c) {
        cart.add(c);
    }

    public static void clear() {
        cart.clear();
    }

    public static double total() {
        double sum = 0;
        for (Dt c : cart) {
            sum += c.price;
        }
        return sum;
    }
}