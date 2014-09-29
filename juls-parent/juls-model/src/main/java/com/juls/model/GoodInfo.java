package com.juls.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class GoodInfo {

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((composition == null) ? 0 : composition.hashCode());
			result = prime * result
					+ ((description == null) ? 0 : description.hashCode());
			result = prime * result + ((mass == null) ? 0 : mass.hashCode());
			result = prime * result + ((type == null) ? 0 : type.hashCode());
			return result;
		}
	
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof GoodInfo))
				return false;
			GoodInfo other = (GoodInfo) obj;
			if (composition == null) {
				if (other.composition != null)
					return false;
			} else if (!composition.equals(other.composition))
				return false;
			if (description == null) {
				if (other.description != null)
					return false;
			} else if (!description.equals(other.description))
				return false;
			if (mass == null) {
				if (other.mass != null)
					return false;
			} else if (!mass.equals(other.mass))
				return false;
			if (type == null) {
				if (other.type != null)
					return false;
			} else if (!type.equals(other.type))
				return false;
			return true;
		}

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
