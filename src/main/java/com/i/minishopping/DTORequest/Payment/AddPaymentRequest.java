package com.i.minishopping.DTORequest.Payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddPaymentRequest {
    private Long product_id;
    private int count;
    private int total_price;
    private String size;
    private String th_pnum;
    private String th_address;

    @Override
    public String toString() {
        return  "{product_id=" + product_id +
                ", count=" + count +
                ", total_price=" + total_price +
                ", size='" + size + '\'' +
                '}';
    }
}
