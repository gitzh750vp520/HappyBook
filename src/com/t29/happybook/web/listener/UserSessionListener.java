package com.t29.happybook.web.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.t29.happybook.service.Cart;

public class UserSessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		session.setAttribute("cart", new Cart());
		session.setAttribute("cond_cname", "È«²¿");
		session.setAttribute("cond_btitle", "");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
	}

}
