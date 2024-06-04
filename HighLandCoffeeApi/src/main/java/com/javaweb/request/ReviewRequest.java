package com.javaweb.request;

public class ReviewRequest {
    private Long order_id;
    private String product_id;
    private String content;
    private int star;
    public Long getOrder_id() {
        return order_id;
    }
    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }
    public String getProduct_id() {
        return product_id;
    }
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getStar() {
        return star;
    }
    public void setStar(int star) {
        this.star = star;
    }
    public ReviewRequest(Long order_id, String product_id, String content, int star) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.content = content;
        this.star = star;
    }
    public ReviewRequest() {
    }

    

}
