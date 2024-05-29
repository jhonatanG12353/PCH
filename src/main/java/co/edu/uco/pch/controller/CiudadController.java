package co.edu.uco.pch.controller;


import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.pch.business.facade.impl.ciudad.ConsultarCiudadesFacade;
import co.edu.uco.pch.business.facade.impl.ciudad.EliminarCiudadFacade;
import co.edu.uco.pch.business.facade.impl.ciudad.ModificarCiudadFacade;
import co.edu.uco.pch.business.facade.impl.ciudad.RegistrarCiudadFacade;
import co.edu.uco.pch.controller.response.CiudadResponse;
import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.dto.CiudadDTO;

@RestController
@RequestMapping("/api/v1/ciudades")
public final class CiudadController {
	
	@GetMapping ("/dummy")
	public CiudadDTO dummy() {
		return CiudadDTO.build();
	}
	
	@GetMapping
	public ResponseEntity<CiudadResponse> consultar(){
		var httpStatusCode = HttpStatus.ACCEPTED;
		var ciudadResponse = new CiudadResponse();
		try {
			var ciudadDto = CiudadDTO.build();
			var facade = new ConsultarCiudadesFacade();
			
			ciudadResponse.setDatos(facade.execute(ciudadDto));
			ciudadResponse.getMensajes().add("Ciudades consultadas exitosamente");
		}
		catch(final PCHException exception){
			httpStatusCode = HttpStatus.BAD_REQUEST;
			ciudadResponse.getMensajes().add(exception.getMessage());
			exception.printStackTrace();
		}catch(final Exception exception) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			
			var mensajeUsuario= "Se ha presentado un problema intentando consultar una ciudad";
					ciudadResponse.getMensajes().add(mensajeUsuario);
					exception.printStackTrace();
		}
		return new ResponseEntity<>(ciudadResponse, httpStatusCode);
	}
	@PostMapping
	public ResponseEntity<CiudadResponse> crear(@RequestBody CiudadDTO ciudad){
		var httpStatusCode = HttpStatus.ACCEPTED;
		var ciudadResponse = new CiudadResponse();
		
		try {
			
			var facade = new RegistrarCiudadFacade();
			facade.execute(ciudad);
			ciudadResponse.getMensajes().add("Ciudades creada exitosamente");
		}
		catch(final PCHException exception){
			httpStatusCode = HttpStatus.BAD_REQUEST;
			ciudadResponse.getMensajes().add(exception.getMessage());
			exception.printStackTrace();
		}catch(final Exception exception) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			
			var mensajeUsuario= "Se ha presentado un problema intentando registrar una ciudad";
					ciudadResponse.getMensajes().add(mensajeUsuario);
					exception.printStackTrace();
		}
		return new ResponseEntity<>(ciudadResponse, httpStatusCode);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<CiudadResponse> eliminar(@PathVariable UUID id){
		var httpStatusCode = HttpStatus.ACCEPTED;
		var ciudadResponse = new CiudadResponse();
		try {
			
			var facade = new EliminarCiudadFacade();
			var dto = CiudadDTO.build().setid(id);
			facade.execute(dto);
			ciudadResponse.getMensajes().add("Ciudades Eliminada exitosamente");
		}
		catch(final PCHException exception){
			httpStatusCode = HttpStatus.BAD_REQUEST;
			ciudadResponse.getMensajes().add(exception.getMessage());
			exception.printStackTrace();
		}catch(final Exception exception) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			
			var mensajeUsuario= "Se ha presentado un problema intentando Eliminar una ciudad";
					ciudadResponse.getMensajes().add(mensajeUsuario);
					exception.printStackTrace();
		}
		return new ResponseEntity<>(ciudadResponse, httpStatusCode);
	}
	@PutMapping ("/{id}")
	public ResponseEntity<CiudadResponse> modificar(@PathVariable UUID id, @RequestBody CiudadDTO ciudad){
		var httpStatusCode = HttpStatus.ACCEPTED;
		var ciudadResponse = new CiudadResponse();
		
		try {
			ciudad.setid(id);
			var facade = new ModificarCiudadFacade();
			facade.execute(ciudad);
			ciudadResponse.getMensajes().add("Ciudades modificada exitosamente");
		}
		catch(final PCHException exception){
			httpStatusCode = HttpStatus.BAD_REQUEST;
			ciudadResponse.getMensajes().add(exception.getMessage());
			exception.printStackTrace();
		}catch(final Exception exception) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			
			var mensajeUsuario= "Se ha presentado un problema intentando modificar una ciudad";
					ciudadResponse.getMensajes().add(mensajeUsuario);
					exception.printStackTrace();
		}
		return new ResponseEntity<>(ciudadResponse, httpStatusCode);
	}
	
}
