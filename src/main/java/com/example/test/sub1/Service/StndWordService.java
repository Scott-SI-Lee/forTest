package com.example.test.sub1.Service;

import com.example.test.sub1.Dto.StndWord;

import java.util.List;

public interface StndWordService {

    List<StndWord> getStndWordList(StndWord dto);

    StndWord getStndWordById(StndWord dto);

    List<StndWord> getStndWordByEngNm(String wordEngNm);

    StndWord addStndWord(StndWord dto);

    void delStndWord(List<StndWord> list);

    void uptStndWord(StndWord dto);
}
