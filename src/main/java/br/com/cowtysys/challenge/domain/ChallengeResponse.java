package br.com.cowtysys.challenge.domain;

import java.util.List;

public class ChallengeResponse {

	private Object result;
	
	private List<String> errorMessages;

	public ChallengeResponse(){}
	
	public ChallengeResponse(Object result){
		setResult(result);
	}
	
	public ChallengeResponse(List<String> errorMessages){
		setErrorMessages(errorMessages);
	}
	
	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}
}
