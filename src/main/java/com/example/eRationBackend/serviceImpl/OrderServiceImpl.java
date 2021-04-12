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
import com.example.eRationBackend.model.order.request.OrderDataResponse;
import com.example.eRationBackend.model.order.request.OrderMastResponse;
import com.example.eRationBackend.model.product.Product;
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
            Product product = productService.getProductById(addOrder.getPId());
            ShopkeeperProduct shopkeeperProductExist = shopProductDao.getProductByShopAndProdctId(addOrder.getPId(),shopkeeper.getId());
            if(shopkeeperProductExist==null)
                continue;

            //count the amt
            amt +=shopkeeperProductExist.getPrice()* addOrder.getQty();


            //create order data list
            OrderData orderData = new OrderData(addOrder);
            //orderData.setImg(product.getImg());
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

    public OrderMastResponse getOrderByid(Long id) throws Exception {
        OrderMast orderMastExist = orderMastDao.getOrderById(id);
        if(orderMastExist==null)
            throw new Exception("no record found");

        OrderMastResponse orderMastResponse = new OrderMastResponse(orderMastExist);
        List<OrderDataResponse> orderDataResponseList = new ArrayList<>();

        for(OrderData orderData:orderMastExist.getOrderDataList())
        {
            Product product = productService.getProductById(orderData.getPId());

            OrderDataResponse orderDataResponse = new OrderDataResponse(orderData,product);
            orderDataResponseList.add(orderDataResponse);
        }
        orderMastResponse.setOrderDataList(orderDataResponseList);

        return orderMastResponse;

        //return orderMastDao.getOrderById(id);
    }

    public List<OrderMast> getAllOrder() {
        return orderMastDao.getAllOrder();
    }
}
