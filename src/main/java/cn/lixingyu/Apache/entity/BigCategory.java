package cn.lixingyu.Apache.entity;

/**
 * @author Lxxxxxxy
 * @time 2020/01/29 20:32
 */
public class BigCategory {
    private Integer bigCategoryId;
    private String bigCategoryName;

    @Override
    public String toString() {
        return "BigCategory{" +
                "bigCategoryId=" + bigCategoryId +
                ", bigCategoryName='" + bigCategoryName + '\'' +
                '}';
    }

    public Integer getBigCategoryId() {
        return bigCategoryId;
    }

    public void setBigCategoryId(Integer bigCategoryId) {
        this.bigCategoryId = bigCategoryId;
    }

    public String getBigCategoryName() {
        return bigCategoryName;
    }

    public void setBigCategoryName(String bigCategoryName) {
        this.bigCategoryName = bigCategoryName;
    }

    public BigCategory() {
    }

    public BigCategory(Integer bigCategoryId, String bigCategoryName) {
        this.bigCategoryId = bigCategoryId;
        this.bigCategoryName = bigCategoryName;
    }
}
