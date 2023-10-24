package ru.liga.repository.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;
import ru.liga.batisMapper.restaurantMenuItem.RestaurantMenuItemMapper;
import ru.liga.entity.RestaurantMenuItem;

import java.io.IOException;
import java.io.Reader;

@Repository
public class RestaurantMenuItemRepositoryBatis {
    private final String MYBATIS_RES = "mybatis-config.xml";


    public RestaurantMenuItem getRestaurantMenuItemById(long id) {
        RestaurantMenuItem restaurantMenuItem = null;
        try (Reader reader = Resources.getResourceAsReader(MYBATIS_RES)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                RestaurantMenuItemMapper restaurantMenuItemMapper =
                        sqlSession.getMapper(RestaurantMenuItemMapper.class);
                restaurantMenuItem = restaurantMenuItemMapper.getRestaurantMenuItemById(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return restaurantMenuItem;
    }

}