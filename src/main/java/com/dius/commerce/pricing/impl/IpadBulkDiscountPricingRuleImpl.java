package com.dius.commerce.pricing.impl;

import com.dius.commerce.pricing.PricingRule;
import com.dius.commerce.catalogue.Product;
import com.dius.commerce.ComputerStore;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Pricing rule for buying more than 4 units of iPad, each unit price will be $499.99
 * instead of $549.99
 */
public class IpadBulkDiscountPricingRuleImpl implements PricingRule {

    @Override
    public BigDecimal calculatePrice(Map<Product, Integer> shoppingCart) {
        Integer ipadCount = shoppingCart.get(ComputerStore.IPAD);
        BigDecimal totalAmount = BigDecimal.valueOf(0);

        if ((ipadCount != null) && (ipadCount > 0)) {
            BigDecimal unitOfPrice = determineUnitOfPrice(ipadCount);
            totalAmount = unitOfPrice.multiply(BigDecimal.valueOf(ipadCount));
        }

        updateShoppingCart(shoppingCart);

        return totalAmount;
    }

    private BigDecimal determineUnitOfPrice(int ipadCount) {
        return ipadCount > 4 ? BigDecimal.valueOf(499.99) : ComputerStore.IPAD.getPrice();
    }

    private void updateShoppingCart(Map<Product, Integer> shoppingCart) {
        shoppingCart.remove(ComputerStore.IPAD);
    }
}
