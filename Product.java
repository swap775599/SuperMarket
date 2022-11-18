package AshishProject.Supemarket;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product 
{
	@Id
	int pid;
	String name;
	int price;
	
	
	public Product() 
	{
		super();
		
	}


	public Product(int pid, String name, int price) 
	{
		super();
		this.pid = pid;
		this.name = name;
		this.price = price;
	}


	public int getPid() {
		return pid;
	}


	public void setPid(int pid) {
		this.pid = pid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "Product [pid=" + pid + ", name=" + name + ", price=" + price + "]";
	}
	
	
	
	
}