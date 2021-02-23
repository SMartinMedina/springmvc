package com.smartinm.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.smartinm.springmvc.converter.CategoriaConverter;
import com.smartinm.springmvc.entity.Categoria;
import com.smartinm.springmvc.model.CategoriaModel;
import com.smartinm.springmvc.service.impl.CategoriaServiceImpl;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
	
	private static final Log LOG = LogFactory.getLog(CategoriaController.class);
	
	private final String DIRECTORIO = "categorias/"; 
	
	@Autowired
	CategoriaServiceImpl categoriaServiceImpl;
	
	@Autowired
	CategoriaConverter categoriaConverter;
	
	@GetMapping("/all")
	public ModelAndView all(){
		ModelAndView mav = new ModelAndView(DIRECTORIO + "list");
		mav.addObject("categorias", categoriaServiceImpl.listAllCategorias());
		return mav;
	}
	
	@GetMapping("/formAdd")
	public ModelAndView formAdd(){
		ModelAndView mav = new ModelAndView(DIRECTORIO + "form_add");
		mav.addObject("categoria", new Categoria());
		return mav;
	}
	
	@PostMapping("/addAction")
	public ModelAndView addAction(@ModelAttribute("categoria")CategoriaModel categoriaModel, BindingResult result){
		ModelAndView mav = new ModelAndView(DIRECTORIO + "list");
		if(!result.hasErrors()) {
			categoriaServiceImpl.addCategoria(categoriaConverter.model2Entity(categoriaModel));
			mav.addObject("categorias", categoriaServiceImpl.listAllCategorias());
		}else {
			mav = new ModelAndView(DIRECTORIO + "form_add");
			mav.addObject("categoria", new Categoria());
		}
		return mav;
	}
	
	@GetMapping("/formEdit/{id}")
	public ModelAndView formEdit(@ModelAttribute("id")int id) {
		ModelAndView mav = new ModelAndView(DIRECTORIO + "form_edit");
		mav.addObject("categoria", categoriaServiceImpl.getCategoria(id));		
		return mav;
	}

	@PostMapping("/editAction")
	public ModelAndView formEdit(@ModelAttribute("categoria")CategoriaModel categoriaModel, BindingResult result) {
		ModelAndView mav = new ModelAndView(DIRECTORIO + "list");
		if(!result.hasErrors()) {
			categoriaServiceImpl.updateCategoria(categoriaConverter.model2Entity(categoriaModel));
			mav.addObject("categorias", categoriaServiceImpl.listAllCategorias());
		}else {
			mav = new ModelAndView(DIRECTORIO + "form_edit");
			mav.addObject("categoria", categoriaServiceImpl.getCategoria(categoriaModel.getId()));
		}		
		return mav;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@ModelAttribute("id")int id) {
		ModelAndView mav = new ModelAndView(DIRECTORIO + "list");
		categoriaServiceImpl.deleteCategoria(id);
		mav.addObject("categorias", categoriaServiceImpl.listAllCategorias());		
		return mav;
	}
}
