package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.bean.LossOfPay;

public interface LossOfPayRepository extends JpaRepository<LossOfPay, Integer> {

}
