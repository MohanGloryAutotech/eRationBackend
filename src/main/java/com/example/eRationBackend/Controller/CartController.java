package com.example.eRationBackend.Controller;


import com.example.eRationBackend.ControllerConfig.ControllerConfig;
import com.example.eRationBackend.generalResponce.GeneralResponse;
import com.example.eRationBackend.model.cart.Cart;
import com.example.eRationBackend.model.cart.request.AddCart;
import com.example.eRationBackend.model.cart.response.CartResponse;
import com.example.eRationBackend.model.cart.response.GetAllCart;
import com.example.eRationBackend.model.product.Product;
import com.example.eRationBackend.serviceImpl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController extends ControllerConfig {

    @Autowired
    CartServiceImpl cartService;

    @PostMapping("/cart/add/")
    public GeneralResponse<Boolean> addCart(@RequestBody AddCart record)
    {
        GeneralResponse<Boolean> response=null;
        try {

            if(record==null)
                throw new Exception("info can't be null");

            cartService.addCart(record);
            response=new GeneralResponse<>(true,"Cart added successfully",true,System.currentTimeMillis(), HttpStatus.CREATED);
        }catch (Exception e)
        {
            response=new GeneralResponse<>(false,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("/cart/get/{id}")
    public GeneralResponse<CartResponse> getCartDetailById(@PathVariable(name = "id")Long id)
    {
        GeneralResponse<CartResponse> response=null;
        try {

            if(id==null)
                throw new Exception("info can't be null");

            CartResponse cart = cartService.getCartById(id);
            if(cart!=null)
            response=new GeneralResponse<>(cart,"Cart fetched successfully",true,System.currentTimeMillis(), HttpStatus.OK);
            else
            response=new GeneralResponse<>(cart,"Cart fetched successfully",true,System.currentTimeMillis(), HttpStatus.OK);
        }catch (Exception e)
        {
            response=new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.OK);
        }

        return response;
    }

    @GetMapping("/cart/getAllByCustomerId/{id}")
    public GeneralResponse<GetAllCart> getCartByCustomerId(@PathVariable(name = "id")Long id)
    {
        GeneralResponse<GetAllCart> response=null;
        try {

            if(id==null)
                throw new Exception("info can't be null");

            GetAllCart cart = cartService.getAllCartByCustomerDetail(id);
            if(cart!=null)
                response=new GeneralResponse<>(cart,"Cart fetched successfully",true,System.currentTimeMillis(), HttpStatus.OK);
            else
                response=new GeneralResponse<>(cart,"Cart fetched successfully",true,System.currentTimeMillis(), HttpStatus.OK);
        }catch (Exception e)
        {
            response=new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.OK);
        }

        return response;
    }



}
