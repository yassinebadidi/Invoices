package com.example.TpInvoices.controller;

import com.example.TpInvoices.entity.Client;
import com.example.TpInvoices.repository.ClientRepository;
import com.example.TpInvoices.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    public List<Client> allCustomer(){
        List<Client> clients;
        clients = clientService.clients();
        return clients;
    }

    @GetMapping("/clients/{id}")
    public Client findClient(@PathVariable int id) {
        return clientService.findClientById(id);
    }

    @PostMapping("/clients")
    public Client saveCustomer(@RequestBody Client client) {
        Client newClient = clientService.saveClient(client);
        return newClient;
    }

    @PutMapping("/clients")
    public Client updateClient(@RequestBody Client client) {
        clientService.saveClient(client);
        return client;
    }


}
