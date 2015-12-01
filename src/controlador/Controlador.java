
package controlador;

import gui.CrearActividad;

public class Controlador {
	CrearActividad crearActividad;

	public Controlador ( )
	{
	}

	public int validarValores ( Integer id )
	{
		if ( id == 0 )
		{
			return 1;
		}
		else
		{
			return id;
		}

	}

	public String validarValores ( String valor )
	{
		if ( valor.equals ( "" ) )
		{
			return "0";
		}
		else
		{
			return valor;
		}

	}

}
