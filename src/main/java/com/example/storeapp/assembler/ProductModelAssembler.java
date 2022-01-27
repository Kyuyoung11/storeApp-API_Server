package com.example.storeapp.assembler;

import com.example.storeapp.controller.ProductController;
import com.example.storeapp.entity.Product;
import com.example.storeapp.representationmodel.ProductModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductModelAssembler
        extends RepresentationModelAssemblerSupport<Product, ProductModel> {

    public ProductModelAssembler() {
        super(ProductController.class, ProductModel.class);
    }


    @Override
    public ProductModel toModel(Product entity) {
        ProductModel productModel = instantiateModel(entity);

        productModel.add(linkTo(methodOn(ProductController.class).retrieveProduct(entity.getId()))
        .withSelfRel());

        productModel.setId(entity.getId());
        productModel.setName(entity.getName());
        productModel.setCompany(entity.getCompany());
        productModel.setPrice(entity.getPrice());
        productModel.setUrl(entity.getUrl());
        productModel.setWriter(entity.getWriter());
        productModel.setDetail(entity.getDetail());
        return productModel;
    }

    @Override
    public CollectionModel<ProductModel> toCollectionModel(Iterable<? extends Product> entities) {

        CollectionModel<ProductModel> actorModels = super.toCollectionModel(entities);

        actorModels.add(linkTo(methodOn(ProductController.class).retrieveAllProducts()).withSelfRel());

        return actorModels;
    }

}
