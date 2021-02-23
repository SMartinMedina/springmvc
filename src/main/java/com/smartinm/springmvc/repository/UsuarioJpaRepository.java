package com.smartinm.springmvc.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartinm.springmvc.entity.Usuario;

public interface UsuarioJpaRepository extends JpaRepository<Usuario, Serializable>{

}
