package com.omnia.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.omnia.app.model.OrderProduct;
import com.omnia.app.model.OrderProductPK;


@Repository
public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {
}