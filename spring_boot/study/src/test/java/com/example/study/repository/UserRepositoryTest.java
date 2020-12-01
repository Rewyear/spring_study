package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    // Dependancy Injection(의존성 주입)
    // 기존 private UserRepository userRepository = new Userpository();과 같이 인스턴스를 생성한 반면
    // Autowired annotation을 사용하게 되면, 유저가 직접 인스턴스를 생성하지 않고 spring boot 직접 생성, 관리
    // Singleton pattern 적용
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create()
    {
        User user = new User();
        // user.setId(); // DB에서 auto increment 기능을 사용하기 때문에 id 값을 설정할 필요가 없다.
        user.setAccount("TestUser01");
        user.setEmail("TestUser01@gmail.com");
        user.setPhoneNumber("010-1111-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin2");

        User newUser = userRepository.save(user);
        System.out.println("newUser :" + newUser);
    }

    @Test
    @Transactional // 해당 어노테이션을 사용하면 쿼리문은 실행되지만 실제 DB의 데어터는 건드리지 않음
    public void read()
    {
        // select * from user where id = ?
        Optional<User> user = userRepository.findById(1L); // id로 찾음
        user.ifPresent(selectUser -> {
            selectUser.getOrderDetailList().stream().forEach(detail -> {
                Item item = detail.getItem();
                System.out.println(item);
            });
        });
    }

    @Test
    @Transactional
    public void update()
    {
        Optional<User> user = userRepository.findById(2L); // id로 찾음
        user.ifPresent(selectUser -> {
            selectUser.setAccount("UPUP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("admin");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional // 해당 어노테이션을 사용하면 쿼리문은 실행되지만 실제 DB의 데어터는 건드리지 않음
    public void delete()
    {
        Optional<User> user = userRepository.findById(2L); // id로 찾음

        Assertions.assertTrue(user.isPresent());

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        // 실제 DB에서 delete됬는지 확인
        Optional<User> deleteUser = userRepository.findById(2L);
        Assertions.assertFalse(deleteUser.isPresent());
    }

}
