package co.edu.uco.pch.crosscutting.helpers;

public final class ObjectHelper {
	
	public static final Object VACIO="";
	private static final ObjectHelper INSTANCE = new ObjectHelper();

	private ObjectHelper() {
		super();
	}
	public static final ObjectHelper getObjectHelper() {
		return INSTANCE;
	}
	
	public static final <O> boolean isNull(final O objeto) {
		return objeto == null;
	}
	
	public static final  <O> boolean isNuloOVacio(final O objeto) {
		return isNull(objeto)|| VACIO.equals(objeto);
	}


	@SuppressWarnings("unchecked")
	public final  <O> O obtenerValorDefecto(final O objeto) {		
		return isNuloOVacio(objeto) ? (O) VACIO : objeto;
	}
	

}
