package co.edu.uco.pch.business.assembler.dto.impl;

import co.edu.uco.pch.business.assembler.dto.AssemblerDTO;
import co.edu.uco.pch.business.domain.CiudadDomain;
import co.edu.uco.pch.business.domain.DepartamentoDomain;
import co.edu.uco.pch.dto.CiudadDTO;
import co.edu.uco.pch.dto.DepartamentoDTO;

public class CiudadAssemblerDTO implements AssemblerDTO<CiudadDomain, CiudadDTO>{
	
	private static final AssemblerDTO<DepartamentoDomain, DepartamentoDTO> departamentoAssembler =  DepartamentoAssemblerDTO.getinstace();
	private static final AssemblerDTO<CiudadDomain, CiudadDTO> instance = new CiudadAssemblerDTO();
	
	private CiudadAssemblerDTO() {
		super();
	}
	
	public static final AssemblerDTO<CiudadDomain, CiudadDTO> getinstace(){
		return instance;
	}
	
	@Override
	public CiudadDomain toDomain(CiudadDTO data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CiudadDTO toDTO(CiudadDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

}
