package co.edu.uco.pch.business.assembler.dto.impl;

import static co.edu.uco.pch.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.List;

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
		//var DepartamentoDtoTmp = getObjectHelper().obtenerValorDefecto(data, DepartamentoDTO.build());
		var datosPais = PaisDTO.build().setid(data.getPais().getid()).setNombre(data.getPais().getNombre());
		var paisDomain = PaisAssemblerDTO.getinstace().toDomain(datosPais);
		return DepartamentoDomain.build(data.getid(),data.getNombre() , paisDomain);
	}

	@Override
	public DepartamentoDTO toDTO(DepartamentoDomain domain) {
		//var DepartamentoDomainTmp = getObjectHelper().obtenerValorDefecto(domain, DepartamentoDomain.build());
		var paisDTO = paisAssembler.toDTO(domain.getPais());
		return DepartamentoDTO.build().setid(domain.getId()).setNombre(domain.getNombre()).setPais(paisDTO);
	}

	@Override
	public List<DepartamentoDomain> toDomainCollection(List<DepartamentoDTO> entityCollection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DepartamentoDTO> toDTOCollection(List<DepartamentoDomain> domain) {
		// TODO Auto-generated method stub
		return null;
	}


}
