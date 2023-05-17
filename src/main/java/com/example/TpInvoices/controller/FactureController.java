package com.example.TpInvoices.controller;

import com.example.TpInvoices.entity.Client;
import com.example.TpInvoices.entity.Facture;
import com.example.TpInvoices.entity.Product;
import com.example.TpInvoices.entity.User;
import com.example.TpInvoices.service.ClientService;
import com.example.TpInvoices.service.FactureService;
import com.example.TpInvoices.service.ProductService;
import com.example.TpInvoices.service.Userservice;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class FactureController {

    @Autowired
    FactureService factureService;

    @Autowired
    ClientService clientService;

    @Autowired
    ProductService productService;

    @Autowired
    Userservice userService;

    @GetMapping("/bills")
    public String newBill(HttpSession session,
                          Model model) {

        String email = (String) session.getAttribute("email");
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        List<Client> clients = clientService.fetchAllByUserId(user.getId());
        List<Product> products = productService.fetchAllProductsById(user.getId());
        List<Facture> bills = factureService.fetchAllByUserId(user.getId());

        model.addAttribute("bills", bills);
        model.addAttribute("clients", clients);
        model.addAttribute("products", products);
        model.addAttribute("bill", new Facture());
        return "bills";
    }

    @PostMapping("/new-bill")
    public String addBill(HttpSession session,
                          @ModelAttribute("facture") Facture newFacture,
                          @RequestParam("clientId") Integer clientId,
                          @RequestParam("productIds") List<Integer> productIds) {

        String email = (String) session.getAttribute("email");
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User not found"));


        newFacture.setDate(LocalDateTime.now());
        newFacture.setPaymentLimitDate(newFacture.getDate().plus(1, ChronoUnit.MONTHS));
        Client client = clientService.findClientById(clientId);
        newFacture.setClient(client);
        List<Product> products = productService.fetchAllProductsByIdList(productIds);
        newFacture.setProducts(products);
        newFacture.setUser(user);

        factureService.createBill(newFacture);
        return "redirect:/factures";
    }
}
