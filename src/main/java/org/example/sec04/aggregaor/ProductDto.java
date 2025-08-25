package org.example.sec04.aggregaor;

public class ProductDto {
    private int id;
    private String product;
    private int rating;
    public ProductDto(int id, String product, int rating){
        this.id = id;
        this.product = product;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", rating=" + rating +
                '}';
    }
}
