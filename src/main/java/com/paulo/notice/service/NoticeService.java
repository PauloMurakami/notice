package com.paulo.notice.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulo.notice.model.Notice;
import com.paulo.notice.repository.NoticeRepository;

@Service
public class NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;

	public Notice addNew(String url) throws IOException {

		StringBuilder nomes = new StringBuilder();
		Notice notice = new Notice();

		Document doc = Jsoup.connect(url).get();

		Element title = doc.getElementsByClass("page-title-1").first();

		Element caption = doc.getElementsByClass("article-lead").first();

		Element dataPublish = doc.getElementsByClass("entry-date").first();

		System.out.println(dataPublish.childNodes().get(0).toString());

		Element author = doc.getElementsByClass("author-name").first();
		Document nameAuthor = Jsoup.parse(author.parentNode().childNode(1).toString());

		Element content = doc.getElementsByClass("article-content").first();
		content.childNodes().forEach(a -> {
			nomes.append(a.toString());
		});
		;
		Document contents = Jsoup.parse(nomes.toString());

		LocalDateTime dateFormating = formattingDate(dataPublish.childNodes().get(0).toString());

		notice.setTitle(title.childNodes().get(0).toString());
		notice.setCaption(caption.childNodes().get(0).toString());
		notice.setDatePublish(dateFormating);
		notice.setAuthor(nameAuthor.text().replace("Por", ""));
		notice.setContents(contents.text());
		notice.setUrl(url);
		noticeRepository.save(notice);
		return notice;
	}

	public LocalDateTime formattingDate(String date) {
		String[] splitted = date.split("\\s+");
		String[] splittedHour = splitted[3].split("h");
		String formatedDate = splitted[0] + '/' + formatMonth(splitted[1]) + '/' + splitted[2] + ' ' + splittedHour[0]
				+ ':' + splittedHour[1];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		LocalDateTime formatDateTime = LocalDateTime.parse(formatedDate, formatter);
		return formatDateTime;
	}

	public String formatMonth(String month) {
		switch (month) {
		case "jan":
			return "01";
		case "fev":
			return "02";
		case "mar":
			return "03";
		case "abr":
			return "04";
		case "maio":
			return "05";
		case "jun":
			return "06";
		case "jul":
			return "07";
		case "ago":
			return "08";
		case "set":
			return "09";
		case "out":
			return "10";
		case "nov":
			return "11";
		case "dez":
			return "12";
		default:
			return null;
		}

	}

	public List<Notice> list() {

		return noticeRepository.findAll();
	}

	public List<Notice> search(String term) {

		return noticeRepository.search(term);
	}

	public Notice findByUrl(String url) {
		
		return noticeRepository.view(url);
	}
}
