package com.smartinm.springmvc.converter;

import org.springframework.stereotype.Component;

import com.smartinm.springmvc.entity.Categoria;
import com.smartinm.springmvc.model.CategoriaModel;

@Component("categoriaConverter")
public class CategoriaConverter {

	public Categoria model2Entity(CategoriaModel categoriaModel) {
		Categoria categoria = new Categoria();
		categoria.setId(categoriaModel.getId());
		categoria.setNombre(categoriaModel.getNombre());
		return categoria;
	}
	
	public CategoriaModel entity2Model(Categoria categoria) {
		CategoriaModel categoriaModel = new CategoriaModel();
		categoriaModel.setId(categoria.getId());
		categoriaModel.setNombre(categoria.getNombre());
		return categoriaModel;
	}
}
