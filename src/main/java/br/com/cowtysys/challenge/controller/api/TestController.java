package br.com.cowtysys.challenge.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.cowtysys.challenge.domain.ChallengeResponse;
import br.com.cowtysys.challenge.facade.ChallengeFacade;

@Controller
public class TestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

	@Autowired private ChallengeFacade facade;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	private ChallengeResponse testGet(){
		LOGGER.info("TEST OK!");
		String message = "API Online";
		ChallengeResponse response = new ChallengeResponse(message);

		return response;
	}
}
