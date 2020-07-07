package cn.lixingyu.Apache.entity;

/**
 * @author Lxxxxxxy
 * @time 2020/01/31 16:34
 */
public class Product {
    private String productId;
    private String productShopId;
    private Integer productCategory;
    private String smallCategoryName;
    private Float productPrice;
    private Integer productRemaining;
    private String productDesc;
    private String productName;

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productShopId='" + productShopId + '\'' +
                ", productCategory=" + productCategory +
                ", smallCategoryName='" + smallCategoryName + '\'' +
                ", productPrice=" + productPrice +
                ", productRemaining=" + productRemaining +
                ", productDesc='" + productDesc + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductShopId() {
        return productShopId;
    }

    public void setProductShopId(String productShopId) {
        this.productShopId = productShopId;
    }

    public Integer getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Integer productCategory) {
        this.productCategory = productCategory;
    }

    public String getSmallCategoryName() {
        return smallCategoryName;
    }

    public void setSmallCategoryName(String smallCategoryName) {
        this.smallCategoryName = smallCategoryName;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductRemaining() {
        return productRemaining;
    }

    public void setProductRemaining(Integer productRemaining) {
        this.productRemaining = productRemaining;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Product() {
    }

    public Product(String productId, String productShopId, Integer productCategory, String smallCategoryName, Float productPrice, Integer productRemaining, String productDesc, String productName) {
        this.productId = productId;
        this.productShopId = productShopId;
        this.productCategory = productCategory;
        this.smallCategoryName = smallCategoryName;
        this.productPrice = productPrice;
        this.productRemaining = productRemaining;
        this.productDesc = productDesc;
        this.productName = productName;
    }
}
