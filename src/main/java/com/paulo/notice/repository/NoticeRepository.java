package com.paulo.notice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paulo.notice.model.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
	@Query("SELECT n FROM Notice n WHERE n.contents LIKE CONCAT('%',:term,'%')")
	List<Notice> search(@Param("term") String term);
	
	
	@Query("SELECT n FROM Notice n WHERE n.url = :url")
	Notice view(@Param("url") String url);
}
