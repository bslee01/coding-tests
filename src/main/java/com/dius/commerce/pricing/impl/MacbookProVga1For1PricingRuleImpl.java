package com.dius.commerce.pricing.impl;

import com.dius.commerce.pricing.PricingRule;
import com.dius.commerce.catalogue.Product;
import com.dius.commerce.ComputerStore;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Pricing rule for buying 1 unit of MacbookPro bundled with 1 free VGA
 */
public class MacbookProVga1For1PricingRuleImpl implements PricingRule {

    @Override
    public BigDecimal calculatePrice(Map<Product, Integer> shoppingCart) {
        Integer macbookProCount = shoppingCart.get(ComputerStore.MACBOOKPRO);
        BigDecimal totalAmount = BigDecimal.valueOf(0);

        if ((macbookProCount != null) && (macbookProCount > 0)) {
            totalAmount = calculateMacbookProPrice(macbookProCount);
            updateShoppingCart(shoppingCart);
        }

        return totalAmount;
    }

    private BigDecimal calculateMacbookProPrice(int macbookProCount) {
        BigDecimal unitPrice = ComputerStore.MACBOOKPRO.getPrice();
        return unitPrice.multiply(BigDecimal.valueOf(macbookProCount));
    }

    private void updateShoppingCart(Map<Product, Integer> shoppingCart) {
        Integer macbookProCount = shoppingCart.get(ComputerStore.MACBOOKPRO);
        Integer vgaCount = shoppingCart.get(ComputerStore.VGA);

        if (vgaCount != null) {
            if (vgaCount > macbookProCount) {
                // Only the count of VGA needs to be paid
                shoppingCart.put(ComputerStore.VGA, (vgaCount - macbookProCount));
            }
            else {
                // More MBP purchased than VGA, all VGAs are free
                shoppingCart.remove(ComputerStore.VGA);
            }
        }

        shoppingCart.remove(ComputerStore.MACBOOKPRO);
    }
}
