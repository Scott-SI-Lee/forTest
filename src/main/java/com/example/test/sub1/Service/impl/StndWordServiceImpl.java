package com.example.test.sub1.Service.impl;

import com.example.test.sub1.Dto.StndWord;
import com.example.test.sub1.Repository.StndWordRepository;
import com.example.test.sub1.Service.StndWordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class StndWordServiceImpl implements StndWordService {

    private final StndWordRepository stndWordRepository;

    public List<StndWord> getStndWordList(StndWord dto) {
        return stndWordRepository.findAll(Sort.by(Sort.Direction.DESC,"regDtm"));
    }

    @Override
    public StndWord getStndWordById(StndWord dto) {
        return stndWordRepository.getReferenceById(dto.getWordId());
    }

    public List<StndWord> getStndWordByEngNm(String wordEngNm) {
        return stndWordRepository.getReferenceByWordEngNm(wordEngNm);
    }

    public StndWord addStndWord(StndWord dto) {
        UUID uuid4 = UUID.randomUUID();
        dto.setWordId(uuid4.toString());
        return stndWordRepository.save(dto);
    }

    @Override
    public void delStndWord(List<StndWord> list) {
        stndWordRepository.deleteAll(list);
    }

    @Override
    public void uptStndWord(StndWord dto) {
        stndWordRepository.save(dto);
    }
}
