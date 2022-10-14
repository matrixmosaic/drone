/**
 * 
 */
package com.musala.drones.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Table(name = "dm_item", schema="dn_main")
@Audited
@AuditTable( value = "aud_item", schema="dn_aud")
public class Item {
	@Id
	@Column(name = "item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer itemId;
	


@Column(name = "name", length = 255)
@Pattern(regexp = "[A-Za-z0-9\\-\\_]+", message = " The name must contain only letters, numbers, ‘-‘, ‘_’ ")
private String name;

@Column(name = "code", length = 255)
@Pattern(regexp = "[A-Z0-9\\-\\_]+", message = " The name must contain only letters, numbers, ‘-‘, ‘_’ ")
private String code;

@Column(columnDefinition = "TEXT",name ="description")
private String description;


@ManyToOne( fetch = FetchType.LAZY)
@JoinColumn(name = "load_id", nullable = true)
private Load load;


/**
 * 
 */
public Item() {
	super();
}


/**
 * @param itemId
 * @param name
 * @param code
 * @param description
 * @param load
 */
public Item(Integer itemId,
		@Pattern(regexp = "[A-Za-z0-9\\-\\_]+", message = " The name must contain only letters, numbers, ‘-‘, ‘_’ ") String name,
		@Pattern(regexp = "[A-Z0-9\\-\\_]+", message = " The name must contain only letters, numbers, ‘-‘, ‘_’ ") String code,
		String description, Load load) {
	super();
	this.itemId = itemId;
	this.name = name;
	this.code = code;
	this.description = description;
	this.load = load;
}


public Item(Item item, Load load) {
	this.itemId = item.getItemId();
	this.name = item.getName();
	this.code = item.getCode();
	this.description = item.getDescription();
	this.load = load;
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
 * @return the load
 */
public Load getLoad() {
	return load;
}


/**
 * @param load the load to set
 */
public void setLoad(Load load) {
	this.load = load;
}


}
