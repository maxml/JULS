package com.juls.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class GoodInfo {
	

		public GoodInfo(){
			
		}
		
		@Column(name="description")
		private String description;
		@Column(name="type")
		private String type;
		@Column(name="mass")
		private String mass;
		@Column(name="composition")
		private String composition;
		
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getMass() {
			return mass;
		}
		public void setMass(String mass) {
			this.mass = mass;
		}
		public String getComposition() {
			return composition;
		}
		public void setComposition(String composition) {
			this.composition = composition;
		}
}
