package com.example.eRationBackend.serviceImpl;

import com.example.eRationBackend.dao.customer.CustomerDao;
import com.example.eRationBackend.dao.customer.MemberDao;
import com.example.eRationBackend.model.Shopkeeper;
import com.example.eRationBackend.model.customer.Customer;
import com.example.eRationBackend.model.customer.Member;
import com.example.eRationBackend.model.customer.request.AddRationCard;
import com.example.eRationBackend.model.otpVerification.OtpVerification;
import com.example.eRationBackend.service.SendOtpMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service("rationServiceImpl")
public class RationServiceImpl {

    @Autowired
    ShopkeepeServiceImpl shopkeepeService;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    MemberDao memberDao;

    public void saveRationDetail(AddRationCard addRationCard) {

        try {
            //check the ration number exist
            Customer rationExist = customerDao.getRationByNumber(addRationCard.getCustomer().getRationNumber());

            if(rationExist!=null)
                throw new Exception("already ration number exist");

            //shopkeeper exist ??
            Shopkeeper shopkeeperExist =shopkeepeService.getShopkeeperById(addRationCard.getCustomer().getShopId());
            if(shopkeeperExist==null)
                throw new Exception("no shopkeeper found");

            Customer customer = new Customer(addRationCard.getCustomer());
            Customer x = customerDao.save(customer);

            List<Member> memberList = new ArrayList<>();
            for (Member m : addRationCard.getMemberList()) {
                Member member = new Member(m);
                member.setControlId(x.getId());
                memberList.add(member);
            }

            memberDao.saveAll(memberList);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public AddRationCard getRationDetailByRationId(Long id) throws Exception {
        Customer customerExist = customerDao.getRationByNumber(id);
        if(customerExist==null)
            throw new Exception("customer not found ");

        List<Member> memberList=memberDao.getAllMemberByControlId(customerExist.getId());

        AddRationCard addRationCard = new AddRationCard(customerExist,memberList);
        return addRationCard;
    }

    public AddRationCard getRationDetailById(Long id) throws Exception {

        Customer customerExist = customerDao.getRationCardDetailById(id);
        if(customerExist==null)
            throw new Exception("customer not found");

        List<Member> memberList=memberDao.getAllMemberByControlId(customerExist.getId());

        AddRationCard addRationCard = new AddRationCard(customerExist,memberList);
        return addRationCard;
    }

    public List<AddRationCard> getAlRationCard() throws Exception {
        List<AddRationCard> list = new ArrayList<>();
        List<Customer> customerList = customerDao.getAllCustomer();
        for(Customer customer:customerList)
        {
            List<Member> memberList=memberDao.getAllMemberByControlId(customer.getId());

            if(memberList.isEmpty())
                continue;

            list.add(new AddRationCard(customer,memberList));
        }
        if(list.isEmpty())
            throw new Exception("no record found");
        return list;
    }

    public OtpVerification getOtpForRationCardNo(String ratioNo) throws Exception {
        Customer customerExist = customerDao.getRationByNumber(Long.parseLong(ratioNo));

        if(customerExist==null)
            throw new Exception("no customer found");

        Random rand =new Random();
        String id = String.format("%04d", rand.nextInt(10000));
        do {
            //to generate the positive number
            id = String.format("%04d", rand.nextInt(10000));
        }while (Long.parseLong(id)<0);

        //send to mail as well
        SendOtpMail sendOtpMail = new SendOtpMail(customerExist.getEmail(),"Otp from the Eration","Otp for ration verifiation is:"+id);
        sendOtpMail.sendMail();


        //otp genrated
        OtpVerification otpVerification =new OtpVerification(id);
        return otpVerification;
    }
}
