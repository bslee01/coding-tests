package com.dius.commerce.pricing;

/**
 * In more realistic setup, the specific type of PricingRule implementation
 * should be created with some form of dependency injection framework
 */
public class PricingRuleCreator {

    private PricingRuleCreator() {
    }

    public static PricingRule createPricingRule(PricingRuleType type) {
        return type.createPricingRule();
    }
}
