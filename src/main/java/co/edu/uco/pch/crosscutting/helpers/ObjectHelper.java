package co.edu.uco.pch.crosscutting.helpers;

import java.util.Objects;

public final class ObjectHelper {
	
	
	private static final ObjectHelper INSTANCE = new ObjectHelper();
	

	private ObjectHelper() {
		super();
	}
	public static final ObjectHelper getObjectHelper() {
		return INSTANCE;
	}
	
	public static final <O> boolean isNull(final O objeto) {
		return Objects.isNull(objeto);
	}

	public final  <O> O obtenerValorDefecto( O objeto , O objeto2) {		
		return isNull(objeto) ?   null  : objeto2;
	}
	public final  <O> O obtenerValorDefecto( O objeto) {		
		return isNull(objeto) ?   null  : objeto;
	}

}
