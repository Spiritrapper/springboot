package com.smhrd.bigdata.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public abstract class BaseEntity {
	
	@CreatedDate
	@Column(name = "created_date")
	private LocalDateTime createdate;
	
	@LastModifiedDate
	@Column(name = "modified_date")
	private LocalDateTime modifiedDate;
}
