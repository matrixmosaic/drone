package com.musala.drones.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.musala.drones.dto.operation.LoadDto;

/**
 * @author George J. Budeba
 *
 */
@Entity
@Table(name = "dm_load", schema="dn_main")
@Audited
@AuditTable( value = "aud_load", schema="dn_aud")
public class Load {
	@Id
	@Column(name = "load_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long loadId;
	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "load_type_id", nullable = true)
	private LoadType type;
	
	   
		
		@Column(name = "name", length = 255)
		@Pattern(regexp = "[A-Za-z0-9\\-\\_]+", message = " The name must contain only letters, numbers, ‘-‘, ‘_’ ")
		private String name;
		
		@Column(name = "code", length = 255)
		@Pattern(regexp = "[A-Z0-9\\-\\_]+", message = " The name must contain only letters, numbers, ‘-‘, ‘_’ ")
		private String code;
		

	    @Column(name = "weight", precision =12, scale = 4)
	    private BigDecimal weight;
	    
		

		
		
		@OneToOne(optional = true, fetch = FetchType.LAZY)
		@JoinColumn(name = "trip_id",  referencedColumnName ="trip_id")
		private Trip trip;
		
		
	    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "load")
	    private List<Item> items;
	    
	    @Lob
	    @Basic(fetch = FetchType.LAZY)
	    private byte[] image;


		public Load() {
			super();
		}

		
		
		/**
		 * @param loadId
		 * @param type
		 * @param name
		 * @param code
		 * @param weight
		 * @param trip
		 * @param items
		 * @param image
		 */
		public Load(Long loadId, LoadType type,
				@Pattern(regexp = "[A-Za-z0-9\\-\\_]+", message = " The name must contain only letters, numbers, ‘-‘, ‘_’ ") String name,
				@Pattern(regexp = "[A-Z0-9\\-\\_]+", message = " The name must contain only letters, numbers, ‘-‘, ‘_’ ") String code,
				BigDecimal weight, Trip trip, List<Item> items, byte[] image) {
			super();
			this.loadId = loadId;
			this.type = type;
			this.name = name;
			this.code = code;
			this.weight = weight;
			this.trip = trip;
			this.items = items;
			this.image = image;
		}



		public Load(LoadDto loadDto, LoadType loadType) {
			this.loadId = loadDto.getLoadId();
			this.type = loadType;
			this.name = loadDto.getName();
			this.code = loadDto.getCode();
			this.weight = loadDto.getWeight();
			this.image = loadDto.getImage();
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
		 * @return the type
		 */
		public LoadType getType() {
			return type;
		}

		/**
		 * @param type the type to set
		 */
		public void setType(LoadType type) {
			this.type = type;
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
		public List<Item> getItems() {
			return items;
		}

		/**
		 * @param items the items to set
		 */
		public void setItems(List<Item> items) {
			this.items = items;
		}



		/**
		 * @return the trip
		 */
		public Trip getTrip() {
			return trip;
		}



		/**
		 * @param trip the trip to set
		 */
		public void setTrip(Trip trip) {
			this.trip = trip;
		}
		
	    
	    
	    
	    
	    
	    
		
		
}
