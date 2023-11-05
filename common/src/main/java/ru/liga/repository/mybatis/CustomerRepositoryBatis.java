package ru.liga.repository.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;
import ru.liga.batisMapper.customer.CustomerMapper;
import ru.liga.entity.Customer;

import java.io.IOException;
import java.io.Reader;

@Repository
public class CustomerRepositoryBatis {
    private final String mybatisRes = "mybatis-config.xml";

    public Customer getCustomerById(long id) {
        Customer customer = null;
        try (Reader reader = Resources.getResourceAsReader(mybatisRes)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
                customer = customerMapper.getCustomerById(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public Customer getCustomerByEmail(String email) {
        Customer customer = null;
        try (Reader reader = Resources.getResourceAsReader(mybatisRes)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
                customer = customerMapper.getCustomerByEmail(email);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public void updateCustomerPhone(Customer customer) {
        try (Reader reader = Resources.getResourceAsReader(mybatisRes)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
                customerMapper.updateCustomerPhone(customer);
                sqlSession.commit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}