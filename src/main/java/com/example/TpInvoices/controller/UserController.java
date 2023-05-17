package com.example.TpInvoices.controller;

import com.example.TpInvoices.entity.User;
import com.example.TpInvoices.repository.UserRepository;
import com.example.TpInvoices.service.Userservice;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    Userservice userservice;

    @GetMapping("/user")
    public String home(Model model) {
        User user = userservice.findByEmail("admin@my-invoice.fr")
                .orElseThrow(() -> new IllegalStateException("User not found !!"));
        model.addAttribute("user", user);
        return "user";
    }



    /*@PostMapping()
    public String authenticateUser(@RequestParam String email,
                                   @RequestParam String password,
                                   HttpSession session){

        Optional<User> user = userRepository.findByEmail(email);

        if (user != null && checkPassword(password,user.getPassword())){
            session.setAttribute("user",user);
            return "redirect:/clients";

        }else {
            return "redirect:/login?error";
        }

    }

    @GetMapping("/factures")
    public String showInvoices(HttpSession session, Model model) {
        // Vérifier si l'utilisateur est connecté en vérifiant la session
        User user = (User) session.getAttribute("user");
        if (user != null) {
            // Utilisateur connecté, récupérer les factures et les ajouter au modèle
            List<Facture> factures = // Logique pour récupérer les factures
                    model.addAttribute("invoices", factures);

            // Afficher la page des factures
            return "factures";
        } else {
            // Utilisateur non connecté, rediriger vers la page de connexion
            return "redirect:/login";
        }
    }*/



    private boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
