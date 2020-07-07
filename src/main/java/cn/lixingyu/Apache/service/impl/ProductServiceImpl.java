package cn.lixingyu.Apache.service.impl;

import cn.lixingyu.Apache.entity.Product;
import cn.lixingyu.Apache.exception.ProductException;
import cn.lixingyu.Apache.repository.ProductRepository;
import cn.lixingyu.Apache.service.ProductService;
import cn.lixingyu.Apache.utils.ImageUtil;
import cn.lixingyu.Apache.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

/**
 * @author Lxxxxxxy
 * @time 2020/01/31 16:37
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> queryAllProductShopId(String shopId) {
        return productRepository.queryAllProductShopId(shopId);
    }

    @Override
    public void addProduct(Product product, CommonsMultipartFile productImage) throws ProductException {
        if(product == null){
            throw new ProductException("商品信息为空！");
        }
        productRepository.addProduct(product);
        addProductImg(product,productImage);
    }

    @Override
    public void deleteProduct(String productId) throws ProductException {
        if(productId == null){
            throw new ProductException("商品信息为空！");
        }
        productRepository.deleteProduct(productId);
    }

    @Override
    public List<Product> searchProduct(String searchContent) throws ProductException {
        if(searchContent == null){
            throw new ProductException("搜索内容为空！");
        }
        List<Product> products = productRepository.searchProduct(searchContent);
        return products;
    }

    @Override
    public List<Product> queryAllProduct() {
        return productRepository.queryAllProduct();
    }

    @Override
    public Product queryProduct(String productId) throws ProductException {
        if(productId == null){
            throw new ProductException("商品信息为空！");
        }
        Product product = productRepository.queryProduct(productId);
        return product;
    }

    @Override
    public List<Product> queryProductSmallCategoryId(String smallCategoryId) throws ProductException {
        if(smallCategoryId == null){
            throw new ProductException("商品分类为空！");
        }
        return productRepository.queryProductSmallCategoryId(smallCategoryId);
    }

    //把图片文件保存到本地
    public void addProductImg(Product product, CommonsMultipartFile shopImg) {
        String dest = PathUtil.getProductImgPath(String.valueOf(product.getProductId()));
        ImageUtil.generateThumbnail(shopImg, dest);
    }
}
