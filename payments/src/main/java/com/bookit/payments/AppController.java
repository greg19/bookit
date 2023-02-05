package com.bookit.payments;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;

@Log4j2
@Controller
public class AppController {

    @Autowired
    Environment env;

    @GetMapping("/pay/{id}")
    public String pay(@PathVariable String id, Model model){
        model.addAttribute("id", id);
        return "pay";
    }

    @GetMapping("/paid/{id}")
    public String confirmPayment(@PathVariable String id) {
        WebClient client = WebClient.create();
        var x = client.get().uri(uriBuilder -> uriBuilder.path("localhost:8081/paid/" + id)
                .build()).retrieve().toBodilessEntity().map(response -> response.getStatusCode()).block();
        log.info("Status: {}", x);
        return "paid";
    }

}
