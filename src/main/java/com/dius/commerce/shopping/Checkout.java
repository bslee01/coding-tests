package com.dius.commerce.shopping;

import com.dius.commerce.pricing.PricingRule;
import com.dius.commerce.catalogue.Product;

import java.math.BigDecimal;
import java.util.*;

public class Checkout {

    private final List<PricingRule> pricingRules;
    private final Map<Product, Integer> shoppingCart = new HashMap<>();

    public Checkout(List<PricingRule> pricingRules) {
        this.pricingRules = new ArrayList<>(pricingRules);
    }

    public void scan(Product item) {
        Integer itemCount = shoppingCart.putIfAbsent(item, 1);

        if (itemCount != null) {
            itemCount++;
            shoppingCart.put(item, itemCount);
        }
    }

    public BigDecimal total() {
        return calculateGrandTotal();
    }

    private BigDecimal calculateGrandTotal() {
        // Create a defensive shallow copy of shopping cart to pass into list of pricing rules
        // for calculation as the current implementation will update the shopping
        // cart as it goes along
        Map<Product, Integer> copyOfShoppingCart = new HashMap<>(shoppingCart);
        BigDecimal grandTotal = BigDecimal.valueOf(0);

        for (PricingRule pricingRule : pricingRules) {
            grandTotal = grandTotal.add(pricingRule.calculatePrice(copyOfShoppingCart));
        }

        return grandTotal;
    }

    public void clearItems() {
        shoppingCart.clear();
    }

    @Override
    public String toString() {
        StringBuilder shoppingCartItems = new StringBuilder("##### Start - Computer Items and Count #####" + System.lineSeparator());

        shoppingCart.forEach((item, count) -> shoppingCartItems.append(item.getName())
                                                                .append(" => ").append(count)
                                                                .append(System.lineSeparator()));
        shoppingCartItems.append("##### End - Computer Items and Count #####");

        return  shoppingCartItems.toString();
    }
}
