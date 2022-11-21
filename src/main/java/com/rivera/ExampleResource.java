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

    @GET
    @Path("/{id}")
    public Response findProduct(@PathParam("id") Long id){
        Product product = productRepository.byId(id);
        return Response.ok(product).build();
    }

    @PUT
    @Path("/{id}")
    public Response editProduct(@PathParam("id") Long id, Product product){
        Product productEdit = productRepository.editProduct(id, product);
        return Response.ok(productEdit).build();
    }

    @DELETE
    @Path("/borrar/{id}")
    public Response deleteProduct(@PathParam("id") Long id){
        productRepository.deleteProduct(id);
        return Response.ok("Producto eliminado con id " + id).build();
    }

}