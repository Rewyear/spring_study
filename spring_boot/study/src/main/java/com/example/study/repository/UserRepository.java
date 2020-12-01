package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Query Method 생성
    // findBy***, JPA에서findBy를 접두사로 사용하게 되면 하기와 같이 뒤에오는 표기이 대하여 select하는 쿼리문을 수행하는 method 생성

    // select from user where account = ?
    Optional<User> findByAccount(String account);

    // select from user where email = ?
    Optional<User> findByEmail(String email);

    // select from user where account = ? and email = ?
    Optional<User> findByAccountAndEmail(String account, String email);
}
