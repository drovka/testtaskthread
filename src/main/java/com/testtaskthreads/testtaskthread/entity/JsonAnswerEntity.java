/**
 * 
 */
package com.testtaskthreads.testtaskthread.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Vova
 *
 */
@Entity
@Table(name="JsonAnswer")
public class JsonAnswerEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private Long keyValue;
	private String message;
	private Long creationDate;
	
	public JsonAnswerEntity() {};
	
	public JsonAnswerEntity(long keyValue, String message, Long creationDate) {
		
		this.keyValue = keyValue;
		this.message = message;
		this.creationDate = creationDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}

	public Long getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(Long keyValue) {
		this.keyValue = keyValue;
	}

	@Override
	public String toString() {
		return "JsonAnswerEntity [id=" + id + ", keyValue=" + keyValue + ", message=" + message + ", creationDate="
				+ creationDate + "]";
	}

}
