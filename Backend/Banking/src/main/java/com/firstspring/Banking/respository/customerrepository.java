package com.firstspring.Banking.respository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import com.firstspring.Banking.entity.customer;



@Repository
public interface customerrepository extends JpaRepository<customer,Long> {



customer findByEmailAndPassword(String email, String password);



customer findByaccountno(String accountNum);



}