package com.aneeshpu.gae.web;

import java.io.IOException;
import java.text.MessageFormat;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aneesh.gae.domain.PMF;
import com.aneesh.gae.domain.QuickThought;

@SuppressWarnings("serial")
public class ThoughtReaderServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PersistenceManager persistenceManager = PMF.get().getPersistenceManager();
		
		Extent<QuickThought> extent = persistenceManager.getExtent(QuickThought.class, true);
		for (QuickThought quickThought : extent) {
			resp.getWriter().print(MessageFormat.format("Thought:{0}<br/>",quickThought));
		}
		
	}
}