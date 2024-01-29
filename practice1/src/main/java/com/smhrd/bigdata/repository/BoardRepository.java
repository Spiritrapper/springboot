package com.smhrd.bigdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.bigdata.entity.Board;

// Long 프라이머리키
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {


}
