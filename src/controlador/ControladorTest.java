package controlador;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ControladorTest {

	private Controlador controladorPrueba;

	public ControladorTest ( )
	{
		// TODO Auto-generated constructor stub
		controladorPrueba = new Controlador ( );

	}

	@Test
	public void ValidarValoresTest ( )
	{

		assertEquals ( 1, controladorPrueba.validarValores ( "t" ) );
		assertEquals ( 2, controladorPrueba.validarValores ( 2 ) );

	}

	@Test
	public void ValidadarValoresTest ( )
	{
		assertEquals ( "", "0", controladorPrueba.validarValores ( "" ) );
		assertEquals ( "valor", "valor", controladorPrueba.validarValores ( "valor" ) );

	}

}
