/**
 * 
 */
package com.musala.drones.dto.operation;

import java.util.List;

import com.musala.drones.model.Item;

public class ItemDto {
	
	private Integer itemId;
    private String name;
    private String code;

    private String description;
    
    private String loadId;


/**
 * 
 */
public ItemDto() {
	super();
}




/**
 * @param itemId
 * @param name
 * @param code
 * @param description
 * @param loadId
 */
public ItemDto(Item item) {
	this.itemId = item.getItemId();
	this.name = item.getName();
	this.code = item.getCode();
	this.description = item.getDescription();
}










/**
 * @return the itemId
 */
public Integer getItemId() {
	return itemId;
}


/**
 * @param itemId the itemId to set
 */
public void setItemId(Integer itemId) {
	this.itemId = itemId;
}


/**
 * @return the name
 */
public String getName() {
	return name;
}


/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}


/**
 * @return the code
 */
public String getCode() {
	return code;
}


/**
 * @param code the code to set
 */
public void setCode(String code) {
	this.code = code;
}


/**
 * @return the description
 */
public String getDescription() {
	return description;
}


/**
 * @param description the description to set
 */
public void setDescription(String description) {
	this.description = description;
}


/**
 * @return the loadId
 */
public String getLoadId() {
	return loadId;
}


/**
 * @param loadId the loadId to set
 */
public void setLoadId(String loadId) {
	this.loadId = loadId;
}




}
