package com.example.EtsyProject.EtsyProject.service;

import com.example.EtsyProject.EtsyProject.dao.ProductRepository;
import com.example.EtsyProject.EtsyProject.entity.Products;
import com.example.EtsyProject.EtsyProject.service.requests.SearchProductRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Products> searchProducts(SearchProductRequest searchProductRequest, String keyword){

        List<Products> products =  productRepository.queryProducts(keyword,
                searchProductRequest.getMin_price(),
                searchProductRequest.getMax_price(),
                searchProductRequest.getOutOfStock(),
                searchProductRequest.getSortType());
        return products;
    }

    public Optional<Products> getProductDetail(Integer productid) throws Exception{
        Optional<Products> result = Optional.ofNullable(productRepository.findById(productid));
        result.orElseThrow(() -> new EntityNotFoundException("Product ID Not found: " + productid));
        return result;
    }
}
