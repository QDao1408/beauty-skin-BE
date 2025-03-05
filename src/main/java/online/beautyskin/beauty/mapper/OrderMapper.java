package online.beautyskin.beauty.mapper;

import online.beautyskin.beauty.entity.Order;
import online.beautyskin.beauty.entity.request.OrderRequest;
import org.modelmapper.PropertyMap;

import java.time.LocalDateTime;

public class OrderMapper extends PropertyMap<OrderRequest, Order> {
    @Override
    protected void configure() {
        map().setId(0);
    }
}
