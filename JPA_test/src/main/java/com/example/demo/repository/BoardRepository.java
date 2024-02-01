package com.example.demo.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Board;

//JpaRepository<Entity 클래스, PK 타입 > => 클래스타입으로 기재
public interface BoardRepository extends JpaRepository<Board, Long> {
	//Entity : DB의 테이블 클래스
	//DTO : 객체를 생성하는 클래스 (=VO)
	

	
}
