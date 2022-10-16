package com.musala.drones.dto.operation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Lob;

import com.musala.drones.model.Item;
import com.musala.drones.model.Load;

/**
 * @author George J. Budeba
 *
 */

public class LoadDto {

	private Long loadId;
	

	
	   private String loadTypeCode;
	
	   
		
		private String name;
		
		private String code;
		

	    private BigDecimal weight;
	    
		private String tripId;
		
	    private List<ItemDto> items;
	    
	    @Lob
	    private byte[] image;

		public LoadDto() {
			super();
		}
		
		

		/**
		 * @param loadId
		 * @param loadTypeCode
		 * @param name
		 * @param code
		 * @param weight
		 * @param tripId
		 * @param items
		 * @param image
		 */
	


		public LoadDto(Load load) {
			this.loadId = load.getLoadId();
			this.loadTypeCode = load.getType().getCode();
			this.name = load.getName();
			this.code = load.getCode();
			this.weight = load.getWeight();
			this.items = this.getItemDtoList(load.getItems());
			this.image = load.getImage();
		}

		
		public  List<ItemDto> getItemDtoList(List<Item> itms) {
			
			items = new ArrayList<ItemDto>();
			for(Item itm : itms) {
				items.add(new ItemDto(itm));
			}
			
			return items;

		}
		
		/**
		 * @return the loadId
		 */
		public Long getLoadId() {
			return loadId;
		}

		/**
		 * @param loadId the loadId to set
		 */
		public void setLoadId(Long loadId) {
			this.loadId = loadId;
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
		 * @return the weight
		 */
		public BigDecimal getWeight() {
			return weight;
		}

		/**
		 * @param weight the weight to set
		 */
		public void setWeight(BigDecimal weight) {
			this.weight = weight;
		}

		/**
		 * @return the image
		 */
		public byte[] getImage() {
			return image;
		}

		/**
		 * @param image the image to set
		 */
		public void setImage(byte[] image) {
			this.image = image;
		}

		/**
		 * @return the items
		 */
		public List<ItemDto> getItems() {
			return items;
		}

		/**
		 * @param items the items to set
		 */
		public void setItems(List<ItemDto> items) {
			this.items = items;
		}

		

		/**
		 * @return the tripId
		 */
		public String getTripId() {
			return tripId;
		}

		/**
		 * @param tripId the tripId to set
		 */
		public void setTripId(String tripId) {
			this.tripId = tripId;
		}

		/**
		 * @return the loadTypeCode
		 */
		public String getLoadTypeCode() {
			return loadTypeCode;
		}

		/**
		 * @param loadTypeCode the loadTypeCode to set
		 */
		public void setLoadTypeCode(String loadTypeCode) {
			this.loadTypeCode = loadTypeCode;
		}
		
	    
	    
	    
	    
	    
	    
		
		
}
