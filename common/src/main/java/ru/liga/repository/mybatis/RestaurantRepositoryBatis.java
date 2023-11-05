package ru.liga.repository.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;
import ru.liga.batisMapper.courier.CourierMapper;
import ru.liga.batisMapper.restaurant.RestaurantMapper;
import ru.liga.entity.Courier;
import ru.liga.entity.Restaurant;

import java.io.IOException;
import java.io.Reader;

@Repository
public class RestaurantRepositoryBatis {
    private final String mybatisRes = "mybatis-config.xml";

    public Restaurant getRestaurantById(long id) {
        Restaurant restaurant = null;
        try (Reader reader = Resources.getResourceAsReader(mybatisRes)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                RestaurantMapper restaurantMapper = sqlSession.getMapper(RestaurantMapper.class);
                restaurant = restaurantMapper.getRestaurantById(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return restaurant;
    }

    public String getNameRestaurantById(long id) {
        Restaurant restaurant = null;
        try (Reader reader = Resources.getResourceAsReader(mybatisRes)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                RestaurantMapper restaurantMapper = sqlSession.getMapper(RestaurantMapper.class);
                restaurant = restaurantMapper.getRestaurantById(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return restaurant.getName();
    }


}