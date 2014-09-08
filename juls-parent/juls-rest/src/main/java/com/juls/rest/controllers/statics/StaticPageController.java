package com.juls.rest.controllers.statics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StaticPageController {

	@RequestMapping(value = "/{pageName:.*\\.html*}", method = RequestMethod.GET)
	public String redirectToPage(@PathVariable String pageName) {
		return "/static/html/" + pageName;
	}
}
