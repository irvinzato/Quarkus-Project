package com.rivera.repositories;

import com.rivera.entities.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProductRepository {
  @Inject
  EntityManager em;

  @Transactional
  public void createProduct(Product product){
    em.persist(product);
  }

  @Transactional
  public List<Product> listProducts(){
    List<Product> products = em.createQuery("SELECT p FROM Product p").getResultList();
    return products;
  }

  @Transactional
  public void deleteProduct(Long id){
    em.remove(byId(id));
  }

  @Transactional
  public Product byId(Long id){
    return (Product) em.createQuery("SELECT p FROM Product p WHERE p.id=:idParam")
            .setParameter("idParam", id)
            .getSingleResult();
  }

  @Transactional
  public Product editProduct(Long id, Product product){
    Product findProduct = byId(id);
    findProduct.setCode(product.getCode());
    findProduct.setName(product.getName());
    findProduct.setDescription(product.getDescription());
    return em.merge(findProduct);
  }
}
