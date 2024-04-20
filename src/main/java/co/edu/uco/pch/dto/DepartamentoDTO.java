package co.edu.uco.pch.dto;

import java.util.UUID;

public class DepartamentoDTO {
	private UUID id;
	private String nombre;
	private PaisDTO pais;
	
	
	private DepartamentoDTO() {
		super();
	}
	private DepartamentoDTO(final UUID id, final String nombre, final PaisDTO pais) {
		setid(id);
		setNombre(nombre);
		setPais(pais);
	}
	public static final DepartamentoDTO build () {
		return new DepartamentoDTO();
	} 
	public final DepartamentoDTO setid( final UUID id) {
		this.id = id;
		return this;
	}
	public final DepartamentoDTO setNombre(final String nombre) {
		this.nombre = nombre;
		return this;
	}
	public final DepartamentoDTO setPais( final PaisDTO pais) {
		this.pais = pais;
		return this;
	}
	public final UUID getid() {
		return id;
	}
	public final String getNombre() {
		return nombre;
	}
	public final PaisDTO getPais() {
		return pais;
	}
	

	
	
}
