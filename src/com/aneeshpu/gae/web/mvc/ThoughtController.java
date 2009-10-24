package com.aneeshpu.gae.web.mvc;

import java.util.List;

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

	@RequestMapping(value = { "/think/{thought}" }, method = RequestMethod.GET)
	public ModelAndView helloWorld(@PathVariable("thought") String thought) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("newThought");
		modelAndView.addObject("thought", mind.think(thought));
		return modelAndView;
	}

	@RequestMapping(value = { "/allMyThoughts" }, method = RequestMethod.GET)
	public ModelAndView allMyThoughts() {

		List<QuickThought> allMyThoughts = mind.allMyThoughts();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("allMyThoughts");
		modelAndView.addObject("allMyThoughts", allMyThoughts);
		return modelAndView;
	}
}