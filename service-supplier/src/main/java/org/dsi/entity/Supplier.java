package org.dsi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Supplier {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    private String email;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Supplier(Long id, String name, String email) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
		}
		public Supplier() {
			super();
		}
		@Override
		public String toString() {
			return "Supplier [id=" + id + ", name=" + name + ", email=" + email + "]";
		}
		
	    
	    
}
