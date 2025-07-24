package bank.controllers;

import com.stripe.exception.StripeException;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import com.stripe.Stripe;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/create-checkout-session")
@ImportAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class CheckoutController {
    @Value("sk_test_51Rn32xR8xEV2XZugiVE1rnDnzoI6o1trQDM24huGIEPqlxSH0JbaGQOUUxMXTnYYlhWtiH6E3MWPor0T5vq5XXvH00F3OUCkr3")
    private String stripeSecretKey;

    @Value("pk_test_51Rn32xR8xEV2XZugXO7DCa5bqDMB5UboeGVWYoJy2wLc1tyY9e4su2daoRsmNu42b2dp0CrMTnl3xfUlJcWfPwGM00i7C50Ini")
    private String stripePublishableKey;

    private static final Gson gson = new Gson();

    @PostMapping
    public ResponseEntity<?> createCheckoutSession(@RequestBody Map<String, Object> priceData) {
        System.out.println("Stripe Secret Key: " + stripeSecretKey);
        Stripe.apiKey = stripeSecretKey;
        Long selectedPrice = Long.parseLong(priceData.get("price").toString() + "00");

        try {
            SessionCreateParams params = SessionCreateParams.builder()
                    .setSuccessUrl("http://localhost:3000/close")
                    .setCancelUrl("http://localhost:3000/close")
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setPriceData(
                                            SessionCreateParams.LineItem.PriceData.builder()
                                                    .setCurrency("usd") // Currency
                                                    .setUnitAmount(selectedPrice) // Amount in cents
                                                    .setProductData(
                                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                    .setName("Custom Product")
                                                                    .build()
                                                    )
                                                    .build()
                                    )
                                    .setQuantity(1L)
                                    .build()
                    )
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .build();

            Session session = Session.create(params);

            Map<String, String> response = new HashMap<>();
            response.put("url", session.getUrl());
            String jsonResponse = gson.toJson(response);
            System.out.println(response.toString());
            return ResponseEntity.ok(response);

        } catch (StripeException e) {
            e.printStackTrace();

            if (e.getStripeError() != null) {
                System.err.println("Stripe Error Type: " + e.getStripeError().getType());
                System.err.println("Stripe Error Code: " + e.getStripeError().getCode());
                System.err.println("Stripe Error Message: " + e.getStripeError().getMessage());
                System.err.println("Stripe Error Param: " + e.getStripeError().getParam());
            }
            String errorMessage = gson.toJson("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }
}
