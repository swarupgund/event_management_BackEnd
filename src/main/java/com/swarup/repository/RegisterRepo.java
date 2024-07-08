package com.swarup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.swarup.entity.RegisterEntity;

import jakarta.transaction.Transactional;

@Transactional  /// to wrap a method in a database transaction
public interface RegisterRepo extends JpaRepository<RegisterEntity, Integer> {

    RegisterEntity findByUserEmail(String userEmail);

    boolean existsByUserEmail(String userEmail);

    @Modifying  //modifies data in the database
    @Query("UPDATE RegisterEntity SET userPassword = :password WHERE userEmail = :mail")
    int updateUserPassword(String mail, String password);

}