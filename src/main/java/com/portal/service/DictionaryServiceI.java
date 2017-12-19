package com.portal.service;

import com.portal.model.Dictionary;

import java.util.List;

/**
 * Created by FGZ on 2017/12/4.
 */
public interface DictionaryServiceI {
    public Dictionary get(Integer id);
    public List<Dictionary> getAll();
}
