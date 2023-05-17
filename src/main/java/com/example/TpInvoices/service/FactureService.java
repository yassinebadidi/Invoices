package com.example.TpInvoices.service;

import com.example.TpInvoices.entity.Facture;
import com.example.TpInvoices.repository.FactureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactureService {

    private final FactureRepository factureRepository;

    public FactureService(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }


    public Facture createBill(Facture facture) {
        return factureRepository.save(facture);
    }

    public List<Facture> fetchAllByUserId(int id) {
        return factureRepository.findAllByUserId(id);
    }
}
