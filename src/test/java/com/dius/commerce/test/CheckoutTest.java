package com.dius.commerce.test;

import com.dius.commerce.pricing.PricingRule;
import com.dius.commerce.pricing.PricingRuleCreator;
import com.dius.commerce.pricing.PricingRuleType;
import com.dius.commerce.shopping.Checkout;
import com.dius.commerce.ComputerStore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class CheckoutTest {

    private Checkout checkout;

    @BeforeEach
    void createCheckout() {
        List<PricingRule> pricingRules = constructPricingRules();
        checkout = new Checkout(pricingRules);
    }

    private List<PricingRule> constructPricingRules() {
        PricingRule appleTV3For2 = PricingRuleCreator.createPricingRule(PricingRuleType.APPELTV3FOR2);
        PricingRule ipadBulkDiscount = PricingRuleCreator.createPricingRule(PricingRuleType.IPADBULKDISCOUNT);
        PricingRule macbookProVga1For1 = PricingRuleCreator.createPricingRule(PricingRuleType.MACBOOKPROVGA1FOR1);
        PricingRule standardUnitPrice = PricingRuleCreator.createPricingRule(PricingRuleType.STANDARDUNITPRICING);

        // standardUnitPrice must be at the last
        // TODO: can be better than this !
        return Arrays.asList(appleTV3For2, ipadBulkDiscount, macbookProVga1For1, standardUnitPrice);

    }

    @AfterEach
    public void clearShoppingCart() {
        checkout.clearItems();
    }

    @Test
    void buyThreeAppleTVForPriceOfTwo() {
        BigDecimal actualPrice;
        BigDecimal expectedPrice = ComputerStore.APPLETV.getPrice().multiply(BigDecimal.valueOf(2));

        checkout.scan(ComputerStore.APPLETV);
        checkout.scan(ComputerStore.APPLETV);
        checkout.scan(ComputerStore.APPLETV);

        actualPrice = checkout.total();

        assertThat(actualPrice).isCloseTo(expectedPrice, within(BigDecimal.valueOf(0.01)));
    }

    @Test
    void buyMoreThanFourSuperIpadFor499_99Each() {
        BigDecimal actualPrice;
        BigDecimal expectedPrice = BigDecimal.valueOf(2718.95);

        checkout.scan(ComputerStore.IPAD);
        checkout.scan(ComputerStore.IPAD);
        checkout.scan(ComputerStore.IPAD);
        checkout.scan(ComputerStore.IPAD);
        checkout.scan(ComputerStore.IPAD);
        checkout.scan(ComputerStore.APPLETV);
        checkout.scan(ComputerStore.APPLETV);

        actualPrice = checkout.total();

        assertThat(actualPrice).isCloseTo(expectedPrice, within(BigDecimal.valueOf(0.01)));
    }

    @Test
    void buyOneMacbookProAndFreeOneVGA() {
        BigDecimal actualPrice;
        BigDecimal expectedPrice = BigDecimal.valueOf(1949.98);

        checkout.scan(ComputerStore.MACBOOKPRO);
        checkout.scan(ComputerStore.VGA);
        checkout.scan(ComputerStore.IPAD);

        actualPrice = checkout.total();

        assertThat(actualPrice).isCloseTo(expectedPrice, within(BigDecimal.valueOf(0.01)));
    }

    @Test
    void buyMoreVGAThanMacbookPro() {
        BigDecimal actualPrice;
        BigDecimal expectedPrice = BigDecimal.valueOf(3379.97);

        checkout.scan(ComputerStore.MACBOOKPRO);
        checkout.scan(ComputerStore.MACBOOKPRO);
        checkout.scan(ComputerStore.VGA);
        checkout.scan(ComputerStore.VGA);
        checkout.scan(ComputerStore.VGA);
        checkout.scan(ComputerStore.IPAD);

        actualPrice = checkout.total();

        assertThat(actualPrice).isCloseTo(expectedPrice, within(BigDecimal.valueOf(0.01)));
    }

    @Test
    void buyLessVGAThanMacbookPro() {
        BigDecimal actualPrice;
        BigDecimal expectedPrice = BigDecimal.valueOf(6149.95);

        checkout.scan(ComputerStore.MACBOOKPRO);
        checkout.scan(ComputerStore.MACBOOKPRO);
        checkout.scan(ComputerStore.MACBOOKPRO);
        checkout.scan(ComputerStore.MACBOOKPRO);
        checkout.scan(ComputerStore.VGA);
        checkout.scan(ComputerStore.VGA);
        checkout.scan(ComputerStore.IPAD);

        actualPrice = checkout.total();

        assertThat(actualPrice).isCloseTo(expectedPrice, within(BigDecimal.valueOf(0.01)));
    }

    @Test
    void purchaseToInvokeAllPricingRules() {
        BigDecimal actualPrice;
        BigDecimal expectedPrice = BigDecimal.valueOf(9037.9);

        checkout.scan(ComputerStore.APPLETV);
        checkout.scan(ComputerStore.APPLETV);
        checkout.scan(ComputerStore.APPLETV);

        checkout.scan(ComputerStore.IPAD);
        checkout.scan(ComputerStore.IPAD);
        checkout.scan(ComputerStore.IPAD);
        checkout.scan(ComputerStore.IPAD);
        checkout.scan(ComputerStore.IPAD);
        checkout.scan(ComputerStore.APPLETV);
        checkout.scan(ComputerStore.APPLETV);

        checkout.scan(ComputerStore.MACBOOKPRO);
        checkout.scan(ComputerStore.MACBOOKPRO);
        checkout.scan(ComputerStore.MACBOOKPRO);
        checkout.scan(ComputerStore.MACBOOKPRO);
        checkout.scan(ComputerStore.VGA);
        checkout.scan(ComputerStore.VGA);
        checkout.scan(ComputerStore.IPAD);

        /*
        APPLE TV => 5 = $438
        IPAD => 6 = $2999.94
        MBP => 4 = $5599.96
        VGA => 2 = $0
        Total should be $9037.9
         */

        actualPrice = checkout.total();

        assertThat(actualPrice).isCloseTo(expectedPrice, within(BigDecimal.valueOf(0.01)));
    }

    @Test
    void buyNothingAtAll() {
        BigDecimal actualPrice = checkout.total();

        assertThat(actualPrice).isZero();
    }

    @Test
    void buyComputerItemsWithNoSpecialPrice() {
        Checkout localCheckout = new Checkout(Collections.singletonList(
                                PricingRuleCreator.createPricingRule(PricingRuleType.STANDARDUNITPRICING)));

        BigDecimal actualPrice;
        BigDecimal expectedPrice = BigDecimal.valueOf(2089.48);

        localCheckout.scan(ComputerStore.MACBOOKPRO);
        localCheckout.scan(ComputerStore.VGA);
        localCheckout.scan(ComputerStore.APPLETV);
        localCheckout.scan(ComputerStore.IPAD);

        actualPrice = localCheckout.total();

        assertThat(actualPrice).isCloseTo(expectedPrice, within(BigDecimal.valueOf(0.01)));
    }
}
