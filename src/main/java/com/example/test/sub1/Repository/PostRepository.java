package com.example.test.sub1.Repository;

import com.example.test.sub1.Dto.LoginUser;
import com.example.test.sub1.Dto.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends JpaRepository<Post,String> {


}
