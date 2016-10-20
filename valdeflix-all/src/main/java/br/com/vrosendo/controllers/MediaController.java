package br.com.vrosendo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vrosendo.domain.Group;
import br.com.vrosendo.services.MediaService;

@RestController
@RequestMapping("/media")
public class MediaController {

	@Autowired
	MediaService mediaService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Group> list(@RequestParam Long idUser){	
		return mediaService.listMediaByUser(idUser);
	}
	
}
