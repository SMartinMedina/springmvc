package com.smartinm.springmvc.controller;

import java.util.List;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.smartinm.springmvc.converter.ProductoConverter;
import com.smartinm.springmvc.entity.Categoria;
import com.smartinm.springmvc.entity.Producto;
import com.smartinm.springmvc.model.ProductoModel;
import com.smartinm.springmvc.repository.CategoriaJpaRepository;
import com.smartinm.springmvc.repository.ProductoJpaRepository;
import com.smartinm.springmvc.service.impl.CategoriaServiceImpl;
import com.smartinm.springmvc.service.impl.ProductoServiceImpl;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
	@Qualifier("productoJpaRepository")
	private ProductoJpaRepository productoJpaRepository;
	
	@Autowired
	@Qualifier("productoServiceImpl")
	ProductoServiceImpl productoServiceImpl;

	@Autowired
	@Qualifier("categoriaServiceImpl")
	CategoriaServiceImpl categoriaServiceImpl;	
	
	@Autowired
	@Qualifier("productoConverter")
	ProductoConverter productoConverter;
	
	private static final Log LOG = LogFactory.getLog(ProductoController.class);
	
	private String DIRECTORIO = "productos/"; 
	
	@GetMapping("/all")
	public ModelAndView all() {
		LOG.info("Call:" + "listAllProductos");
		List<Producto> productos = productoJpaRepository.findAll();
		ModelAndView mav = new ModelAndView(DIRECTORIO + "list");
		mav.addObject("productos",productos);
		return mav;		
	}
	
	@GetMapping("/formAdd")
	public ModelAndView formAdd(Model model) {
		LOG.info("Call:" + "formAddProducto");
		ModelAndView mav = new ModelAndView(DIRECTORIO + "form_add");
		mav.addObject("producto", new ProductoModel());
		mav.addObject("categorias", categoriaServiceImpl.listAllCategorias());
		return mav;		
	}
	
	@PostMapping("/addAction")
	public ModelAndView addAction(@ModelAttribute("producto") ProductoModel producto, BindingResult bindingResult) {
		LOG.info("Call:" + "addAction");
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			mav = new ModelAndView(DIRECTORIO + "form_add");
			mav.addObject("producto", new Producto());
			mav.addObject("categorias", categoriaServiceImpl.listAllCategorias());
		}else {
			mav = new ModelAndView(DIRECTORIO + "list");
			Producto productoEntity = new Producto();
			productoEntity = productoConverter.model2Entity(producto);
			productoServiceImpl.addProducto(productoEntity);
			mav.addObject("productos", productoServiceImpl.listAllProductos());
		}
		return mav;		
	}
	
	
	@GetMapping("/formEdit/{id}")
	public ModelAndView formEdit(@ModelAttribute("id")int id) {
		LOG.info("Call:" + "formEditProducto");
		ModelAndView mav = new ModelAndView(DIRECTORIO + "form_edit");
		mav.addObject("producto", productoConverter.entity2Model(productoServiceImpl.getProducto(id)));
		mav.addObject("categorias", categoriaServiceImpl.listAllCategorias());
		return mav;		
	}
	
	@PostMapping("/editAction")
	public ModelAndView editAction(@ModelAttribute("producto") ProductoModel producto, BindingResult bindingResult) {
		LOG.info("Call:" + "addAction");
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			mav = new ModelAndView(DIRECTORIO + "form_edit");
			mav.addObject("producto", productoServiceImpl.getProducto(producto.getId()));
			mav.addObject("categorias", categoriaServiceImpl.listAllCategorias());
		}else {
			mav = new ModelAndView(DIRECTORIO + "list");
			Producto productoEntity = new Producto();
			productoEntity = productoConverter.model2Entity(producto);
			productoServiceImpl.updateProducto(productoEntity);
			mav.addObject("productos", productoServiceImpl.listAllProductos());
		}
		return mav;		
	}
	
	
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@ModelAttribute ("id")int id) {
		LOG.info("Entrando a: /delete/");
		ModelAndView mav = new ModelAndView(DIRECTORIO + "list");
		productoServiceImpl.deleteProducto(id);
		mav.addObject("productos", productoServiceImpl.listAllProductos());
		return mav;		
	}
	

}
