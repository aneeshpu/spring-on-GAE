package com.foo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ThoughtController {

	@RequestMapping("/helloWorld")
	public ModelAndView helloWorld(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("helloWorld");
		MyModel myModel = new MyModel();
		myModel.setName("Spring");
		myModel.setVersion("2.5");
		
		modelAndView.addObject("message", myModel);
		return modelAndView;
	}
}