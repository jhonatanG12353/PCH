package co.edu.uco.pch.dto;

import java.util.UUID;

public class CiudadDTO {
	private UUID id;
	private String nombre;
	private DepartamentoDTO departamento;
	
	private CiudadDTO() {
		super();
	}
	
	private CiudadDTO(final UUID id, final String nombre, final DepartamentoDTO departamento) {
		setid(id);
		setNombre(nombre);
		setDepartamento(departamento);
	}
	public static final CiudadDTO build () {
		return new CiudadDTO();
	} 
	public final CiudadDTO setid(final UUID id) {
		this.id = id;
		return this;
	}
	public final CiudadDTO setNombre(final String nombre) {
		this.nombre = nombre;
		return this;
	}
	public final CiudadDTO setDepartamento(final DepartamentoDTO departamento) {
		this.departamento = departamento;
		return this;
	}
	public final UUID getid() {
		return id;
	}
	public final String getNombre() {
		return nombre;
	}
	public final DepartamentoDTO getDepartamento() {
		return departamento;
	}
	
}
