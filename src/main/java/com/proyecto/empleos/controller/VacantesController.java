package com.proyecto.empleos.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.empleos.model.Vacante;
import com.proyecto.empleos.service.CategoriaService;
import com.proyecto.empleos.service.VacantesService;
import com.proyecto.empleos.util.Utileria;


@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	@Autowired
	private VacantesService vacanteService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Vacante> lista = vacanteService.buscarTodas();
		model.addAttribute("vacantes",lista);
		return "vacantes/listVacantes";
	}
	
	@GetMapping("/create")
	public String crear(Vacante vacante, Model model) {
		//model.addAttribute("categorias",categoriaService.buscarTodas());
		return "vacantes/formVacante";
	}
	
	@GetMapping("/delete")
	public String eliminar(@RequestParam("id") int idVacante, Model model) {
		System.out.println("borrando vacante con id: "+idVacante);
		model.addAttribute("id",idVacante);
		return "mensaje";
	}
	
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model model) {
		Vacante vacante = vacanteService.buscarVacante(idVacante);
		
		model.addAttribute("vacante",vacante);
		return "detalle";
	}
	
	@PostMapping(value="/save")
	public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes, 
			@RequestParam("archivoImagen") MultipartFile multiPart) {
		if (result.hasErrors()) {
			for(ObjectError error: result.getAllErrors()) {
				System.out.println("ocurrio un error: "+ error.getDefaultMessage());
			}
			return "vacantes/formVacante";
		}
		
		if(!multiPart.isEmpty()) {
			String ruta ="D:/WORKSPACE/IMG-VACANTES/";
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if(nombreImagen != null) {// La imagen si se subio
				vacante.setImagen(nombreImagen);
			}
		}
		vacanteService.guardar(vacante);
		attributes.addFlashAttribute("msg", "Registro Guardado");
		return "redirect:/vacantes/index";
		//return "vacantes/listVacantes";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
