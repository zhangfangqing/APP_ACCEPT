package com.nci.tkb.busi.exception;

public class DataNoFoundException extends NullPointerException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6872975836350212247L;

	public DataNoFoundException(String errorNo) {
		super(errorNo);
	}

}
