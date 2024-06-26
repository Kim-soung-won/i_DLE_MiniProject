package com.i.minishopping.Services.User;

import com.i.minishopping.Domains.ENUM.DOIT;
import com.i.minishopping.Domains.User.Cart;
import com.i.minishopping.Domains.User.Love;
import com.i.minishopping.Domains.User.UserInfo;
import com.i.minishopping.Domains.User.UserAccount;
import com.i.minishopping.Repositorys.User.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final LoveRepository loveRepository;
    private final CartRepository cartRepository;
    private final UserLogService userLogService;

    @Transactional
    public void setSession(String email, HttpSession session){
        UserAccount account = findByEmail(email);
        UserInfo user = findById(account.getId());
        if(user==null){
            return;
        }
        account.updateLastLogin(LocalDateTime.now());
        List<Love> loves = findLoveByUserId(account.getId());
        System.out.println("loves = " + loves);
        List<Cart> carts = findCartByUserId(account.getId());
        session.setAttribute("user", user);
        session.setAttribute("loves", loves);
        session.setAttribute("carts", carts);
        session.setMaxInactiveInterval(60*60*24*30);  //세션 유효시간 결정(초단위)
        userLogService.saveUserLog(user.getId(), user.getName(), DOIT.LOGIN);
    }

    @Transactional(readOnly = true)
    public UserAccount findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public UserInfo findById(Long id){
        return userInfoRepository.findById(id)
                .filter(m -> m.getId().equals(id))
                .orElse(null);
    }
    @Transactional(readOnly = true)
    public List<Love> findLoveByUserId(Long id){
        return loveRepository.findByUserId(id);
    }
    @Transactional(readOnly = true)
    public List<Cart> findCartByUserId(Long id){
        return cartRepository.findByUserId(id);
    }
}
