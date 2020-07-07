package cn.lixingyu.Apache.service;

import cn.lixingyu.Apache.entity.BigCategory;
import cn.lixingyu.Apache.entity.SmallCategory;
import cn.lixingyu.Apache.exception.CategoryException;

import java.util.List;

/**
 * @author Lxxxxxxy
 * @time 2020/01/29 20:44
 */
public interface CategoryService {

    List<BigCategory> queryAllBigCategory();

    void addBigCategory(String bigCategoryName) throws CategoryException;

    void addSmallCategory(SmallCategory smallCategory) throws CategoryException;

    List<SmallCategory> queryAllSmallCategory();

    SmallCategory querySmallCategoryId(Integer id) throws CategoryException;

    void deleteSmallCategory(Integer id) throws CategoryException;
}
