package ru.aplana.util;

import java.util.Objects;

public class Product {
    private String productName;
    private String productPrice;

    public Product(String productName, String productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(productName, product.productName) &&
                Objects.equals(productPrice, product.productPrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productName, productPrice);
    }

    @Override
    public String toString() {
        return productName + " " + productPrice;
    }
}
