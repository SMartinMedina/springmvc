package com.smartinm.springmvc.service;

import java.util.List;

import com.smartinm.springmvc.entity.Categoria;

public interface CategoriaService {
	public abstract Categoria getCategoria(int id);
	public abstract List<Categoria> listAllCategorias();
	public abstract void addCategoria(Categoria categoria);
	public abstract void deleteCategoria(int id);
	public abstract void updateCategoria(Categoria categoria);
}
