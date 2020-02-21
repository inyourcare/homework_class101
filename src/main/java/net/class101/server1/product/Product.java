package net.class101.server1.product;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class Product implements ProductInterface {

    int productNumber;
    String productName;
    int productPrice;
    int productStockCount;
    String kind;

    public Product(int productNumber, String productName, int productPrice, int productStockCount) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStockCount = productStockCount;
    }

    @Override
    public String toString() {
        String split = "    ";
        StringBuilder sb = new StringBuilder();
        sb
                .append(this.productNumber)
                .append(split)
                .append(this.productName)
                .append(split)
                .append(this.productPrice)
                .append(split)
                .append(this.productStockCount);
        return sb.toString();
    }
}
