package com.dius.commerce.pricing.impl;

import com.dius.commerce.pricing.PricingRule;
import com.dius.commerce.catalogue.Product;
import com.dius.commerce.ComputerStore;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Pricing rule for buying 3 Apple TVs, the pays the price of 2 only which is $219
 * instead of $328.50
 */
public class AppleTV3For2PricingRuleImpl implements PricingRule {

    private final BigDecimal priceOf3Unit = BigDecimal.valueOf(219);

    @Override
    public BigDecimal calculatePrice(Map<Product, Integer> shoppingCart) {
        Integer appleTVCount = shoppingCart.get(ComputerStore.APPLETV);
        BigDecimal totalAmount = BigDecimal.valueOf(0);

        if ((appleTVCount != null) && (appleTVCount >= 3)) {
            totalAmount = totalAmount.add(calculatePriceFor3Unit(appleTVCount));
        }

        if ((appleTVCount != null) && (appleTVCount > 0)) {
            totalAmount = totalAmount.add(calculatePriceForSingleUnit(appleTVCount));
        }

        updateShoppingCart(shoppingCart);

        return totalAmount;
    }

    private BigDecimal calculatePriceFor3Unit(int appleTVCount) {
        int unitOf3 = appleTVCount / 3;
        return priceOf3Unit.multiply(BigDecimal.valueOf(unitOf3));
    }

    private BigDecimal calculatePriceForSingleUnit(int appleTVCount) {
        int unit = appleTVCount % 3;
        BigDecimal unitPrice = ComputerStore.APPLETV.getPrice();
        return unitPrice.multiply(BigDecimal.valueOf(unit));
    }

    private void updateShoppingCart(Map<Product, Integer> shoppingCart) {
        shoppingCart.remove(ComputerStore.APPLETV);
    }
}