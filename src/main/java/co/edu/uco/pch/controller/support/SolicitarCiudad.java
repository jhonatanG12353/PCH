package co.edu.uco.pch.controller.support;

import java.util.UUID;

public class SolicitarCiudad {
		private UUID id;
		private String nombre;
		private UUID idCiudad;
		
		public SolicitarCiudad() {
			setId(id);
			setNombre(nombre);
			setIdCiudad(idCiudad);
		}
		public SolicitarCiudad(final UUID id, final String nombre, final UUID idCiudad) {
			setId(id);
			setNombre(nombre);
			setIdCiudad(idCiudad);
		}

		public UUID getId() {
			return id;
		}

		public void setId(UUID id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public UUID getIdCiudad() {
			return idCiudad;
		}

		public void setIdCiudad(UUID idCiudad) {
			this.idCiudad = idCiudad;
		}

		
}
