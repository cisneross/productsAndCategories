package com.sergio.productsandcategories.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sergio.productsandcategories.models.Category;
import com.sergio.productsandcategories.models.CategoryProduct;
import com.sergio.productsandcategories.models.Product;
import com.sergio.productsandcategories.repository.CategoryRepository;
import com.sergio.productsandcategories.repository.ProdCatRepository;
import com.sergio.productsandcategories.repository.ProductRepository;

@Service
public class ProdCatService {
	private final ProductRepository pR;
	private final CategoryRepository cR;
	private final ProdCatRepository pCR;
	public ProdCatService(ProductRepository pR, CategoryRepository cR, ProdCatRepository pCR) {
		this.pR = pR;
		this.cR = cR;
		this.pCR = pCR;
	}
	//create a new product
	public Product createProduct(Product product) {
		return pR.save(product);
	}
	//create category
	public Category createCategory(Category category) {
		return cR.save(category);
	}
	//finds a specific product with an id
	public Product findProduct(Long id) {
		Optional<Product> optionalProd = pR.findById(id);
		if(optionalProd.isPresent()) {
			Product prod = optionalProd.get();
			return prod;
		}
		else {
			return null;
		}
	}
	//find a specific category with an id
	public Category findCategory(Long id) {
		Optional<Category> optionalCat = cR.findById(id);
		if(optionalCat.isPresent()) {
			Category cat = optionalCat.get();
			return cat;
		}
		else {
			return null;
		}
	}
	//gets all categories and all product categories and removes 
	//the product categories  from the entire category list
	public List<Category> findCatsToAdd(Long id) {
		Optional<Product> optionalProd = pR.findById(id);
		Product prod = optionalProd.get();
		List<Category> categories = prod.getCategories();
		List<Category> allCats = cR.findAll();
		for (Category c : categories ) {
			allCats.remove(c);
		}
		return allCats;
	} 
	//Gets all products and category products and removes
	//the category products from the entire product list
	public List<Product> findProdToAdd(Long id) {
		Optional<Category> optionalCat = cR.findById(id);
		Category cat = optionalCat.get();
		List<Product> products = cat.getProducts();
		List<Product> allProd = pR.findAll();
		for (Product p : products ) {
			allProd.remove(p);
		}
		return allProd;
	} 
	
	
	
	public void addProdToCat(Long id, CategoryProduct c) {
		pCR.save(c);
	}
	//Saves and creates tp CategoryProduct
	public void addCatToProd(Long id, CategoryProduct c){
//		Optional<Product> optionalProd = pR.findById(id);
//		Product prod = optionalProd.get();
//		List<Category> categories = prod.getCategories();
//		categories.add(c);
		pCR.save(c);
	}
}
