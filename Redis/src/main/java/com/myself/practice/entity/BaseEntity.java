package com.myself.practice.entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;


@Data
@MappedSuperclass
public class BaseEntity implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Date createdOn;
private Date ModifiedOn;
private boolean isDelete;
}
