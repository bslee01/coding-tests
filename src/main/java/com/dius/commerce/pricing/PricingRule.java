package com.dius.commerce.pricing;

import com.dius.commerce.catalogue.Product;

import java.math.BigDecimal;
import java.util.Map;

/**
 * The implementation of each pricing rule cannot guarantee if the shopping cart will be mutated during the calculation.
 * As a result, it is advisable to make a defensive copy of shopping cart to pass into PricingRule implementation.
 *
 * Please refer to Effective Java Item 50
 */
public interface PricingRule {

    BigDecimal calculatePrice(Map<Product, Integer> shoppingCart);

}
