package com.poc.strategy;

import com.fasterxml.jackson.databind.JsonNode;
import com.poc.dto.OrderModel;

public interface EventStrategy {

   void initialize(String path, JsonNode value);
   
   void add(OrderModel orderModel);
   
   void update(OrderModel orderModel);

   void delete(OrderModel orderModel);
   
}
