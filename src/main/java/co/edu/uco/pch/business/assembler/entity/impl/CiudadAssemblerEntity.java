package co.edu.uco.pch.business.assembler.entity.impl;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CiudadEntity toEntity(CiudadDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

}
