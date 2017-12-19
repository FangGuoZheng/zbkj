package com.portal.service.impl;

import com.portal.dao.DictionaryDaoI;
import com.portal.model.Dictionary;
import com.portal.service.DictionaryServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FGZ on 2017/12/4.
 */
@Service("DictionaryService")
public class DictionaryServiceImpl implements DictionaryServiceI {
    private DictionaryDaoI dictionaryDao;
    @Autowired
    public void setDictionaryDao(DictionaryDaoI dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    @Override
    public Dictionary get(Integer id) {
        return this.dictionaryDao.get(id);
    }

    @Override
    public List<Dictionary> getAll() {
        return this.dictionaryDao.getAll();
    }
}
