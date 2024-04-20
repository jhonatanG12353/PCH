package co.edu.uco.pch.dto;

import java.util.UUID;

import co.edu.uco.pch.crosscutting.helpers.TextHelper;


public class PaisDTO {
	private UUID id;
	private String nombre;
	
	private PaisDTO()
	{
		super();
	}
	private PaisDTO(final UUID id,final String nombre) {
		setid(id);
		setNombre(nombre);
		
	}
	public static final PaisDTO build () {
		return new PaisDTO();
	} 
	public final PaisDTO setid( final UUID id) {
		this.id = id;
		return this;
	}
	public final PaisDTO setNombre(final String nombre) {
		this.nombre  = TextHelper.applyTrim(nombre);
		return this;
	}
	public final UUID getid() {
		return id;
	}
	public final String getNombre() {
		return nombre;
	}
	
}
