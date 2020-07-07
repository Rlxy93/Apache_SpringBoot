package cn.lixingyu.Apache.service.impl;

import cn.lixingyu.Apache.entity.BigCategory;
import cn.lixingyu.Apache.entity.SmallCategory;
import cn.lixingyu.Apache.exception.CategoryException;
import cn.lixingyu.Apache.repository.CategoryRepository;
import cn.lixingyu.Apache.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lxxxxxxy
 * @time 2020/01/29 20:45
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<BigCategory> queryAllBigCategory() {
        List<BigCategory> bigCategories = categoryRepository.queryAllBigCategory();
        return bigCategories;
    }

    @Override
    public void addBigCategory(String bigCategoryName) throws CategoryException {
        if (bigCategoryName == "" || bigCategoryName == null) {
            throw new CategoryException("分类名为空！");
        }
        try {
            categoryRepository.addBigCategory(bigCategoryName);
        } catch (Exception e) {
            throw new CategoryException("分类名添加失败！请检查分类名是否已经存在！");
        }
    }

    @Override
    public void addSmallCategory(SmallCategory smallCategory) throws CategoryException {
        if(smallCategory == null){
            throw new CategoryException("分类信息为空！");
        }
        categoryRepository.addSmallCategory(smallCategory);
    }

    @Override
    public List<SmallCategory> queryAllSmallCategory() {
        return categoryRepository.queryAllSmallCategory();
    }

    @Override
    public SmallCategory querySmallCategoryId(Integer id) throws CategoryException {
        if(id == null){
            throw new CategoryException("分类id为空！");
        }
        SmallCategory smallCategory = categoryRepository.querySmallCategoryId(id);
        return smallCategory;
    }

    @Override
    public void deleteSmallCategory(Integer id) throws CategoryException {
        if(id == null){
            throw new CategoryException("分类id为空！");
        }
        categoryRepository.deleteSmallCategory(id);
    }
}
