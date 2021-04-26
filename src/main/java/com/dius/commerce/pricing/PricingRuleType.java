package com.dius.commerce.pricing;

import com.dius.commerce.pricing.impl.AppleTV3For2PricingRuleImpl;
import com.dius.commerce.pricing.impl.IpadBulkDiscountPricingRuleImpl;
import com.dius.commerce.pricing.impl.MacbookProVga1For1PricingRuleImpl;
import com.dius.commerce.pricing.impl.StandardUnitPricingRuleImpl;

public enum PricingRuleType {
    APPELTV3FOR2() {
        @Override
        PricingRule createPricingRule() {
            return new AppleTV3For2PricingRuleImpl();
        }
    },

    IPADBULKDISCOUNT() {
        @Override
        PricingRule createPricingRule() {
            return new IpadBulkDiscountPricingRuleImpl();
        }
    },

    MACBOOKPROVGA1FOR1() {
        @Override
        PricingRule createPricingRule() {
            return new MacbookProVga1For1PricingRuleImpl();
        }
    },

    STANDARDUNITPRICING() {
        @Override
        PricingRule createPricingRule() {
            return new StandardUnitPricingRuleImpl();
        }
    };

    abstract PricingRule createPricingRule();
}
