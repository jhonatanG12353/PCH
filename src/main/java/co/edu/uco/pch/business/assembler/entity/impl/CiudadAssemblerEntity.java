package co.edu.uco.pch.business.assembler.entity.impl;

import static co.edu.uco.pch.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.pch.business.assembler.entity.AssemblerEntity;
import co.edu.uco.pch.business.domain.CiudadDomain;
import co.edu.uco.pch.business.domain.DepartamentoDomain;
import co.edu.uco.pch.entity.CiudadEntity;
import co.edu.uco.pch.entity.DepartamentoEntity;

public class CiudadAssemblerEntity  implements AssemblerEntity<CiudadDomain, CiudadEntity>{
	
	private static final AssemblerEntity<DepartamentoDomain, DepartamentoEntity> departamentoAssembler = DepartamentoAssemblerEntity.getinstace();
	private static final AssemblerEntity<CiudadDomain, CiudadEntity> instance = new CiudadAssemblerEntity();
	
	private CiudadAssemblerEntity() {
		super();
	}
	
	public static final AssemblerEntity<CiudadDomain, CiudadEntity>  getinstace(){
		return instance;
	}

	@Override
	public CiudadDomain toDomain(CiudadEntity data) {
		//var CiudadEntityTmp = getObjectHelper().obtenerValorDefecto(data, CiudadEntity.build());
		var departamentoDomain = departamentoAssembler.toDomain(data.getDepartamento());
		return CiudadDomain.build(data.getid(),data.getNombre() , departamentoDomain);
	}

	@Override
	public CiudadEntity toEntity(CiudadDomain domain) {
		var ciudadDomainTmp = getObjectHelper().obtenerValorDefecto(domain, CiudadDomain.build());
		var departamentoEntity = departamentoAssembler.toEntity(ciudadDomainTmp.getDepartamento());
		return CiudadEntity.build().setid(ciudadDomainTmp.getId()).setNombre(ciudadDomainTmp.getNombre()).setDepartamento(departamentoEntity);
	}

	@Override
	public List<CiudadDomain> toDomainCollection(List<CiudadEntity> entityCollection) {
		List<CiudadDomain> resultados = new ArrayList<>();
		for (int i = 0; i < entityCollection.size(); i++) {
			resultados.add(CiudadAssemblerEntity.getinstace().toDomain(entityCollection.get(i)));
		}		
		return resultados;
	}

}
