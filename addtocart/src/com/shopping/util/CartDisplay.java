//package com.shopping.util;
//
//import com.shopping.model.CartItem;
//import com.shopping.service.DiscountManager;
//import java.util.Map;
//
//public class CartDisplay {
//    public static void printCartContents(Map<Integer, CartItem> items, DiscountManager discountManager) {
//        if (items.isEmpty()) {
//            System.out.println("Shopping cart is empty.");
//            return;
//        }
//
//        System.out.println("\n====== Shopping Cart ======");
//        items.values().forEach(item -> {
//            System.out.printf("%s x%d - $%.2f (Added: %s)%n",
//                item.getProduct().toString(),
//                item.getQuantity(),
//                item.getTotalPrice(),
//                item.getAddedAt().toString());
//        });
//
//        double subtotal = calculateSubtotal(items);
//        printPriceDetails(subtotal, discountManager);
//    }
//
//    public static void printCheckoutSummary(Map<Integer, CartItem> items, DiscountManager discountManager) {
//        System.out.println("\n====== Checkout Summary ======");
//        items.values().forEach(item -> {
//            System.out.printf("%s x%d - $%.2f%n",
//                item.getProduct().getName(),
//                item.getQuantity(),
//                item.getTotalPrice());
//        });
//
//        double subtotal = calculateSubtotal(items);
//        printPriceDetails(subtotal, discountManager);
//    }
//
//    private static double calculateSubtotal(Map<Integer, CartItem> items) {
//        return items.values().stream()
//            .mapToDouble(CartItem::getTotalPrice)
//            .sum();
//    }
//
//    private static void printPriceDetails(double subtotal, DiscountManager discountManager) {
//        if (discountManager.getDiscountPercent() > 0) {
//            System.out.printf("Subtotal: $%.2f%n", subtotal);
//            System.out.printf("Discount (%.1f%%): -$%.2f%n",
//                discountManager.getDiscountPercent(),
//                discountManager.calculateDiscount(subtotal));
//        }
//        System.out.printf("Total: $%.2f%n",
//            subtotal - discountManager.calculateDiscount(subtotal));
//    }
//}
