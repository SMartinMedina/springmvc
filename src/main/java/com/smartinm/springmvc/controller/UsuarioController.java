package com.smartinm.springmvc.controller;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.smartinm.springmvc.converter.UsuarioConverter;
import com.smartinm.springmvc.entity.Usuario;
import com.smartinm.springmvc.model.UsuarioModel;
import com.smartinm.springmvc.repository.UsuarioJpaRepository;
import com.smartinm.springmvc.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	UsuarioConverter usuarioConverter;
	
	private static final Log LOG = LogFactory.getLog(UsuarioController.class);
	
	@GetMapping("/all")
	public ModelAndView all() {
		LOG.info("Entrando a: /all");
		ModelAndView mav = new ModelAndView("usuarios/list");
		mav.addObject("usuarios", usuarioServiceImpl.listAllUsuarios());
		return mav;
	} 
	@GetMapping("/formAdd")
	public ModelAndView formAdd() {
		LOG.info("Entrando a: /formAdd");
		ModelAndView mav = new ModelAndView("usuarios/form_add");
		mav.addObject("usuario", new Usuario());
		return mav;
	}
	@GetMapping("/formEdit/{id}")
	public ModelAndView formEdit(@ModelAttribute(name="id")int id) {
		LOG.info("Entrando a: /formEdit");
		ModelAndView mav = new ModelAndView("usuarios/form_edit");
		mav.addObject("usuario", usuarioServiceImpl.getUsuario(id));
		return mav;
	}
	
	@PostMapping("/editAction")
	public ModelAndView editAction(@ModelAttribute("usuario")UsuarioModel usuario) {
		LOG.info("Entrando a: /editAction");
		ModelAndView mav = new ModelAndView("usuarios/list");
		usuarioServiceImpl.updateUsuario(usuarioConverter.model2Entity(usuario));
		mav.addObject("usuarios", usuarioServiceImpl.listAllUsuarios());
		return mav;
	}
	
	@PostMapping("/addAction")
	public ModelAndView addAction(@ModelAttribute("usuario")Usuario usuario, BindingResult result) {
		LOG.info("Entrando a: /addAction");		
		ModelAndView mav = new ModelAndView("usuarios/form_add");
		if(!result.hasErrors()) {
			mav = new ModelAndView("usuarios/list");
			usuarioServiceImpl.addUsuario(usuario);
			mav.addObject("usuarios", usuarioServiceImpl.listAllUsuarios());
		}
		
		return mav;
	} 
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@ModelAttribute ("id")int id) {
		LOG.info("Entrando a: /delete/");
		ModelAndView mav = new ModelAndView("usuarios/list");
		usuarioServiceImpl.deleteUsuario(id);
		mav.addObject("usuarios", usuarioServiceImpl.listAllUsuarios());
		return mav;
	}
	

}
