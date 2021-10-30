package com.ecommerce.backend.repository;

import com.ecommerce.backend.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends MongoRepository <Produto, String> {
}
