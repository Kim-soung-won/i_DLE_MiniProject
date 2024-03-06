package com.i.minishopping.Services;

import com.i.minishopping.Domains.EMBEDDED.Created;
import com.i.minishopping.Domains.EMBEDDED.Love_key;
import com.i.minishopping.Domains.User.Love;
import com.i.minishopping.Domains.Product.Product;
import com.i.minishopping.Domains.User.User;
import com.i.minishopping.Repositorys.LoveRepository;
import com.i.minishopping.Services.Product.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoveService {
    private final LoveRepository loveRepository;
    private final ProductService productService;
    public int countByUserId(Product product){
        return loveRepository.countByUserId(product);
    }

    @Transactional
    public Love findById(Love_key key) {
        Love love = loveRepository.findById(key).orElse(null);
        return love;
    }
    @Transactional
    public Love saveLove(Love_key key){
        return loveRepository.save(
                Love.builder()
                .love_key(key)
                .created_at(LocalDateTime.now())
                .build());
    }
    @Transactional
    public Love clickLove(HttpSession session) {
        Product product = productService.findById(23505L);
        User user = (User) session.getAttribute("user");
        Created created = new Created(user, LocalDateTime.now());
        Love_key key = new Love_key(user, product);
        Love love = findById(key);
        if (love == null) {
            productService.count_Love(1, product.getProduct_id());
            return saveLove(key);
        } else {
            deleteLove(love.getLove_key());
            productService.count_Love(-1, product.getProduct_id());
            return null;
        }
    }
    @Transactional
    public void deleteLove(Love_key id){
        Love love = loveRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("not found: " + id));
        loveRepository.delete(love);
    }
}