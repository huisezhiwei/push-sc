package com.xiaohui.pushsc.cc.infranstructure.persist;

import com.xiaohui.pushsc.cc.domain.dict.model.Dictionary;
import com.xiaohui.pushsc.cc.domain.dict.model.DictionaryRepository;
import com.xiaohui.pushsc.cc.infranstructure.persist.jpa.DictionaryJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author xiaohui
 * create on 2020-03-15
 */
@Service
public class DictionaryRepositoryImpl implements DictionaryRepository {

    @Autowired
    private DictionaryJPARepository jpaRepository;

    @Override
    public Dictionary save(Dictionary client) {
        return jpaRepository.save(client);
    }

    @Override
    public void delete(Dictionary client) {
        jpaRepository.delete(client);
    }

    @Override
    public Optional<Dictionary> find(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Dictionary> findSet(String dictCode) {
        return jpaRepository.findByDictCode(dictCode);
    }

    @Override
    public List<Dictionary> findByParentId(Long id) {
        return jpaRepository.findByParentId(id);
    }

}
