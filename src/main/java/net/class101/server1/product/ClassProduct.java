package net.class101.server1.product;

import net.class101.server1.code.ProjectCode;

public class ClassProduct extends Product {
    public ClassProduct(int productNumber, String productName, int productPrice, int productStockCount) {
        super(productNumber, productName, productPrice, productStockCount);
        this.kind = ProjectCode.ProductKind.Class.getCode();
    }
}
