package com.example.test.sub1.Repository;

import com.example.test.sub1.Dto.StndWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StndWordRepository extends JpaRepository<StndWord,String> {


}
