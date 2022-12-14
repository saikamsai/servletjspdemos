package com.example;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;  
import javax.persistence.Table;

@Entity
@Table(name = "products")

public class Products {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	
	@Column(name = "name")
    private String name;
	
	@Column(name = "color")
    private String color;
	
	@Column(name = "price")
    private Double price;
        
        public Products() {
                
        }
        
        public Products(int id, String name, String color, Double price) {
        	this.id = id;
            this.name = name;
            this.color = color;
            this.price = price;
        }
        
        public int getId() {return this.id;}
        public String getName() { return this.name;}
        public String getColor() { return this.color;}
        public Double getPrice() { return this.price;}
        
        public void setID(int id) { this.id = id;}
        public void setName(String name) { this.name = name;}
        public void setColor(String color) { this.color = color;}
        public void setPrice(Double price) { this.price = price;}

}
        
