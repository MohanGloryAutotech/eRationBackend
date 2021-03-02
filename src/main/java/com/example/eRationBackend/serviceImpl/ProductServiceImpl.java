package com.example.eRationBackend.serviceImpl;

import com.example.eRationBackend.dao.product.ProductDao;
import com.example.eRationBackend.dao.product.ShopProductDao;
import com.example.eRationBackend.model.product.Product;
import com.example.eRationBackend.model.product.ShopkeeperProduct;
import com.example.eRationBackend.model.product.request.AddProductShop;
import com.example.eRationBackend.model.product.request.GetAllProduct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("productService")
public class ProductServiceImpl {


    @Autowired
    ProductDao productDao;

    @Autowired
    ShopProductDao shopProductDao;


    public void addProduct(Product product) throws Exception {
        Product productExist = productDao.getProductByName(product.getProductName());
        if(productExist!=null)
            throw new Exception("product already exist");

        productDao.save(product);

    }

    public Product getProductById(Long id) {
        return productDao.getProductById(id);
    }

    public void addProductInShop(AddProductShop addProductShop) throws Exception {

        Product productExist = productDao.getProductById(addProductShop.getPid());
        if(productExist==null)
            throw new Exception("no product data found");


        //check in shop prodct is exist
        ShopkeeperProduct shopkeeperProductExist = shopProductDao.getProductByShopAndProdctId(addProductShop.getPid(),addProductShop.getShopId());

        if(shopkeeperProductExist==null)
        {
            //create the record
            ShopkeeperProduct product = new ShopkeeperProduct(addProductShop);
            shopProductDao.save(product);
        }
        else {
            //update the record with existing
            ShopkeeperProduct shopkeeperProduct =new ShopkeeperProduct(shopkeeperProductExist);

            shopkeeperProduct.setQty(shopkeeperProduct.getQty()+addProductShop.getQty());
            shopkeeperProduct.setPrice(addProductShop.getPrice());

            shopProductDao.saveAndFlush(shopkeeperProduct);
        }
    }

    public List<GetAllProduct> getProductListByShoId(Long shopId) {
        List<GetAllProduct> getAllProducts =new ArrayList<>();

        List<ShopkeeperProduct> shopkeeperProductList = shopProductDao.getAllProductByShopId(shopId);

        for(ShopkeeperProduct shopkeeperProduct:shopkeeperProductList)
        {
            Product product = productDao.getProductById(shopkeeperProduct.getPid());
            if(product==null)
                continue;

            getAllProducts.add(new GetAllProduct(shopkeeperProduct,product));
        }
        return getAllProducts;
    }

    public List<Product> getAllProduct() {
        return productDao.getALlProduct();
    }
}
