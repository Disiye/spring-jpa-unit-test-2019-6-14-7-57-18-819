package com.oocl.web.sampleWebApp.jpaSample.repository;

import com.oocl.web.sampleWebApp.jpaSample.entity.SingleEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SingleEntityRepositoryRepositoryTest {
    @Autowired
    private SingleEntityRepository singleEntityRepository;

    @Test
    public void test_should_return_user_when_the_user_exist() {
        //given
        SingleEntity singleEntity = new SingleEntity();
        singleEntity.setName("test");
        singleEntityRepository.save(singleEntity);

        //when
        List<SingleEntity> userList = singleEntityRepository.findAll();

        //then
        Assertions.assertEquals(1, userList.size());
        Assertions.assertEquals("test", userList.get(0).getName());
    }

    @Test
    public void should_return_exception_when_the_user_name_length_more_64() {
        //given
        SingleEntity singleEntity = new SingleEntity();
        singleEntity.setName("11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");

        Assertions.assertThrows(Exception.class, () -> {
            singleEntityRepository.saveAndFlush(singleEntity);
        });
    }

    @Test
    public void should_return_name_when_find_by_name(){
        String name = "test";
        SingleEntity singleEntity = new SingleEntity();
        singleEntity.setName(name);
        singleEntityRepository.save(singleEntity);
        SingleEntity testSingleEntity = singleEntityRepository.findByName(name);
        Assertions.assertEquals(name, testSingleEntity.getName());
    }

    @Test
    public void should_return_user_when_find_by_name(){
        String name = "test";
        SingleEntity singleEntity1 = new SingleEntity();
        singleEntity1.setName(name);
        SingleEntity singleEntity2 = new SingleEntity();
        singleEntity2.setName(name);
        singleEntityRepository.save(singleEntity1);
        singleEntityRepository.save(singleEntity2);
        List<SingleEntity> testSingleEntitys = singleEntityRepository.findUsersByName(name);
        Assertions.assertEquals(name, testSingleEntitys.get(0).getName());
        Assertions.assertEquals(name, testSingleEntitys.get(1).getName());
    }

    @Test
    public void should_return_age_desc_when_find_all(){
        String name = "test";
        SingleEntity singleEntity1 = new SingleEntity();
        singleEntity1.setName(name);
        singleEntity1.setAge(12);
        SingleEntity singleEntity2 = new SingleEntity();
        singleEntity2.setName(name);
        singleEntity2.setAge(11);
        singleEntityRepository.save(singleEntity1);
        singleEntityRepository.save(singleEntity2);
        List<SingleEntity> testSingleEntitys = singleEntityRepository.findByOrderByAgeDesc();
        Assertions.assertEquals(12, testSingleEntitys.get(0).getAge());
        Assertions.assertEquals(11, testSingleEntitys.get(1).getAge());
    }

    @Test
    public void should_return_age_asc_when_find_all(){
        String name = "test";
        SingleEntity singleEntity1 = new SingleEntity();
        singleEntity1.setName(name);
        singleEntity1.setAge(12);
        SingleEntity singleEntity2 = new SingleEntity();
        singleEntity2.setName(name);
        singleEntity2.setAge(11);
        singleEntityRepository.save(singleEntity1);
        singleEntityRepository.save(singleEntity2);
        List<SingleEntity> testSingleEntitys = singleEntityRepository.findByOrderByAgeAsc();
        Assertions.assertEquals(11, testSingleEntitys.get(0).getAge());
        Assertions.assertEquals(12, testSingleEntitys.get(1).getAge());
    }

    @Test
    public void should_return_delete_when_delete_name_is_a(){
        String name = "test";
        SingleEntity singleEntity1 = new SingleEntity();
        singleEntity1.setName("a");
        singleEntity1.setAge(12);
        SingleEntity singleEntity2 = new SingleEntity();
        singleEntity2.setName(name);
        singleEntity2.setAge(11);
        singleEntityRepository.save(singleEntity1);
        singleEntityRepository.save(singleEntity2);
        singleEntityRepository.deleteByName(name);
        List<SingleEntity> singleEntities = singleEntityRepository.findAll();
        Assertions.assertEquals(1, singleEntities.size());
    }

    @Test
    public void should_return_delete_when_delete_by_id(){
        String name = "name";
        SingleEntity singleEntity1 = new SingleEntity();
        singleEntity1.setName(name);
        singleEntity1.setAge(12);
        SingleEntity singleEntity2 = new SingleEntity();
        singleEntity2.setName(name);
        singleEntity2.setAge(11);
        singleEntityRepository.save(singleEntity1);
        singleEntityRepository.save(singleEntity2);
        singleEntityRepository.deleteUserById(1);
        List<SingleEntity> singleEntities = singleEntityRepository.findAll();
        Assertions.assertEquals(1, singleEntities.size());
    }
}
