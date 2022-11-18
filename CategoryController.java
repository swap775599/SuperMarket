package AshishProject.Supemarket;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
public class CategoryController 
{
	@Autowired
	SessionFactory factory;

	@GetMapping("categories/{di}")
	public Category getCategory(@PathVariable int di)
	{
		
		Session session=factory.openSession();
		
		Category category=session.load(Category.class, di);
		
		return category;
		
	}

	@GetMapping("categories")
	public List<Category> getAllCategory()
	{
			Session session=factory.openSession();
				
		Query query=session.createQuery("from Category");
		
		List<Category> list=query.list();
		
		return list;
		}
	@PostMapping("categories")
	public Category addCategory(@RequestBody Category category)
	{
		
		Session session=factory.openSession();
		
		Transaction transaction=session.beginTransaction();
		
				session.save(category);
				
		transaction.commit();
		
		System.out.println("Category added into database");
			
		return category;
		
	}
	
	
	@DeleteMapping("categories/{cid}")
	public String deleteCategory(@PathVariable int di)
	{
	
		Session session=factory.openSession();
		
		Category category=session.load(Category.class, di);
		
		Transaction transaction=session.beginTransaction();
		
				session.delete(category);
				
		transaction.commit();
		
		return "record deleted";
	}
	
	
	@PutMapping("categories")
	public String updatecategory(@RequestBody Category clientCategory)
	{
		
				Session session=factory.openSession();
				
				Category category=session.load(Category.class,clientCategory.getdi());
				
				category.setName(clientCategory.getName());
				

				Transaction transaction=session.beginTransaction();
				
					session.update(category);
								
				transaction.commit();
				
				
				return "Record Updated";

	}

	
	
}