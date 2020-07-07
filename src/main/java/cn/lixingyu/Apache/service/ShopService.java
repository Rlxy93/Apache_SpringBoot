package cn.lixingyu.Apache.service;

import cn.lixingyu.Apache.entity.Shop;
import cn.lixingyu.Apache.exception.ShopException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

/**
 * @author Lxxxxxxy
 * @time 2020/01/25 15:40
 */
public interface ShopService {

    void applyShop(Shop shop, CommonsMultipartFile shopImage,String userUUID);

    Shop getShopInfoShopUserId(String shopUserId) throws ShopException;

    void editShopInfo(Shop shop,CommonsMultipartFile shopImage) throws ShopException;

    List<Shop> queryAllShop();

    Shop queryShopName(String shopId) throws ShopException;
}
