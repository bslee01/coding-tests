package com.dius.commerce.pricing.impl;

import com.dius.commerce.pricing.PricingRule;
import com.dius.commerce.catalogue.Product;
import com.dius.commerce.ComputerStore;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Pricing rule for all other computer items that are not charged with special sales pricing rules
 */
public class StandardUnitPricingRuleImpl implements PricingRule {

    private final List<Product> availableItems = Arrays.asList( ComputerStore.IPAD,
                                                                ComputerStore.MACBOOKPRO,
                                                                ComputerStore.APPLETV,
                                                                ComputerStore.VGA );

    @Override
    public BigDecimal calculatePrice(Map<Product, Integer> shoppingCart) {
        BigDecimal totalAmount = BigDecimal.valueOf(0);

        for (Product item : availableItems) {
            Integer itemCount = shoppingCart.get(item);

            if (itemCount != null) {
                BigDecimal unitPrice = item.getPrice();
                totalAmount = totalAmount.add(unitPrice.multiply(BigDecimal.valueOf(itemCount)));
            }
        }

        return totalAmount;
    }
}
