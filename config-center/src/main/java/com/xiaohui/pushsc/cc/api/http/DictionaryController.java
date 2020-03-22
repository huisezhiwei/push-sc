package com.xiaohui.pushsc.cc.api.http;

import com.xiaohui.pushsc.cc.domain.dict.model.Dictionary;
import com.xiaohui.pushsc.cc.infranstructure.persist.jpa.DictionaryJPARepository;
import com.xiaohui.pushsc.protocol.source.DictionaryResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author xiaohui
 * create on 2020-03-15
 */
@RestController
@RequestMapping("/dict")
public class DictionaryController {

    @Autowired
    private DictionaryJPARepository dictionaryJPARepository;

    @GetMapping("/list/{code}")
    public ResponseEntity<List<DictionaryResource>> listByCode(@PathVariable String code) {

        List<Dictionary> dictionaryList = dictionaryJPARepository.findByDictCode(code);

        if (dictionaryList == null) {
            dictionaryList = new ArrayList<>();
        }

        return new ResponseEntity<>(dictionaryList.stream().map(Dictionary::toResource).collect(Collectors.toList()), HttpStatus.OK);

    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<DictionaryResource> detail(@PathVariable Long id) {
        Optional<Dictionary> optional = dictionaryJPARepository.findById(id);
        if (optional.isPresent()) {
            Dictionary dictionary = optional.get();
            return new ResponseEntity<>(dictionary.toResource(), HttpStatus.OK);
        } else {
            throw new IllegalStateException("字典数据不存在");
        }

    }

}
