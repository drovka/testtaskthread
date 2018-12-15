/**
 * 
 */
package com.testtaskthreads.testtaskthread.services;

/**
 * @author Vova
 *
 */
public class TwoMethodsServiceImpl implements TwoMethodsService {

	/* (non-Javadoc)
	 * @see com.testtaskthreads.testtaskthread.services.TwoMethodsService#firstMethod(java.lang.Long)
	 */
	@Override
	public String firstMethod(Long value) {
		// emulate heavy remote call
	   try {
	        Thread.sleep(Double.valueOf(Math.random()*2000).longValue() ) ;
	    } catch (InterruptedException e) {
	        // ignore
	    }
	    
	    //return "Hello, you are in firstMethod, entered value is - " + value + "!";
	    return "Hello, " + value + "!";
		
	}

	/* (non-Javadoc)
	 * @see com.testtaskthreads.testtaskthread.services.TwoMethodsService#secondMethod(java.lang.Long)
	 */
	@Override
	public String secondMethod(Long value) {
		
		// emulate heavy remote call
		try {
			Thread.sleep(Double.valueOf(Math.random()*2000).longValue() ) ;
		} catch (InterruptedException e) {
			// ignore
		}
		//return "Hello, you are in secondMethod, entered value is - " + value + "!";
		return value + "*2 = " + (value * 2) + ".";
	}

}
