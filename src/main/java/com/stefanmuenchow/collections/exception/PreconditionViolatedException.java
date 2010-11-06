package com.stefanmuenchow.collections.exception;

public class PreconditionViolatedException extends Exception {
	private static final long serialVersionUID = -7129585369401847680L;
	
	public PreconditionViolatedException(String msg) {
		super(msg);
	}
}
