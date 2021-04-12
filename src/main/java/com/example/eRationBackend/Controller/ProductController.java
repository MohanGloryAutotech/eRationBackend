package com.example.eRationBackend.Controller;

import com.example.eRationBackend.ControllerConfig.ControllerConfig;
import com.example.eRationBackend.generalResponce.GeneralResponse;
import com.example.eRationBackend.model.product.Product;
import com.example.eRationBackend.model.product.request.AddProductShop;
import com.example.eRationBackend.model.product.request.GetAllProduct;
import com.example.eRationBackend.serviceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController extends ControllerConfig {


    @Autowired
    ProductServiceImpl productService;

    @PostMapping("/product/add/")
    public GeneralResponse<Boolean> createProduct(@RequestBody Product product)
    {
        GeneralResponse<Boolean> response=null;
        try {

            if(product==null)
                throw new Exception("product info can't be null");

            productService.addProduct(product);
            response=new GeneralResponse<>(true,"Product added successfully",true,System.currentTimeMillis(), HttpStatus.CREATED);
        }catch (Exception e)
        {
            response=new GeneralResponse<>(false,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }
    @GetMapping("/product/get/all/")
    public GeneralResponse<List<Product>> getAllProduct()
    {
        GeneralResponse<List<Product>> response=null;
        try {

            List<Product> list = productService.getAllProduct();
            if(list.isEmpty())
            {
                throw new Exception("no product found");
            }
            response=new GeneralResponse<>(list,"Product fetched successfully",true,System.currentTimeMillis(), HttpStatus.CREATED);

        }catch (Exception e)
        {
            response=new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("/product/get/{id}")
    public GeneralResponse<Product> getProductById(@PathVariable(name = "id")Long id)
    {
        GeneralResponse<Product> response=null;
        try {

            if(id==null)
                throw new Exception("id info can't be null");

            Product product = productService.getProductById(id);
            if(product==null)
            {
                response=new GeneralResponse<>(null,"Product not found",false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
            }
            else {
                response=new GeneralResponse<>(product,"Product added successfully",true,System.currentTimeMillis(), HttpStatus.CREATED);
            }
        }catch (Exception e)
        {
            response=new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @PostMapping("/product/add/shop/")
    public GeneralResponse<Boolean> addProductInShop(@RequestBody AddProductShop addProductShop)
    {
        GeneralResponse<Boolean> response=null;
        try {

            if(addProductShop==null)
                throw new Exception(" info can't be null");

            productService.addProductInShop(addProductShop);
            response=new GeneralResponse<>(true,"Product added successfully",true,System.currentTimeMillis(), HttpStatus.CREATED);

        }catch (Exception e)
        {
            response=new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("/product/getAll/byShopId/{shopId}")
    public GeneralResponse<List<GetAllProduct>> getAllProductByShopId(@PathVariable(name = "shopId")Long shopId)
    {
        GeneralResponse<List<GetAllProduct>> response=null;
        try {

            if (shopId == null)
                throw new Exception(" info can't be null");

            List<GetAllProduct> list = productService.getProductListByShoId(shopId);

            if (!list.isEmpty()) {
                response = new GeneralResponse<>(list, "Product fetched successfully", true, System.currentTimeMillis(), HttpStatus.FOUND);

            }else {
                response=new GeneralResponse<>(null,"Product not found",false,System.currentTimeMillis(), HttpStatus.FOUND);
            }


        }catch (Exception e)
        {
            response=new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

}
