package com.dius.commerce;

import com.dius.commerce.catalogue.Product;

/**
 * This is implemented with convenient usage in mind. No further thoughts
 * were considered for the purpose of this assignment.
 *
 * In more realistic setup, the source of computer items could be
 * from DB, file or any form of backend services
 */
public class ComputerStore {
    public static final Product IPAD = new Product("ipd", "Super iPad", 549.99);
    public static final Product MACBOOKPRO = new Product("mbp", "MacBook Pro", 1399.99);
    public static final Product APPLETV = new Product("atv", "Apple TV", 109.50);
    public static final Product VGA = new Product("vga", "VGA Adapter", 30.00);

    private ComputerStore() {}
}
