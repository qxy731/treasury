package com.soule.crm.pfm.dsl.parser;

public class TokenException extends Exception {
    private static final long serialVersionUID = 5655156438042908209L;
    private String msg;

	public TokenException(String string) {
		msg = string;
	}

	public String getMessage() {
		
		return msg;
	}

}
