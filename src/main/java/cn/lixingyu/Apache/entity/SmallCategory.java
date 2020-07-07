package cn.lixingyu.Apache.entity;

/**
 * @author Lxxxxxxy
 * @time 2020/01/30 18:15
 */
public class SmallCategory {
    private Integer smallCategoryId;
    private Integer bigCategoryId;
    private String bigCategoryName;
    private String smallCategoryName;

    public SmallCategory() {
    }

    @Override
    public String toString() {
        return "SmallCategory{" +
                "smallCategoryId=" + smallCategoryId +
                ", bigCategoryId=" + bigCategoryId +
                ", bigCategoryName='" + bigCategoryName + '\'' +
                ", smallCategoryName='" + smallCategoryName + '\'' +
                '}';
    }

    public Integer getSmallCategoryId() {
        return smallCategoryId;
    }

    public void setSmallCategoryId(Integer smallCategoryId) {
        this.smallCategoryId = smallCategoryId;
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

    public String getSmallCategoryName() {
        return smallCategoryName;
    }

    public void setSmallCategoryName(String smallCategoryName) {
        this.smallCategoryName = smallCategoryName;
    }

    public SmallCategory(Integer smallCategoryId, Integer bigCategoryId, String bigCategoryName, String smallCategoryName) {
        this.smallCategoryId = smallCategoryId;
        this.bigCategoryId = bigCategoryId;
        this.bigCategoryName = bigCategoryName;
        this.smallCategoryName = smallCategoryName;
    }
}
