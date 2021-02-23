package com.smartinm.springmvc.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartinm.springmvc.entity.Producto;

@Repository
public interface ProductoJpaRepository extends JpaRepository<Producto, Serializable>{
	public abstract List<Producto> findAll();
}
