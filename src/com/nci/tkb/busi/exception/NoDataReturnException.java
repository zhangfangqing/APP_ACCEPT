package com.nci.tkb.busi.exception;

public class NoDataReturnException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7896944106361972486L;
	
	public NoDataReturnException(String errorNo) {
		super(errorNo);
	}
}
