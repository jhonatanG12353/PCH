package co.edu.uco.pch.entity;

import java.util.UUID;

import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.crosscutting.helpers.UUIDHelper;

public class CiudadEntity {
	private UUID id;
	private String nombre;
	private DepartamentoEntity departamento;
	
	public CiudadEntity() {
		setid(UUIDHelper.generarUUIDDefecto());
		setNombre(TextHelper.EMPTY);
		setDepartamento(DepartamentoEntity.build());
	}
	
	public  CiudadEntity(final UUID id, final String nombre, final DepartamentoEntity departamento) {
		setid(id);
		setNombre(nombre);
		setDepartamento(departamento);
	}
	public static final CiudadEntity build () {
		return new CiudadEntity();
	} 
	public final CiudadEntity setid(final UUID id) {
		this.id = UUIDHelper.obtenerValorDefecto(id);
		return this;
	}
	public final CiudadEntity setNombre(final String nombre) {
		this.nombre  = TextHelper.applyTrim(nombre);
		return this;
	}
	public final CiudadEntity setDepartamento(final DepartamentoEntity departamento) {
		this.departamento = ObjectHelper.getObjectHelper().obtenerValorDefecto(departamento, new DepartamentoEntity());
		return this;
	}
	public final UUID getid() {
		return id;
	}
	public final String getNombre() {
		return nombre;
	}
	public final DepartamentoEntity getDepartamento() {
		return departamento;
	}
	
}
