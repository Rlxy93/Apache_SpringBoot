package cn.lixingyu.Apache.service.impl;

import cn.lixingyu.Apache.entity.Shop;
import cn.lixingyu.Apache.exception.ShopException;
import cn.lixingyu.Apache.exception.UserException;
import cn.lixingyu.Apache.repository.ShopRepository;
import cn.lixingyu.Apache.service.ShopService;
import cn.lixingyu.Apache.utils.ImageUtil;
import cn.lixingyu.Apache.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

/**
 * @author Lxxxxxxy
 * @time 2020/01/25 15:40
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public void applyShop(Shop shop, CommonsMultipartFile shopImage, String userUUID) {
        if (shop == null || shopImage == null) {
            throw new UserException("店铺信息错误！");
        }
        shopRepository.applyShop(shop);
        shopRepository.updateShopStatus(userUUID);
        shopRepository.changeRole(userUUID);
        addShopImg(shop, shopImage);
    }

    @Override
    public Shop getShopInfoShopUserId(String shopUserId) throws ShopException {
        if (shopUserId == null) {
            throw new ShopException("查询失败，请检查是否已经拥有店铺！");
        }
        return shopRepository.getShopInfoShopUserId(shopUserId);
    }

    @Override
    public void editShopInfo(Shop shop, CommonsMultipartFile shopImage) throws ShopException {
        if (shop == null) {
            throw new ShopException("修改店铺信息失败！");
        }
        if (shop.getShopUserId() == null) {
            throw new ShopException("修改店铺信息失败！");
        }
        shopRepository.editShopInfo(shop);
        addShopImg(shop, shopImage);
    }

    @Override
    public List<Shop> queryAllShop() {
        return shopRepository.queryAllShop();
    }

    @Override
    public Shop queryShopName(String shopId) throws ShopException {
        if(shopId == null){
            throw new ShopException("获取店铺信息失败！");
        }
        Shop shop = shopRepository.queryShopName(shopId);
        return shop;
    }

    //把图片文件保存到本地
    public void addShopImg(Shop shop, CommonsMultipartFile shopImg) {
        String dest = PathUtil.getShopImgPath(shop.getShopId());
        ImageUtil.generateThumbnail(shopImg, dest);
    }
}
