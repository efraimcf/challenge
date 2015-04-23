package br.com.cowtysys.challenge.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.cowtysys.challenge.util.logger.CowtySysLogger;
import br.com.cowtysys.challenge.util.logger.CowtySysLoggerFactory;

@Controller
public class TestController {

	private static final CowtySysLogger LOGGER = CowtySysLoggerFactory.getInstance().getLogger(TestController.class);
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	private Map<String, Object> testGet(){
		LOGGER.info("OK");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Hello", "Word");
		
		return map;
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	private Map<String, Object> testPost(@RequestBody Map<String, Object> map){
		LOGGER.info("OK");
		
		return map;
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public Map<String, Object> handleExceptions(Exception e){
		LOGGER.error(e.getMessage(), e);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", e.getMessage());
		
		return map;
	}
}
