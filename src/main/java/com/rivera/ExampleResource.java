package com.rivera;

import com.rivera.entities.Product;
import com.rivera.repositories.ProductRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
public class ExampleResource {

    @Inject
    ProductRepository productRepository;

    @GET
    public List<Product> list() {
        return productRepository.listProducts();
    }

    @POST
    public Response addProduct(Product product){
        productRepository.createProduct(product);
        return Response.ok().build();
    }

    @DELETE
    public Response deleteProduct(Product product){
        productRepository.deleteProduct(product);
        return Response.ok().build();
    }

}