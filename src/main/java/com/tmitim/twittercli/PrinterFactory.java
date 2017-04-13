package com.tmitim.twittercli;

public class PrinterFactory {
	private static Printer instance = null;
	
	protected PrinterFactory() {	
	}
	
	public static Printer getPrinter(){
		if (instance == null) {
			return new SystemPrinter();
		} else {
			return instance;
		}
	}
}
