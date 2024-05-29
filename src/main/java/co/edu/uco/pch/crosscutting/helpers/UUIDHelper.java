package co.edu.uco.pch.crosscutting.helpers;

import java.util.Random;
import java.util.UUID;


public class UUIDHelper {

	
	public static final UUID UUIDDEFECTO = new UUID(0L, 0L);
	private static final String DEFAULT_UUID_STRING = "00000000-0000-0000-0000-000000000000";
	
	private UUIDHelper() {
		super();
	}
	
	public static UUID generarUUIDDefecto() {
        return new UUID(0L, 0L);
    }
	
	public static UUID convertirStringaUUID(String uuidString) {
            return UUID.fromString(uuidString);        
    }
	
	public static UUID generarUUIDAleatorio() {
		Random random = new Random();
        long mas = random.nextLong();
        long menos = random.nextLong();
        return new UUID(mas, menos);
    }
	
	public static final boolean isNull(final UUID uuid) {
		return (uuid == null);
	}
	
	public static final UUID obtenerValorDefecto(final UUID uuid, final UUID valorDefecto) {		
		return isNull(uuid) ? valorDefecto: uuid;
	}
	public static final UUID obtenerValorDefecto(final UUID uuid) {		
		return obtenerValorDefecto(uuid, UUIDDEFECTO);
	}
	public static final UUID getDefault() {
		return convertToUUID(DEFAULT_UUID_STRING);
	}
	
	public static final UUID getDefault(final UUID value, final UUID defaultValue) {
		return ObjectHelper.getObjectHelper().obtenerValorDefecto(value, defaultValue);
	}
	public static final boolean isDefault(final UUID value) {
		return getDefault(value, getDefault()).equals(getDefault());
	}
	public static final UUID convertToUUID(final String uuidAsString) {
		return UUID.fromString(uuidAsString);
	}
}
