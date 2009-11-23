package com.aneeshpu.gae.web.mvc;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.aneesh.gae.domain.QuickThought;
import com.aneeshpu.gae.domain.service.Mind;


public class ThoughtControllerTest {

	@Test
	public void should_set_a_thought_on_model_and_view() throws Exception {
		String thoughtString = "still on the train";
		String tag = "train";
		QuickThought quickThought = new QuickThought(thoughtString, tag);
		
		Mind mindMock = createMock(Mind.class);
		expect(mindMock.think(thoughtString, tag)).andReturn(quickThought);
		replay(mindMock);
		
		ThoughtController thoughtController = new ThoughtController(mindMock);
		ModelAndView modelAndView = thoughtController.think(thoughtString, tag);
		
		assertEquals(quickThought, modelAndView.getModel().get("thought"));
		assertEquals("newThought", modelAndView.getViewName());
		
		verify(mindMock);
	}
	
	@Test
	public void should_call_mind_without_tags() throws Exception {
		String thoughtString = "still on the train";
		QuickThought quickThought = new QuickThought(thoughtString);
		
		Mind mindMock = createMock(Mind.class);
		expect(mindMock.think(thoughtString)).andReturn(quickThought);
		replay(mindMock);
		
		ThoughtController thoughtController = new ThoughtController(mindMock);
		ModelAndView modelAndView = thoughtController.think(thoughtString);
		
		assertEquals(quickThought, modelAndView.getModel().get("thought"));
		assertEquals("newThought", modelAndView.getViewName());
		
		verify(mindMock);
	}
	
	@Test
	public void should_set_a_collection_of_thoughts() throws Exception {
		Mind mindMock = createMock(Mind.class);
		ArrayList<QuickThought> allMyThoughts = new ArrayList<QuickThought>();
		expect(mindMock.allMyThoughts()).andReturn(allMyThoughts);
		replay(mindMock);
		
		ThoughtController thoughtController = new ThoughtController(mindMock);
		ModelAndView modelAndView = thoughtController.allMyThoughts();
		
		assertEquals(allMyThoughts, modelAndView.getModel().get("allMyThoughts"));
		assertEquals("allMyThoughts", modelAndView.getViewName());
		
		verify(mindMock);
		
	}
	
	@Test
	public void should_set_a_collection_of_tags_when_searching_for_tags() throws Exception {
		Mind mindMock = createMock(Mind.class);
		ArrayList<QuickThought> allMyThoughts = new ArrayList<QuickThought>();
		expect(mindMock.allThoughtTaggedWith("FooBar")).andReturn(allMyThoughts);
		replay(mindMock);
		
		ThoughtController thoughtController = new ThoughtController(mindMock);
		ModelAndView modelAndView = thoughtController.allThoughtsTaggedWith("FooBar");
		
		assertEquals(allMyThoughts, modelAndView.getModel().get("allMyThoughts"));
		assertEquals("allMyThoughts", modelAndView.getViewName());
		
		verify(mindMock);
	}
}
