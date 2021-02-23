package com.smartinm.springmvc.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smartinm.springmvc.entity.Producto;
import com.smartinm.springmvc.model.ProductoModel;
import com.smartinm.springmvc.service.impl.CategoriaServiceImpl;
import com.smartinm.springmvc.service.impl.ProductoServiceImpl;

@Component("productoConverter")
public class ProductoConverter {

	@Autowired
	ProductoServiceImpl productoServiceImpl;
	
	@Autowired
	CategoriaServiceImpl categoriaServiceImpl;
	
	//Entity a Model
	public ProductoModel entity2Model(Producto producto) {
		
		ProductoModel productoModel = new ProductoModel();
		
		productoModel.setId(producto.getId());
		productoModel.setNombre(producto.getNombre());
		productoModel.setIdCategoria(producto.getCategoria().getId());
		
		return productoModel;		
	}
	
	
	//Model a Entity
	public Producto model2Entity(ProductoModel productoModel) {
		
		Producto producto = new Producto();
		
		producto.setId(productoModel.getId());// != 0 ? productoModel.getId() : null);
		producto.setNombre(productoModel.getNombre());
		producto.setCategoria(categoriaServiceImpl.getCategoria(productoModel.getIdCategoria()));
		
		return producto;		
	}
	
}
