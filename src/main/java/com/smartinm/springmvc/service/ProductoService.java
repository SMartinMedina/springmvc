package com.smartinm.springmvc.service;

import java.util.List;

import com.smartinm.springmvc.entity.Producto;

public interface ProductoService {

	public abstract Producto getProducto(int id);
	public abstract List<Producto> listAllProductos();
	public abstract void addProducto(Producto producto);
	public abstract void deleteProducto(int id);
	public abstract void updateProducto(Producto producto);
	
}
