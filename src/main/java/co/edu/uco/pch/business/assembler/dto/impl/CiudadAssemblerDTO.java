package co.edu.uco.pch.business.assembler.dto.impl;



import java.util.ArrayList;
import java.util.List;

import co.edu.uco.pch.business.assembler.dto.AssemblerDTO;
import co.edu.uco.pch.business.domain.CiudadDomain;
import co.edu.uco.pch.business.domain.DepartamentoDomain;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
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
		//var CiudadDtoTmp = getObjectHelper().obtenerValorDefecto(data, CiudadDTO.build());
		//var departamentoDomain = departamentoAssembler.toDomain(data.getDepartamento());
		var datodepartamento = DepartamentoDTO.build().setid(data.getDepartamento().getid()).setNombre(data.getDepartamento().getNombre()).setPais(data.getDepartamento().getPais()) ;
		var departamentoDomain = DepartamentoAssemblerDTO.getinstace().toDomain(datodepartamento);
		return CiudadDomain.build(data.getid(),data.getNombre() , departamentoDomain);
	}

	@Override
	public  CiudadDTO toDTO(CiudadDomain domain) {
		var departamentoDTO = departamentoAssembler.toDTO(domain.getDepartamento());
		return CiudadDTO.build().setid(domain.getId()).setNombre(domain.getNombre()).setDepartamento(departamentoDTO);
	}

	@Override
	public List<CiudadDomain> toDomainCollection(List<CiudadDTO> dtoCollection) {
		var dtoCollectionTmp = ObjectHelper.getObjectHelper().obtenerValorDefecto(dtoCollection, new ArrayList<CiudadDTO>());		
		return dtoCollectionTmp.stream().map(this::toDomain).toList();
	}

	@Override
	public List<CiudadDTO> toDTOCollection(List<CiudadDomain> domainCollection) {
		List<CiudadDTO> resultados = new ArrayList<>();
		for (int i = 0; i < domainCollection.size(); i++) {
			resultados.add(CiudadAssemblerDTO.getinstace().toDTO(domainCollection.get(i)));
		}		
		return resultados;
	}
	


}
