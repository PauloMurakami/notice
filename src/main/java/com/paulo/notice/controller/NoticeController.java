package com.paulo.notice.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.paulo.notice.model.Notice;
import com.paulo.notice.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	NoticeService noticeService;

	@PostMapping(value = "/notice/saveNew")
	public String saveNotice(final Notice noticeInput, Model model) throws IOException {
		noticeService.addNew(noticeInput.getUrl());
		return "redirect:/notice/list";
	}

	@GetMapping(value = "/notice")
	public String newNotice(Model model) throws IOException {
		model.addAttribute("noticeInput", new Notice());
		return "addNotice";
	}

	@GetMapping(value = "/notice/list")
	public String listNotice(Model model) throws IOException {
		List<Notice> notices = noticeService.list();
		model.addAttribute("notices", notices);
		return "notices";
	}

	@GetMapping(value = "/notice/search", params = { "term" })
	public String searchNotice(Model model, final HttpServletRequest req) throws IOException {

		List<Notice> notices = noticeService.search(req.getParameter("term"));
		model.addAttribute("notices", notices);
		return "notices";
	}
	
	@GetMapping(value = "/notice/view", params = { "id" })
	public String viewNotice(Model model, final HttpServletRequest req) throws IOException {

		Optional<Notice> notice = noticeService.findById(Long.parseLong(req.getParameter("id")));
		model.addAttribute("notice", notice);
		return "view";
	}
}
