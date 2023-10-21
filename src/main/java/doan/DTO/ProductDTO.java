package doan.DTO;

import java.io.Serializable;

import doan.model.test_Product;

public class ProductDTO implements Serializable {
	private test_Product sanpham;
    private int quantity;


    public ProductDTO() {
    }

    public ProductDTO(test_Product sp) {
        this.sanpham = sp;
        this.quantity = 1;
    }

    public test_Product getSanpham() {
        return sanpham;
    }

    public void setSanpham(test_Product sanpham) {
        this.sanpham = sanpham;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}