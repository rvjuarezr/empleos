package com.proyecto.empleos.service;

import java.util.List;

import com.proyecto.empleos.model.Categoria;

public interface CategoriaService {
	void guardar(Categoria categoria);
	List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);
}
