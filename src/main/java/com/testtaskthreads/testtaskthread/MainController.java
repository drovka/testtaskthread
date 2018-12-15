/**
 * 
 */
package com.testtaskthreads.testtaskthread;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.testtaskthreads.testtaskthread.entity.JsonAnswerEntity;
import com.testtaskthreads.testtaskthread.services.ManageArrayService;

/**
 * @author Vova
 *
 */
@RestController
public class MainController {
	
	private static final String tmpl = "You sent, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	ManageArrayService manarService;
	
	@RequestMapping("/testing")
	public  JsonAnswerEntity testing(@RequestParam(value="name", defaultValue="default") String name) {
		return new JsonAnswerEntity(counter.incrementAndGet(),
					String.format(tmpl, name), System.currentTimeMillis());
	}
	
	@RequestMapping(value = "/showByLong", method = RequestMethod.GET)
	public List<JsonAnswerEntity> showByLong(@RequestParam("longArray") Long[] longArray) {
		
		return manarService.loadEntityByLong(Arrays.asList(longArray));
	}
	
	/**
	 * this is an emulation for post method need to be
	 * override
	 * @param val
	 * @return
	 */
	@RequestMapping(value = "/populate", method = RequestMethod.POST)
	@ResponseBody
	public Map<Long,String> populate(@RequestParam("longArray") Long[] longArray) {
		
		
		//check if array is not empty
		if(longArray == null || longArray.length <= 0) {
			throw new IllegalArgumentException("An array can't be empty! Try again");
		}
		
		return manarService.buildAnswer(Arrays.asList(longArray));
		//not multithreading
		//return manarService.processArray(Arrays.asList(longArray));
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final String exceptionHandlerIllegalArgumentException(
						final IllegalArgumentException ex) {
		return '"' + ex.getMessage() + '"';
	}
	
	@RequestMapping("/showAll")
	public List<JsonAnswerEntity> showAll() {
		return (List<JsonAnswerEntity>) manarService.loadAll();
	}
	
	@RequestMapping("/deleteAll")
	public String deleteAll() {
		manarService.deleteAll();
		return "DONE!";
	}
}
