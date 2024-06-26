package com.i.minishopping.DTOResponse.Product;

import com.i.minishopping.DTOResponse.Common.CommonResponse;
import com.i.minishopping.Domains.Product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductListResponse extends CommonResponse {
    private Long id;
    private String name;
    private int price;
    public ProductListResponse(int status, String msg, Product product){
        super(status,msg);
        this.id = product.getProduct_id();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
