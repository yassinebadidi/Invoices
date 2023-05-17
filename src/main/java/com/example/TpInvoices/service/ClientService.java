package com.example.TpInvoices.service;

import com.example.TpInvoices.entity.Client;
import com.example.TpInvoices.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> clients(){

        return clientRepository.findAll();
    }

    public Client findClientById(int id) {
        Optional<Client> resultatId = clientRepository.findById(id);

        Client findedClient;

        if (resultatId.isPresent()) {
            findedClient = resultatId.get();
        } else {
            throw new RuntimeException("Client not found with id = : " + id);

        }
        return findedClient;
    }

    public List<Client> fetchAllByUserId(int id) {
        return clientRepository.findAllByUserId(id);
    }
    public void updateClient (Client client) {
        clientRepository.save(client);
    }

    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }
}
