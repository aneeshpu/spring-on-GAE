package com.aneeshpu.gae.web;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aneesh.gae.domain.PMF;
import com.aneesh.gae.domain.QuickThought;

@SuppressWarnings("serial")
public class ThoughtCreatorServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		QuickThought quickThought = new QuickThought(req.getParameter("qThought"));

		PersistenceManager persistenceManager = PMF.get().getPersistenceManager();
		javax.jdo.Transaction tx = persistenceManager.currentTransaction();
		try {
			tx.begin();
			persistenceManager.makePersistent(quickThought);
			tx.commit();
			
		} catch (Exception e) {
			if(tx.isActive()){
				tx.rollback();
			}
			
		} finally {
			persistenceManager.close();
		}
	}
}