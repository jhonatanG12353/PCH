package co.edu.uco.pch.business.assembler.dto.impl;

import co.edu.uco.pch.business.assembler.dto.AssemblerDTO;
import co.edu.uco.pch.business.domain.DepartamentoDomain;
import co.edu.uco.pch.business.domain.PaisDomain;
import co.edu.uco.pch.dto.DepartamentoDTO;
import co.edu.uco.pch.dto.PaisDTO;

public class DepartamentoAssemblerDTO implements AssemblerDTO<DepartamentoDomain, DepartamentoDTO>{

	private static final AssemblerDTO<PaisDomain, PaisDTO> paisAssembler =  PaisAssemblerDTO.getinstace();
	private static final AssemblerDTO<DepartamentoDomain, DepartamentoDTO> instance = new DepartamentoAssemblerDTO();
	
	private DepartamentoAssemblerDTO() {
		super();
	}
	
	public static final AssemblerDTO<DepartamentoDomain, DepartamentoDTO> getinstace(){
		return instance;
	}
	@Override
	public DepartamentoDomain toDomain(DepartamentoDTO data) {
		
		//return DepartamentoDomain.build(data.getid(),data.getNombre() , paisAssembler);
		return null;
	}

	@Override
	public DepartamentoDTO toDTO(DepartamentoDomain domain) {
		
		return DepartamentoDTO.build().setid(domain.getId()).setNombre(domain.getNombre());
	}


}
