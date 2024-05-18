package co.edu.uco.pch.entity;

import java.util.UUID;

import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.crosscutting.helpers.UUIDHelper;

public class DepartamentoEntity {
	private UUID id;
	private String nombre;
	private PaisEntity pais;
	
	
	public DepartamentoEntity() {
		super();
	}
	public DepartamentoEntity(final UUID id, final String nombre, final PaisEntity pais) {
		setid(id);
		setNombre(nombre);
		setPais(pais);
	}
	public static final DepartamentoEntity build () {
		return new DepartamentoEntity();
	} 
	public final DepartamentoEntity setid( final UUID id) {
		this.id = UUIDHelper.obtenerValorDefecto(id);
		return this;
	}
	public final DepartamentoEntity setNombre(final String nombre) {
		this.nombre  = TextHelper.applyTrim(nombre);
		return this;
	}
	public final DepartamentoEntity setPais( final PaisEntity pais) {
		this.pais = ObjectHelper.getObjectHelper().obtenerValorDefecto(pais);
		return this;
	}
	public final UUID getid() {
		return this.id;
	}
	public final String getNombre() {
		return this.nombre;
	}
	public final PaisEntity getPais() {
		return this.pais;
	}
	

	
	
}
