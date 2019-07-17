package com.oocl.web.sampleWebApp.jpaSample.repository;

import com.oocl.web.sampleWebApp.jpaSample.entity.SingleEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SingleEntityRepository extends JpaRepository<SingleEntity, Long> {
    SingleEntity findByName(String name);

    List<SingleEntity> findUsersByName(String name);

    List<SingleEntity> findByOrderByAgeAsc();

    List<SingleEntity> findByOrderByAgeDesc();

    void deleteByName(String name);

//    @Modifying
//    @Query(nativeQuery = true, value = "delete from SINGLE_ENTITY where id = :#{#id}")
//    void deleteUserById(@Param("id") long id);

//    @Modifying
//    @Query(nativeQuery = true, value = "delete from SINGLE_ENTITY where id = :id")
//    void deleteUserById(@Param("id") long id);

    @Modifying
    @Query(nativeQuery = true, value = "delete from SINGLE_ENTITY where id = ?1")
    void deleteUserById(long id);
}
