package com.unir.payments.service;

import com.unir.payments.data.PaymentJpaRepository;
import com.unir.payments.data.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

//  @Autowired //Inyeccion por campo (field injection). Es la menos recomendada.
//  private ProductsFacade productsFacade;

  private final PaymentJpaRepository repository;

  public PaymentServiceImpl(PaymentJpaRepository repository) {
    this.repository = repository;
  }

//  @Override
//  public Order createOrder(OrderRequest request) {
//
////    List<Product> products = request.getProducts().stream().map(productsFacade::getProduct).filter(Objects::nonNull).toList();
//
//    if(products.size() != request.getProducts().size() || products.stream().anyMatch(product -> !product.getVisible())) {
//      return null;
//    } else {
//      Order order = Order.builder().products(products.stream().map(Product::getId).collect(Collectors.toList())).build();
//      repository.save(order);
//      return order;
//    }
//
//  }

  @Override
  public List<Payment> getPayments() {
    return repository.findAll();
  }

    @Override
    public Payment getPaymentById(Long id) {
      return repository.findById(id)
              .orElseThrow(() ->
                      new RuntimeException("Payment no encontrado con id: " + id));
    }
}
