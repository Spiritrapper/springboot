package com.smhrd.bigdata.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class) // 이 주석을 추가하세요
public class Board extends BaseEntity{
	
	/*GenerationType.IDENTITY: 데이터베이스에서 자동으로 생성된 키를 사용합니다. 주로 MySQL, PostgreSQL, mariadb 등과 같은 일부 데이터베이스에서 지원됩니다.
	GenerationType.SEQUENCE: 데이터베이스 시퀀스를 사용하여 값을 생성합니다. 이는 Oracle과 같은 데이터베이스에서 주로 사용됩니다.*/
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String content;
	private String filename;
	private String filepath;
	
	@Column(name = "view_count", columnDefinition = "int default 0")
	private int viewCount;




}
