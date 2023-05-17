package com.example.TpInvoices.repository;

import com.example.TpInvoices.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Integer> {
    List<Facture> findAllByUserId(int id);
}
