package com.sergio.productsandcategories.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sergio.productsandcategories.models.Category;
import com.sergio.productsandcategories.models.CategoryProduct;
import com.sergio.productsandcategories.models.Product;
import com.sergio.productsandcategories.service.ProdCatService;

@Controller
public class ProdCatController {
	private final ProdCatService pCS;
	public ProdCatController(ProdCatService pCS) {
		this.pCS = pCS;
	}
	//load new product
	@RequestMapping("/products/new")
	public String newProd(@ModelAttribute("productObj") Product product) {
		return "/productsandcategories/newprod.jsp";
	}
	@RequestMapping(value="/products/new", method=RequestMethod.POST)
	public String newProduct(@Valid @ModelAttribute("productObj") Product product, BindingResult result) {
		if (result.hasErrors()) {
	        
			return "/productsandcategories/newprod.jsp";
		}
		else {
			pCS.createProduct(product);
			return "redirect:/products/new";
		}
	}
	//new category page 
	@RequestMapping("/categories/new")
	public String newCat(@ModelAttribute("categoryObj") Category category) {
		return "/productsandcategories/newcat.jsp";
	}
	@RequestMapping(value="/categories/new", method=RequestMethod.POST)
	public String newCategory(@Valid @ModelAttribute("categoryObj") Category category, BindingResult result) {
		if (result.hasErrors()) {
	        
			return "/productsandcategories/newprod.jsp";
		}
		else {
			pCS.createCategory(category);
			return "redirect:/categories/new";
		}
	}
	//add category to product
	@RequestMapping("/products/{id}")
	public String addProductToNewCategory(@PathVariable("id") Long id, @ModelAttribute("categoryProductObj") CategoryProduct cp, Model model) {
		Product p = pCS.findProduct(id);
		//gets product
		model.addAttribute("product", p);
		//gets categories from prodct
		model.addAttribute("productCategories", p.getCategories());
		//gets all categories minus the ones already added
		model.addAttribute("allCategories", pCS.findCatsToAdd(id));
		return "/productsandcategories/showprod.jsp";
	}
	//product post request below
	@RequestMapping(value="/products/{product.id}", method=RequestMethod.POST)
	public String addCategoryToProduct(@PathVariable("product.id") Long id, @Valid @ModelAttribute("categoryProductObj")CategoryProduct c, BindingResult result) {
		System.out.println("Hello");
		pCS.addCatToProd(id, c);
		return "redirect:/products/"+id;
	}
	//add product to category
	@RequestMapping("/categories/{id}")
	public String addToNewCategory(@PathVariable("id") Long id, @ModelAttribute("productCategoryObj") CategoryProduct cp, Model model) {
		Category c = pCS.findCategory(id);
		model.addAttribute("Category", c);
		model.addAttribute("productCategories", c.getProducts());
		model.addAttribute("allProducts", pCS.findProdToAdd(id));
		return "/productsandcategories/showcat.jsp";
	}
	@RequestMapping(value="/categories/{categories.id}", method=RequestMethod.POST)
	public String addPToCategory(@PathVariable("categories.id") Long id, @Valid @ModelAttribute("productCategoryObj")CategoryProduct c, BindingResult result) {
		System.out.println("Hello");
		pCS.addProdToCat(id, c);
		return "redirect:/categories/"+id;
	}
	
	
	
}
