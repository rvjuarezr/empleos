package com.proyecto.empleos.service;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.empleos.model.Categoria;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	private List<Categoria> lista = null;
	
	public CategoriaServiceImpl() {
		lista = new LinkedList<Categoria>();
		Categoria categoria1 = new Categoria();
		categoria1.setId(1);
		categoria1.setNombre("Recursos Humanos");
		categoria1.setDescripcion("Trabajos relacionados con el area de RH.");
		
		Categoria categoria2 = new Categoria();
		categoria2.setId(2);
		categoria2.setNombre("Ventas");
		categoria2.setDescripcion("Ofertas de trabajo relacionado con ventas.");
		
		Categoria categoria3 = new Categoria();
		categoria3.setId(3);
		categoria3.setNombre("Arquitectura");
		categoria3.setDescripcion("Diseño de planos en general y trabajos relacionados.");
		
		lista.add(categoria1);
		lista.add(categoria2);
		lista.add(categoria3);
	}
	
	public void guardar(Categoria categoria) {
		lista.add(categoria);
	}
	
	public List<Categoria> buscarTodas(){
		return lista;
	}
	
	public Categoria buscarPorId(Integer idCategoria) {
		for (Categoria cat : lista) {
			if (cat.getId() == idCategoria) {
				return cat;
			}
		}
		return null;
	}
}
