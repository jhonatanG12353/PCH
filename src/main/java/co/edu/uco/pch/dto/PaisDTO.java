package co.edu.uco.pch.dto;

import java.util.UUID;

import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.crosscutting.helpers.UUIDHelper;


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
		this.id = UUIDHelper.obtenerValorDefecto(id);
		return this;
	}
	public final PaisDTO setNombre(final String nombre) {
		this.nombre  = TextHelper.applyTrim(nombre);
		return this;
	}
	public final UUID getid() {
		return this.id;
	}
	public final String getNombre() {
		return this.nombre;
	}
	
}
