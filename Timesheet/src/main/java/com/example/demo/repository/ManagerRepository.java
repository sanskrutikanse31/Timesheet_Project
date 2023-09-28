package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.bean.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
	
}