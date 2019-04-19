package com.newer.mall.admin.account.thread;

import java.util.List;

import org.springframework.stereotype.Component;

import com.newer.mall.common.pojo.Notice;
import com.newer.mall.common.utils.EmailSenderService;

@Component
public class EmailRunnable implements Runnable {

	List<Notice> list;

	EmailSenderService mail;

	public EmailRunnable(List<Notice> list, EmailSenderService mail) {
		this.list = list;
		this.mail = mail;
	}

	@Override
	public void run() {

		for (Notice n : list) {
			mail.sendTextMail("商城", "您关注的商品" + n.getTitle() + "已有备货", n.getEmail());
		}

	}

}
