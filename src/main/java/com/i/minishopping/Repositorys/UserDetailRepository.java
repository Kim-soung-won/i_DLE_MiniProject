package com.i.minishopping.Repositorys;

import com.i.minishopping.Domains.User.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
}