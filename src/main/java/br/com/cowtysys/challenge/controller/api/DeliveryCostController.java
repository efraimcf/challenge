package br.com.cowtysys.challenge.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.cowtysys.challenge.domain.ChallengeResponse;
import br.com.cowtysys.challenge.domain.DeliveryCostResponse;
import br.com.cowtysys.challenge.exception.DestinationUnreachableException;
import br.com.cowtysys.challenge.exception.InvalidParameterValueException;
import br.com.cowtysys.challenge.exception.NodeNotFoundException;
import br.com.cowtysys.challenge.facade.ChallengeFacade;

@Controller
public class DeliveryCostController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryCostController.class);
	
	@Autowired private ChallengeFacade facade;
	
	@RequestMapping(value = "/delivery", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	private ChallengeResponse getDeliveryCost(@RequestParam(value="source") String source,
			@RequestParam(value="target") String target,
			@RequestParam(value="autonomy") double autonomy,
			@RequestParam(value="gasCost") double gasCost) throws DestinationUnreachableException, NodeNotFoundException, InvalidParameterValueException{
		
		LOGGER.info("Getting route with data: [Source=" + source + "; Target=" + target + ";Autonomy=" + autonomy + "; GasCost=" + gasCost + ";]");

		DeliveryCostResponse deliveryCostResponse = getFacade().getShortestPath(source, target, autonomy, gasCost);
		
		ChallengeResponse response = new ChallengeResponse(deliveryCostResponse);
		return response;
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ChallengeResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
		LOGGER.error(e.getMessage(), e);
		List<String> errorMessages = new ArrayList<String>();
		List<ObjectError> errors = e.getBindingResult().getAllErrors();
		for (ObjectError error : errors){
			errorMessages.add(error.getDefaultMessage());
		}
		ChallengeResponse response = new ChallengeResponse();
		response.setErrorMessages(errorMessages);
		
		return response;
	}

	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ChallengeResponse handleExceptions(Exception e){
		LOGGER.error(e.getMessage(), e);
		String errorMessage = e.getMessage().split("\n")[0];
		List<String> errorMessages = new ArrayList<String>();
		errorMessages.add(errorMessage);
		ChallengeResponse response = new ChallengeResponse();
		response.setErrorMessages(errorMessages);
		
		return response;
	}

	public ChallengeFacade getFacade() {
		return facade;
	}
}
