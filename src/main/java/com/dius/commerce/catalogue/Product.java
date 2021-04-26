package com.dius.commerce.catalogue;

import java.math.BigDecimal;

/**
 * This class is immutable.
 */
public final class Product {
    private final String sku;
    private final String name;
    private final BigDecimal price;

    public Product(String sku, String name, double price) {
        this.sku = sku;
        this.name = name;
        this.price = BigDecimal.valueOf(price);
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product that = (Product) o;

        return sku.equals(that.sku);
    }

    @Override
    public int hashCode() {
        return sku.hashCode();
    }

    @Override
    public String toString() {
        return "ComputerItem [" +
                "sku=" + sku +
                ", name=" + name +
                ", price=" + price + "]";
    }
}
