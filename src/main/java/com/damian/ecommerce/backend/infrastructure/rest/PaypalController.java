package com.damian.ecommerce.backend.infrastructure.rest;

import com.damian.ecommerce.backend.domain.model.DataPayment;
import com.damian.ecommerce.backend.domain.model.UrlPaypalResponse;
import com.damian.ecommerce.backend.infrastructure.Service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1/payments")
public class PaypalController {
    private final PaypalService paypalService;
    private final String SUCCESS_URL = "http://localhost:8085/api/v1/payments/success";
    private final String CANCEL_URL = "http://localhost:8085/api/v1/payments/cancel";

    @PostMapping
    public UrlPaypalResponse createPayment(@RequestBody DataPayment dataPayment) throws PayPalRESTException {
        Payment payment = paypalService.createPayment(
            Double.valueOf(dataPayment.getAmount()),
            dataPayment.getCurrency(),
            dataPayment.getMethod(),
            "SALE",
            dataPayment.getDescription(),
            CANCEL_URL,
            SUCCESS_URL
        );
        for (Links links : payment.getLinks()){
            if (links.getRel().equals("approval_url")){ //si el pago esta aprobado
                return new UrlPaypalResponse(links.getHref());
            }
        }
        return new UrlPaypalResponse("");
    }
    @GetMapping("/success")
    public RedirectView paymentSuccessView(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("payerId") String payerId
    ){
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")){
                return new RedirectView("http://localhost:4200/payment/success");
            }
        } catch (PayPalRESTException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @GetMapping("/cancel")
    public RedirectView paymentCancelView(){
        return new RedirectView("http://localhost:4200");
    }

}
