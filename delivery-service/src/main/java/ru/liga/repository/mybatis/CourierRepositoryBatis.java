package ru.liga.repository.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;
import ru.liga.batisMapper.courier.CourierMapper;
import ru.liga.entity.Courier;

import java.io.IOException;
import java.io.Reader;

@Repository
public class CourierRepositoryBatis {
    private final String mybatisRes = "mybatis-config.xml";

    public Courier getCourierById(long id) {
        Courier courier = null;
        try (Reader reader = Resources.getResourceAsReader(mybatisRes)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                CourierMapper courierMapper = sqlSession.getMapper(CourierMapper.class);
                courier = courierMapper.getCourierById(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courier;
    }

    public Courier getCourierByPhone(String phone) {
        Courier courier = null;
        try (Reader reader = Resources.getResourceAsReader(mybatisRes)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                CourierMapper courierMapper = sqlSession.getMapper(CourierMapper.class);
                courier = courierMapper.getCourierByPhone(phone);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courier;
    }

    public void updateCourierStatus(Courier courier) {
        try (Reader reader = Resources.getResourceAsReader(mybatisRes)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                CourierMapper courierMapper = sqlSession.getMapper(CourierMapper.class);
                courierMapper.updateCourierStatus(courier);
                sqlSession.commit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}