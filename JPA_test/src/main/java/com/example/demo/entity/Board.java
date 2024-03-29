package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Board extends TimeBased {
	//Entity : DB의 테이블 클래스
	//DTO : 객체를 생성하는 클래스 ( =VO)
	
	//자주쓰는 코드들 : base class로 별도 관리
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private Long bno;
	
	@Column(length = 100, nullable = false)
	private String title;
	
	//@ColumnDefault("writer")
	//@Column(columnDefinition = "integer default -1")
	@Column(length = 100, nullable = false)
	private String writer;
	
	@Column(length = 2000, nullable = false)
	private String content;
	
	//생성시 초기화 값을 지정
	/*
	 * @Builder.Default private int point = 0;
	 */
}
