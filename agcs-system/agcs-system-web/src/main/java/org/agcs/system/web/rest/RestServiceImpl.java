package org.agcs.system.web.rest;

import org.agcs.system.web.entity.glpt.GlptUserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api")
public class RestServiceImpl implements RestServiceI{
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)  
	@ResponseBody
    public GlptUserEntity view(@PathVariable("id") String id) { 
		System.out.println("Id:"+id);
		GlptUserEntity user = new GlptUserEntity();  
        user.setId(id);  
        user.setUserName("wangyong");
        return user;  
    }

}
