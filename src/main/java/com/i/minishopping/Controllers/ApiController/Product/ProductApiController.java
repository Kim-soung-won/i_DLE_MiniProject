package com.i.minishopping.Controllers.ApiController.Product;

import com.i.minishopping.DTO.Product.Request.AddProductRequest;
import com.i.minishopping.DTO.Product.Request.DeleteProductRequest;
import com.i.minishopping.DTO.Product.Request.UpdateProductRequest;
import com.i.minishopping.DTO.Product.Response.ProductDeleteResponse;
import com.i.minishopping.Domains.EMBEDDED.Created;
import com.i.minishopping.Domains.Product.Product;
import com.i.minishopping.Domains.User.UserInfo;
import com.i.minishopping.Services.Product.ProductService;
import com.i.minishopping.Services.User.UserInfoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class ProductApiController {
    private final ProductService productService;
    private final UserInfoService userService;

    @PostMapping("/api/POST/product")
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid AddProductRequest request,
                                               @AuthenticationPrincipal UserInfo user){
        UserInfo info = userService.findById(user.getId());
//        User user = userService.findById(authentication.getId());
        Created created = new Created(info, LocalDateTime.now());
        Product product = productService.saveOneProduct(request, created);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @DeleteMapping("/api/DELETE/product")
    public ResponseEntity<ProductDeleteResponse> deleteProduct(@RequestBody DeleteProductRequest request){
        Product product = productService.deleteOneProduct(request.getProduct_id());
        ProductDeleteResponse response = new ProductDeleteResponse(200, "success", product.getProduct_id(), product.getName());
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/api/PUT/product")
    public ResponseEntity<UpdateProductRequest> updateProduct(@RequestBody UpdateProductRequest request, HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("user");
        Created created = new Created(user, LocalDateTime.now());
        Product product = productService.updateOneProduct(request, created);
        return ResponseEntity.ok().body(request);
    }
}
