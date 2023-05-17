package com.example.TpInvoices.service;

import com.example.TpInvoices.entity.Product;
import com.example.TpInvoices.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> fetchAllProductsByIdList(List<Integer> list_id){
        return productRepository.findAllByIdIn(list_id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> fetchAllProductsById(int id) {
        return productRepository.findAllByUserId(id);
    }
}
