package com.example.demo.repository;

import com.example.demo.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository <Token, Integer> {
    Token findByAccountId(int id);
//    Token findByTokenId(int id);
//    Token removeTokensByAccountId(int id);
//    Token removeTokenByToken(String token);
}

