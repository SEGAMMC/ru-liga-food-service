package ru.liga.repository.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;
import ru.liga.batisMapper.customer.CustomerMapper;
import ru.liga.batisMapper.order.OrderMapper;
import ru.liga.entity.Customer;
import ru.liga.entity.Order;

import java.io.IOException;
import java.io.Reader;

@Repository
public class OrderRepositoryBatis {
    private final String mybatisRes = "mybatis-config.xml";

    public Order getOrderById(long id) {
        Order order = null;
        try (Reader reader = Resources.getResourceAsReader(mybatisRes)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
                order = orderMapper.getOrderById(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return order;
    }

    public void updateOrderStatus(Order order) {
        try (Reader reader = Resources.getResourceAsReader(mybatisRes)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
                orderMapper.updateOrderStatus(order);
                sqlSession.commit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}