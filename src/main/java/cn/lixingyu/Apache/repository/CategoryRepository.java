package cn.lixingyu.Apache.repository;

import cn.lixingyu.Apache.entity.BigCategory;
import cn.lixingyu.Apache.entity.SmallCategory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lxxxxxxy
 * @time 2020/01/29 20:44
 */
@Repository
public interface CategoryRepository {

    @Select("SELECT DISTINCT(BIG_CATEGORY_ID),BIG_CATEGORY_NAME FROM BIG_CATEGORY")
    List<BigCategory> queryAllBigCategory();

    @Insert("INSERT INTO BIG_CATEGORY (BIG_CATEGORY_ID,BIG_CATEGORY_NAME) VALUES (null,#{bigCategoryName})")
    void addBigCategory(String bigCategoryName);

    @Insert("INSERT INTO SMALL_CATEGORY (SMALL_CATEGORY_ID,BIG_CATEGORY_ID,SMALL_CATEGORY_NAME)" +
            " VALUES(null,#{bigCategoryId},#{smallCategoryName})")
    void addSmallCategory(SmallCategory smallCategory);

    @Select("SELECT DISTINCT(BIG_CATEGORY.BIG_CATEGORY_NAME)," +
            "SMALL_CATEGORY.SMALL_CATEGORY_ID," +
            "SMALL_CATEGORY.BIG_CATEGORY_ID," +
            "SMALL_CATEGORY.SMALL_CATEGORY_NAME FROM SMALL_CATEGORY,BIG_CATEGORY " +
            "WHERE SMALL_CATEGORY.BIG_CATEGORY_ID = BIG_CATEGORY.BIG_CATEGORY_ID")
    List<SmallCategory> queryAllSmallCategory();

    @Select("SELECT DISTINCT(SMALL_CATEGORY_ID),BIG_CATEGORY_ID,SMALL_CATEGORY_NAME FROM SMALL_CATEGORY WHERE SMALL_CATEGORY_ID = #{id}")
    SmallCategory querySmallCategoryId(Integer id);

    @Delete("DELETE FROM SMALL_CATEGORY WHERE SMALL_CATEGORY_ID = #{id}")
    void deleteSmallCategory(Integer id);
}
