package com.proyecto.empleos.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyecto.empleos.model.Vacante;
import com.proyecto.empleos.service.VacantesService;

@Controller
public class HomeController {
	
	@Autowired
	private VacantesService vacanteService;
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Vacante> lista = vacanteService.buscarTodas();
		//List<Vacante> lista = getVacantes();
		model.addAttribute("vacantes",lista);
		System.out.println("vista tabla");
		return "tabla";
	}
	
	@GetMapping("/detalle")
	public String mostrarDetalle(Model model) {
		Vacante vacante = new Vacante();
		vacante.setNombre("Ingeniero de Comunicaciones");
		vacante.setDescripcion("se solicita ingeniero para dar soporte a intranet");
		vacante.setFecha(new Date());
		vacante.setSalario(9700.0);
		model.addAttribute("vacante", vacante);
		return "detalle";
	}
	
	@GetMapping("/listado")
	public String mostrarListado(Model model) {//model: datos al modelo
		List<String> lista = new LinkedList<String>();
		lista.add("Ingeniero de Sistemas");
		lista.add("Auxiliar de Contabilidad");
		lista.add("Vendedor");
		lista.add("Arquitecto");
		
		model.addAttribute("empleos", lista);
		
		return "listado";
	}
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		List<Vacante> lista = vacanteService.buscarTodas();
		model.addAttribute("vacantes",lista);
		model.addAttribute("fecha",new Date());
		return "home";//ruta: src/main/resources/templates
	}
	
	/*public List<Vacante> getVacantes(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		List<Vacante> lista = new LinkedList<Vacante>();
		try {
			Vacante vacante1 = new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("Ingeniero Civil");
			vacante1.setDescripcion("se solicita ingeniero civil para diseñar puente peatonal");
			vacante1.setFecha(sdf.parse("02-09-2021"));
			vacante1.setSalario(8500.0);
			vacante1.setDestacado(1);
			vacante1.setImagen("empresa1.png");
			
			Vacante vacante2 = new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Contador Publico");
			vacante2.setDescripcion("Empresa importante solicita contador con 5 años de experiencia titulado");
			vacante2.setFecha(sdf.parse("02-09-2021"));
			vacante2.setSalario(12000.0);
			vacante2.setDestacado(0);
			vacante2.setImagen("empresa2.png");
			
			Vacante vacante3 = new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("Ingeniero Electrico");
			vacante3.setDescripcion("Empresa internacional solicita ingeniero mecanico para mantenimiento");
			vacante3.setFecha(sdf.parse("02-09-2021"));
			vacante3.setSalario(10500.0);
			vacante3.setDestacado(0);
			
			Vacante vacante4 = new Vacante();
			vacante4.setId(4);
			vacante4.setNombre("Ingeniero Informatico");
			vacante4.setDescripcion("Se requiere desarrolladores para proyecto a largo plazo");
			vacante4.setFecha(sdf.parse("02-09-2021"));
			vacante4.setSalario(8500.0);
			vacante4.setDestacado(1);
			vacante4.setImagen("empresa4.png");
			
			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);
		}catch(ParseException ex) {
			System.out.print("Error: "+ex.getMessage());
		}
		return lista;
	}*/
}
