package com.example.test.sub1.Repository;

import com.example.test.sub1.Dto.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<LoginUser,String> {

    LoginUser getUserByUserNm(String userNm);
}
