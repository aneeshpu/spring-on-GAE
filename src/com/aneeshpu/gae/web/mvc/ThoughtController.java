package com.aneeshpu.gae.web.mvc;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aneesh.gae.domain.QuickThought;
import com.aneeshpu.gae.domain.service.Mind;

@Controller
public class ThoughtController {

	@Autowired
	private Mind mind;
	
	public ThoughtController(){
	}
	
	public ThoughtController(Mind mind){
		this();
		this.mind = mind;
	}

	@RequestMapping(value = { "/think/{thought}/tag/{tags}" }, method = RequestMethod.GET)
	public ModelAndView think(@PathVariable("thought") String thought, @PathVariable("tags") String tags) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("newThought");
		modelAndView.addObject("thought", mind.think(thought, tags));
		return modelAndView;
	}

	@RequestMapping(value = { "/allMyThoughts" }, method = RequestMethod.GET)
	public ModelAndView allMyThoughts() {

		Collection<QuickThought> allMyThoughts = mind.allMyThoughts();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("allMyThoughts");
		modelAndView.addObject("allMyThoughts", allMyThoughts);
		return modelAndView;
	}
	
	@RequestMapping(value = { "/allMyThoughts/taggedWith/{tag}" }, method = RequestMethod.GET)
	public ModelAndView allThoughtsTaggedWith(@PathVariable("tag") String tag){
		Collection<QuickThought> allMyThoughts = mind.allThoughtTaggedWith(tag);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("allMyThoughts");
		modelAndView.addObject("allMyThoughts", allMyThoughts);
		return modelAndView;

	}
}