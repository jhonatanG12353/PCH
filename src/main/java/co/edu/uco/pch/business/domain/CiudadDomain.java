package co.edu.uco.pch.business.domain;

import java.util.UUID;

import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.crosscutting.helpers.UUIDHelper;

public final class CiudadDomain {
	
	private UUID id;
	private String nombre;
	private DepartamentoDomain departamento;
	
	protected CiudadDomain(final UUID id, final String nombre, final DepartamentoDomain departamento) {
		setId(id);
		setNombre(nombre);
		setDepartamento(departamento);
		
	}
	public static CiudadDomain build(final UUID id, final String nombre, final DepartamentoDomain departamento) {
		return new CiudadDomain(id,nombre, departamento);
	}
	public static CiudadDomain build(final UUID id) {
		return new CiudadDomain(id,TextHelper.EMPTY, DepartamentoDomain.build() );
	}
	public static CiudadDomain build() {
		return new CiudadDomain(UUIDHelper.generarUUIDDefecto(),TextHelper.EMPTY, DepartamentoDomain.build());
	}
	
	private void setId(final UUID id) {
		this.id = UUIDHelper.obtenerValorDefecto(id);
	}
	private void setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
	}
	private void setDepartamento(final DepartamentoDomain departamento) {
		//this.departamento = ObjectHelper.getObjectHelper().obtenerValorDefecto(departamento,DepartamentoDomain.build());
		this.departamento = departamento;
	}
	public UUID getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public DepartamentoDomain getDepartamento() {
		return departamento;
	}
	
	
}
