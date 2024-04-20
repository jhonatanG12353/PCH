package co.edu.uco.pch.crosscutting.helpers;

public class ObjectHelper {
	
	public static final String VACIO="";

	private ObjectHelper() {
		super();
	}
	
	public static final <O> boolean isNull(final O objeto) {
		return objeto == null;
	}
	public static final  <O> boolean isNuloOVacio(final O objeto) {
		return isNull(objeto)|| VACIO.equals(objeto);
	}



	@SuppressWarnings("unchecked")
	public static final  <O> O obtenerValorDefecto(final O objeto, final String valorDefecto) {		
		return isNuloOVacio(objeto) ? (O) valorDefecto: objeto;
		//TO DO: buscar la manera de como solucionar este casteo
	}
	public static final  <O> O obtenerValorDefecto(final O objeto) {		
		return obtenerValorDefecto(objeto , VACIO);
	}

}
