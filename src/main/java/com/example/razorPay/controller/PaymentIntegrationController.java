package com.example.razorPay.controller;

import com.example.razorPay.entity.OrderRequest;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentIntegrationController {

    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;

    @PostMapping("/create-order")
    public String createOrder(@RequestBody OrderRequest orderRequest) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient(keyId, keySecret);

        JSONObject orderDetails = new JSONObject();
        orderDetails.put("amount", orderRequest.getAmount() * 100);
        orderDetails.put("currency", "INR");
        orderDetails.put("receipt", "txn_123456");

        Order order = razorpay.orders.create(orderDetails);
        return order.toString();
    }
}
