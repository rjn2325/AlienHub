package org.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController
{
	@RequestMapping("/Messages.html")
	public String showChat()
	{
		return "ChatRoom";
	}
}
