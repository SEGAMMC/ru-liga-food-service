package ru.liga.repository.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;
import ru.liga.batisMapper.orderItem.OrderItemMapper;
import ru.liga.entity.OrderItem;
import ru.liga.entity.RestaurantMenuItem;

import java.io.IOException;
import java.io.Reader;

@Repository
public class OrderItemRepositoryBatis {
    private final String mybatisRes = "mybatis-config.xml";

    public OrderItem getOrderItemById(long id) {
        OrderItem orderItem = null;
        try (Reader reader = Resources.getResourceAsReader(mybatisRes)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                OrderItemMapper orderItemMapper = sqlSession.getMapper(OrderItemMapper.class);
                orderItem = orderItemMapper.getOrderItemById(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orderItem;
    }

    public RestaurantMenuItem getRestaurantMenuItemById(long id) {
        RestaurantMenuItem restaurantMenuItem = null;
        try (Reader reader = Resources.getResourceAsReader(mybatisRes)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                OrderItemMapper orderItemMapper = sqlSession.getMapper(OrderItemMapper.class);
                restaurantMenuItem = orderItemMapper.getRestaurantMenuItemById(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return restaurantMenuItem;
    }

}