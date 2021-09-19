package com.paulo.notice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "notice")
public class Notice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "caption")
	private String caption;

	@Column(name = "author")
	private String author;

	@Column(name = "datePublish")
	private LocalDateTime datePublish;

	@Column(name = "contents",columnDefinition="TEXT")
	private String contents;

	@Column(name = "url")
	private String url;
}
