package com.proyecto.empleos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.empleos.model.Categoria;
import com.proyecto.empleos.service.CategoriaService;

@Controller
@RequestMapping(value="/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String mostrarIndex(Model model) {
		List<Categoria> lista = categoriaService.buscarTodas();
		model.addAttribute("categorias",lista);
		return "categorias/listCategorias";
	}
	
	@GetMapping(value="/create")
	public String crear(Categoria categoria) {
		return "categorias/formCategorias";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String guardar(@RequestParam("nombre") String nombre,@RequestParam("descripcion") String descripcion) {
		System.out.println("Nombre: "+nombre);
		System.out.println("Descripcion: "+ descripcion);
		return "categorias/listCategorias";
	}
	
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idCategoria, Model model) {
		Categoria categoria = categoriaService.buscarPorId(idCategoria);
		model.addAttribute("categoria",categoria);
		return "categorias/detalleCategorias";
	}
	
	/*@PostMapping(value="/save")
	public String guardar(Categoria categoria, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			for(ObjectError error: result.getAllErrors()) {
				System.out.println("ocurrio un error: "+ error.getDefaultMessage());
			}
			return "categorias/formCategorias";
		}
		//categoriaService.guardar(categoria);
		attributes.addFlashAttribute("msg", "Registro Guardado");
		return "redirect:/categorias/index";
	}*/
}
