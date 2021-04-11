package com.example.eRationBackend.Controller;

import com.example.eRationBackend.ControllerConfig.ControllerConfig;
import com.example.eRationBackend.generalResponce.GeneralResponse;
import com.example.eRationBackend.model.cart.request.AddCart;
import com.example.eRationBackend.model.order.OrderMast;
import com.example.eRationBackend.model.order.request.AddOrder;
import com.example.eRationBackend.serviceImpl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController extends ControllerConfig {

    @Autowired
    OrderServiceImpl orderService;


    @PostMapping("/order/add")
    public GeneralResponse<Boolean> addOrder(@RequestBody List<AddOrder> record)
    {
        GeneralResponse<Boolean> response=null;
        try {

            if(record==null)
                throw new Exception("info can't be null");

            orderService.addOrder(record);
            response=new GeneralResponse<>(true,"Order added successfully",true,System.currentTimeMillis(), HttpStatus.OK);
        }catch (Exception e)
        {
            response=new GeneralResponse<>(false,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }


    @GetMapping("/order/getBy/all")
    public GeneralResponse<List<OrderMast>> getAllOrder()
    {
        GeneralResponse<List<OrderMast>> response=null;
        try {


            List<OrderMast> orderMastList = orderService.getAllOrder();
            if(orderMastList.isEmpty())
            {
                response=new GeneralResponse<>(orderMastList,"data not found",false,System.currentTimeMillis(), HttpStatus.OK);
            }
            else
                response=new GeneralResponse<>(orderMastList,"Order found successfully",true,System.currentTimeMillis(), HttpStatus.OK);
        }catch (Exception e)
        {
            e.printStackTrace();
            response=new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }



    @GetMapping("/order/getBy/{cId}")
    public GeneralResponse<List<OrderMast>> getOrderByCid(@PathVariable(name = "cId")Long id)
    {
        GeneralResponse<List<OrderMast>> response=null;
        try {

            if(id==null)
                throw new Exception("info can't be null");

            List<OrderMast> orderMastList = orderService.getOrderByCid(id);
            if(orderMastList.isEmpty())
            {
                response=new GeneralResponse<>(orderMastList,"data not found",false,System.currentTimeMillis(), HttpStatus.OK);
            }
            else
            response=new GeneralResponse<>(orderMastList,"Order found successfully",true,System.currentTimeMillis(), HttpStatus.OK);
        }catch (Exception e)
        {
            e.printStackTrace();
            response=new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }



    @GetMapping("/order/getByOrder/{id}")
    public GeneralResponse<OrderMast> getOrderByid(@PathVariable(name = "id")Long id)
    {
        GeneralResponse<OrderMast> response=null;
        try {

            if(id==null)
                throw new Exception("info can't be null");

            OrderMast orderMastList = orderService.getOrderByid(id);
            if(orderMastList==null)
            {
                response=new GeneralResponse<>(orderMastList,"data not found",false,System.currentTimeMillis(), HttpStatus.OK);
            }
            else
                response=new GeneralResponse<>(orderMastList,"Order found successfully",true,System.currentTimeMillis(), HttpStatus.OK);
        }catch (Exception e)
        {
            e.printStackTrace();
            response=new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }



}
