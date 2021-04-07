package com.example.eRationBackend.serviceImpl;

import com.example.eRationBackend.dao.customer.CustomerDao;
import com.example.eRationBackend.dao.order.OrderDataDao;
import com.example.eRationBackend.dao.order.OrderMastDao;
import com.example.eRationBackend.dao.product.ShopProductDao;
import com.example.eRationBackend.model.Shopkeeper;
import com.example.eRationBackend.model.customer.Customer;
import com.example.eRationBackend.model.order.OrderData;
import com.example.eRationBackend.model.order.OrderMast;
import com.example.eRationBackend.model.order.request.AddOrder;
import com.example.eRationBackend.model.product.ShopkeeperProduct;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("orderServiceImpl")
public class OrderServiceImpl {


    @Autowired
    OrderDataDao orderDataDao;

    @Autowired
    OrderMastDao orderMastDao;

    @Autowired
    ShopProductDao shopProductDao;


    @Autowired
    ShopkeepeServiceImpl shopkeepeService;

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    CustomerDao customerDao;

    public void addOrder(List<AddOrder> record) {

        List<ShopkeeperProduct> shopkeeperProductList = new ArrayList<>();

        OrderMast orderMast = new OrderMast();
        Customer customer = customerDao.getRationCardDetailById(record.get(0).getCId());
        Shopkeeper shopkeeper = shopkeepeService.getShopkeeperById(customer.getShopId());
        Double amt =0.0;
        List<OrderData> orderDataList =new ArrayList<>();

        for(AddOrder addOrder:record)
        {
            ShopkeeperProduct shopkeeperProductExist = shopProductDao.getProductByShopAndProdctId(addOrder.getPId(),shopkeeper.getId());
            if(shopkeeperProductExist==null)
                continue;

            //count the amt
            amt +=shopkeeperProductExist.getPrice();


            //create order data list
            OrderData orderData = new OrderData(addOrder);
            orderDataList.add(orderData);


        }

        //create the order
        orderMast.setAmt(amt);
        orderMast.setAppointmentDate(record.get(0).getAppointmentDateTime());
        orderMast.setIsAppointment(true);
        orderMast.setCId(record.get(0).getCId());
        orderMast.setStatus(false);
        orderMast.setOrderDataList(orderDataList);

        orderMastDao.save(orderMast);




    }

    public List<OrderMast> getOrderByCid(Long id) {
        return orderMastDao.getOrderByCid(id);
    }

    public OrderMast getOrderByid(Long id) {
        return orderMastDao.getOrderById(id);
    }
}