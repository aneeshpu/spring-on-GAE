package com.aneeshpu.gae.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aneeshpu.gae.domain.service.Mind;


@Controller
public class ThoughtController {

	@RequestMapping(value={"/foo/{weethought}"}, method=RequestMethod.GET)
	public ModelAndView helloWorld(@PathVariable("weethought") String thought){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("helloWorld");
		modelAndView.addObject("thought", new Mind().think(thought));
		return modelAndView;
	}
}