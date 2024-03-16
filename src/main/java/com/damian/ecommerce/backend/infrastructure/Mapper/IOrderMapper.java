package com.damian.ecommerce.backend.infrastructure.Mapper;

import com.damian.ecommerce.backend.domain.model.Order;
import com.damian.ecommerce.backend.domain.model.OrderProduct;
import com.damian.ecommerce.backend.infrastructure.Entity.OrderEntity;
import com.damian.ecommerce.backend.infrastructure.Entity.OrderProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses =  {IOrderProductMapper.class })
public interface IOrderMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "dateCreated", target = "dateCreated"),
            @Mapping(source = "orderProducts", target = "orderProducts"),
            @Mapping(source = "orderState", target = "orderState"),
            @Mapping(source = "userEntity.id", target = "userId")
    })
    Order toOrder(OrderEntity orderEntity);

    Iterable<Order> toOrderList(Iterable<OrderEntity> orderEntities);

    @InheritInverseConfiguration
    OrderEntity toOrderEntity(Order order);
}
