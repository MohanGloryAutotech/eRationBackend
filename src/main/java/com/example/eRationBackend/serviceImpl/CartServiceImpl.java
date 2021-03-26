package com.example.eRationBackend.serviceImpl;

import com.example.eRationBackend.dao.cart.CartDao;
import com.example.eRationBackend.dao.cart.CartItemDao;
import com.example.eRationBackend.dao.customer.CustomerDao;
import com.example.eRationBackend.model.cart.Cart;
import com.example.eRationBackend.model.cart.CartItem;
import com.example.eRationBackend.model.cart.request.AddCart;
import com.example.eRationBackend.model.cart.request.ProductList;
import com.example.eRationBackend.model.cart.response.CartResponse;
import com.example.eRationBackend.model.cart.response.GetAllCart;
import com.example.eRationBackend.model.customer.Customer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("cartServiceImpl")
public class CartServiceImpl {

    @Autowired
    CartDao cartDao;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    CartItemDao cartItemDao;


    public void addCart(AddCart record) {

        Cart cart = new Cart(record);
        Cart x = cartDao.save(cart);

        List<CartItem> cartItemList =new ArrayList<>();
        for(ProductList productList:record.getProductList())
        {
            CartItem cartItem = new CartItem(productList);
            cartItem.setControlId(x.getId());
            cartItemList.add(cartItem);
        }

        cartItemDao.saveAll(cartItemList);



    }

    public CartResponse getCartById(Long id) throws Exception {
        Optional<Cart> cart = cartDao.findById(id);
        if(cart.isPresent())
            throw new Exception("no record found");

        List<CartItem> cartItemList = cartItemDao.findByControlId(cart.get().getId());
        if(cartItemList.isEmpty())
            throw new Exception("no record found");

        CartResponse cartResponse = new CartResponse(cart.get(),cartItemList);
        return cartResponse;

    }

    public GetAllCart getAllCartByCustomerDetail(Long id) throws Exception {
        //check customer exist or not

        GetAllCart getAllCart =new GetAllCart();

        Customer customerExist =customerDao.getRationCardDetailById(id);
        if(customerExist==null)
            throw new Exception("no record found");

        List<CartResponse> list = new ArrayList<>();
        List<Cart> cartList = cartDao.getAllCartByCustomerId(id);
        for(Cart cart:cartList)
        {
            List<CartItem> cartItemList = cartItemDao.findByControlId(cart.getId());
            if(cart!=null && !cartItemList.isEmpty())
                list.add(new CartResponse(cart,cartItemList));
        }
        if(list.isEmpty())
            throw new Exception("no record found");

        getAllCart.setCartResponseList(list);
        return getAllCart;

    }
}
