package com.proyecto.empleos.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Utileria {
	
	public static String guardarArchivo(MultipartFile multipart, String ruta) {
		// Obtenemos el nombre original del archivo.
		String nombreOriginal = multipart.getOriginalFilename();
		nombreOriginal = nombreOriginal.replace(" ", "-");
		System.out.println("nombre foto: "+nombreOriginal);
		String nombreFinal = randomAlphaNumeric(8) + nombreOriginal;
		try {
			// Formamos el nombre del archivo para guardarlo en el disco duro.
			File imageFile = new File(ruta + nombreFinal);
			//Guardamos fisicamente el archivo en HD.
			multipart.transferTo(imageFile);
			return nombreFinal;
		}catch(IOException e) {
			return null;
		}
	}
	
	/**
	 * Metodo para generar una cadena aleatoria de longitud N
	 * @param count
	 * @return
	 */
	public static String randomAlphaNumeric(int count) {
		String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * CARACTERES.length());
			builder.append(CARACTERES.charAt(character));
		}
		return builder.toString();
	}
}
