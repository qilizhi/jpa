package com.qlz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qlz.entities.Log;

public interface LogDao extends JpaRepository<Log, Long>{
	
	

}
