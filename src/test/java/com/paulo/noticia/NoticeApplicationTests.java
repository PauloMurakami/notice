package com.paulo.noticia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import com.paulo.notice.NoticeApplication;
import com.paulo.notice.model.Notice;
import com.paulo.notice.service.NoticeService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(
	classes = NoticeApplication.class
)
@SpringBootConfiguration
@RunWith(SpringRunner.class)
class NoticeApplicationTests {
	@Autowired
	NoticeService noticeService;

	@Test
	void noticeService() {
		String originalUrl = "https://www.infomoney.com.br/mercados/itausa-lucra-123-mais-no-1o-tri-a-r-24-bi-prejuizo-da-marisa-cai-50-e-mais-balancos-petrobras-petrorio-e-outros-destaques/";
		Notice notice;
		try {
			notice = noticeService.addNew(originalUrl);
			assertEquals(originalUrl, notice.getUrl());			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
