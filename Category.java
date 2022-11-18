package AshishProject.Supemarket;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;



@Entity
public class Category 
{
	@Id
	int di;
	String name;
	@OneToMany (targetEntity = Product.class,cascade = CascadeType.ALL,orphanRemoval =true)
	@JoinColumn(name="di")
	List<Product> products;
	
	public Category() {
		super();
		
	}

	public Category(int di, String name, List<Product> products) {
		super();
		this.di = di;
		this.name = name;
		this.products = products;
	}

	@Override
	public String toString() {
		return "Category [di=" + di + ", name=" + name + ", products=" + products + "]";
	}

	public int getdi() {
		return di;
	}

	public void setdi(int di) {
		this.di = di;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
	
	
	
	
}