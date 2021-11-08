package com.proyecto.empleos.service;

import java.util.List;

import com.proyecto.empleos.model.Vacante;

public interface VacantesService {
	List<Vacante> buscarTodas();
	Vacante buscarVacante(Integer idVacante);
	void guardar(Vacante vacante);
}
