package AshishProject.Supemarket;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")

public class ProductController 
{
	@Autowired
	SessionFactory factory;
	
	@PostMapping("products/{di}")
	public Product addProduct(@RequestBody Product product,@PathVariable int di)
	{
		System.out.println("Category id is " + di);
		
		Session session=factory.openSession();
		
		Category category=session.load(Category.class,di);
		
		List<Product> productlist=category.getProducts();
			
		
		Transaction transaction=session.beginTransaction();
		
			productlist.add(product);
						
		transaction.commit();
		
		
		System.out.println("product added into database");
		
		return product;
		
	
	}

	
	
	@DeleteMapping("products/{pid}")
	public String deleteProduct(@PathVariable int pid)
	{
		
		Session session=factory.openSession();
		
		NativeQuery<Object[]> query=session.createSQLQuery("select category.cid  , category.name from category,product where product.cid=category.cid and pid="+pid);

		List<Object[]> list = query.list(); 
		
		System.out.println(list.size());
		
		Object[] array=list.get(0);
		
		int di=(int)array[0];
		
	System.out.println(pid + " " + di);
			
	
        Category category=session.load(Category.class,di);
				
        List<Product> productlist=category.getProducts();
		
		

		Product product=session.load(Product.class,pid);
				
		Transaction transaction=session.beginTransaction();
		
			productlist.remove(product);
						
		transaction.commit();
		
		
		return "record deleted";
				
	}
	
	
	@GetMapping("products/{pid}")
	public String viewProduct(@PathVariable int pid)
	{
		
				Session session=factory.openSession();
				
				NativeQuery<Object[]> query=session.createSQLQuery("select product.pid,product.name,product.price, category.di,category.name as cname from category,product where product.di=category.di and pid="+pid);

				List<Object[]> list = query.list(); 
				
				Object[] array=list.get(0);
				
				return Arrays.toString(array);
	}
	

	@GetMapping("products")
	public String getAllProducts()
	{
		
				Session session=factory.openSession();
				
				NativeQuery<Object[]> query=session.createSQLQuery("select product.pid,product.name,product.price, category.di ,category.name as cname from category,product where product.di=category.di");

				List<Object[]> list = query.list(); 
					
				StringBuffer stringBuffer=new StringBuffer();
				
				for(int i=0;i<list.size();i++)
				{
					Object[] array=list.get(i);
					
					stringBuffer.append(Arrays.toString(array) + " , ");
					
				}
				
				return stringBuffer.toString();
	}


	
	
	@PutMapping("products")
	public String updateProduct(@RequestBody Product clientProduct)
	{
		
				Session session=factory.openSession();
				
				
				Product product=session.load(Product.class,clientProduct.getPid());
				
				product.setName(clientProduct.getName());
				
				product.setPrice(clientProduct.getPrice());
				

				Transaction transaction=session.beginTransaction();
				
					session.update(product);
								
				transaction.commit();
				
				
				return "Record Updated";

	}
	

}
