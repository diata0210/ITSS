package app.services;

import app.models.Product;
import app.repositories.ProductRepository;

import java.util.List;

public class ProductServiceImp {
    private ProductRepository productRepository;

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct(){
        return productRepository.getAllProducts();
    }

    public Product getProductById(int id){
        return productRepository.getProductById(id);
    }

}
