package com.smartinm.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.smartinm.springmvc.entity.Producto;
import com.smartinm.springmvc.repository.ProductoJpaRepository;
import com.smartinm.springmvc.service.ProductoService;

@Service
@Qualifier("productoServiceImpl")
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	@Qualifier("productoJpaRepository")
	ProductoJpaRepository productoJpaRepository;

	@Override
	public List<Producto> listAllProductos() {
		return productoJpaRepository.findAll();
	}

	@Override
	public void addProducto(Producto producto){
		productoJpaRepository.save(producto);
	}

	@Override
	public void deleteProducto(int id) {
		productoJpaRepository.deleteById(id);
		
	}

	@Override
	public void updateProducto(Producto producto) {
		productoJpaRepository.save(producto);		
	}

	@Override
	public Producto getProducto(int id) {
		return productoJpaRepository.getOne(id);
	}
	
	

}
