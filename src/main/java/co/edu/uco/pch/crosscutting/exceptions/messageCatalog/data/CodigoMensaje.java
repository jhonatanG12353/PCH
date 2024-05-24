package co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data;

import static co.edu.uco.pch.crosscutting.helpers.TextHelper.concatenate;
import static co.edu.uco.pch.crosscutting.helpers.TextHelper.UNDERLINE;;


public enum CodigoMensaje {
	
	M00001(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00001", true),
	M00002(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00002", true),
	M00003(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00003", true),
	M00004(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00004", true),
	M00005(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00005", true),
	M00006(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00006", true),
	M00007(TipoMensaje.USUARIO, CategoriaMensaje.EXITO, "00007", false),
	M00008(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00008", true),
	M00009(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00009", true),
	M00010(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00010", true),
	M00011(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00011", true),
	M00012(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00012", true),
	M00013(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00013", true),
	M00014(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00014", true),
	M00015(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00015", true),
	M00016(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00016", true),
	M00017(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00017", true),
	M00018(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00018", true),
	M00019(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00019", true),
	M00020(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00020", true),
	M00021(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00021", true),
	M00022(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00022", true),
	M00023(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00023", true),
	M00024(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00024", true),
	M00025(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00025", true),
	M00026(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00026", true),
	M00027(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00027", true),
	M00028(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00028", true),
	M00029(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00029", true),
	M00030(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00030", true),
	M00031(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00031", true),
	M00032(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00032", true),
	M00033(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00033", true),
	M00034(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00034", true),
	M00035(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00035", true),
	M00036(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00036", true),
	M00037(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00037", true),
	M00038(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00038", true),
	M00039(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00039", true),
	M00040(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00040", true),
	M00041(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00041", false),
	M00042(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00042", false),
	M00043(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00043", false),
	M00044(TipoMensaje.USUARIO, CategoriaMensaje.ADVERTENCIA, "00044", false),
	M00045(TipoMensaje.USUARIO, CategoriaMensaje.ADVERTENCIA, "00045", false),
	M00046(TipoMensaje.USUARIO, CategoriaMensaje.ADVERTENCIA, "00046", false),
	M00047(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00047", false),
	M00048(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00048", false),
	M00049(TipoMensaje.USUARIO, CategoriaMensaje.ADVERTENCIA, "00049", false),
	M00050(TipoMensaje.USUARIO, CategoriaMensaje.ADVERTENCIA, "00050", false),
	M00051(TipoMensaje.USUARIO, CategoriaMensaje.ADVERTENCIA, "00051", false),
	M00052(TipoMensaje.USUARIO, CategoriaMensaje.ADVERTENCIA, "00052", false),
	M00053(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00053", false),
	M00054(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00054", false),
	M00055(TipoMensaje.USUARIO, CategoriaMensaje.ADVERTENCIA, "00055", false),
	M00056(TipoMensaje.USUARIO, CategoriaMensaje.ADVERTENCIA, "00056", false),
	M00057(TipoMensaje.USUARIO, CategoriaMensaje.ADVERTENCIA, "00057", false),
	M00058(TipoMensaje.USUARIO, CategoriaMensaje.ADVERTENCIA, "00058", false),
	M00059(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00059", true),
	M00060(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00060", true),
	M00061(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00061", true),
	M00062(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00062", true),
	M00063(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00063", true),
	M00064(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00064", true),
	M00065(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00065", true),
	M00066(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00066", true),
	M00067(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00067", true),
	M00068(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00068", true),
	M00069(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00069", true),
	M00070(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00070", true),
	M00071(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00071", true),
	M00072(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00072", true),
	M00073(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00073", true),
	M00074(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00074", true),
	M00075(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00075", true),
	M00076(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00076", true),
	M00077(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00077", true),
	M00078(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00078", true),
	M00079(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00079", true),
	M00080(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00080", true),
	M00081(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00081", true),
	M00082(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00082", true),
	M00083(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00083", true),
	M00084(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00084", true),
	M00085(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00085", true),
	M00086(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00086", true),
	M00087(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00087", true),
	M00088(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00088", true),
	M00089(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00089", true),
	M00090(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00090", true),
	M00091(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00091", true),
	M00092(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00092", true);
	
	private TipoMensaje tipo;
	private CategoriaMensaje categoria;
	private String codigo;
	private boolean base;
	
	private CodigoMensaje(final TipoMensaje tipo, final  CategoriaMensaje categoria, final  String codigo, final boolean base) {
		setTipo(tipo);
		setCategoria(categoria);
		setCodigo(codigo);
		setBase(base);
	}
	
	
	public final boolean esBase() {
		return base;
	}


	private final void setBase( final boolean base) {
		this.base = base;
	}


	public final TipoMensaje getTipo() {
		return tipo;
	}

	public final CategoriaMensaje getCategoria() {
		return categoria;
	}

	public final String getCodigo() {
		return codigo;
	}

	private final void setTipo( final TipoMensaje tipo) {
		this.tipo = tipo;
	}

	private final void setCategoria(final CategoriaMensaje categoria) {
		this.categoria = categoria;
	}

	private final void setCodigo(final String codigo) {
		this.codigo = codigo;
	}
	
	public String getIdentificador () {		

		return concatenate( getTipo().name() , 
				UNDERLINE , getCategoria().name() , 
				UNDERLINE , getCodigo());
	}
	
}