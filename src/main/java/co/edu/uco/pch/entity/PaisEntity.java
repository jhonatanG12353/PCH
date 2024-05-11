package co.edu.uco.pch.entity;

import java.util.UUID;

import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.crosscutting.helpers.UUIDHelper;


public class PaisEntity {
	private UUID id;
	private String nombre;
	
	private PaisEntity()
	{
		super();
	}
	private PaisEntity(final UUID id,final String nombre) {
		setid(id);
		setNombre(nombre);
		
	}
	public static final PaisEntity build () {
		return new PaisEntity();
	} 
	public final PaisEntity setid( final UUID id) {
		this.id = UUIDHelper.obtenerValorDefecto(id);
		return this;
	}
	public final PaisEntity setNombre(final String nombre) {
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
