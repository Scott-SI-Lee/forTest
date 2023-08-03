package com.example.test.sub1.Repository;

import com.example.test.sub1.Dto.StndWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StndWordRepository extends JpaRepository<StndWord,String> {

    List<StndWord> getReferenceByWordEngNm(String wordEngNm);
}
