package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controlador.Controlador;
import gui.CrearActividad;

public class Listener implements ActionListener {

	private CrearActividad	crearActividad;
	private Controlador		control	= new Controlador ( );
	Integer					idTipoActividad;
	Integer					idNivel;
	Integer					nivel;
	Integer					idAct;
	Integer					idActividad;
	Integer					idActPpal;
	String					nombreActividad;
	String					undMedida;
	Double					valorEsperado;
	Double					valorAlcanzado;
	String					nombreImg;
	String					tipoidAct;
	String					tipoActividad;
	Object [ ]				primeraFila;
	JLabel					rutaImg;

	public Listener ( CrearActividad c )
	{
		this.crearActividad = c;
	}

	@Override
	public void actionPerformed ( ActionEvent e )
	{
		String listen = e.getActionCommand ( );

		if ( listen.equals ( "btnCrearactividad" ) )
		{

			idTipoActividad = crearActividad.getCbxTipo ( ).getSelectedIndex ( );
			crearActividad.getCbxTipo ( ).setSelectedIndex ( control.validarValores ( idTipoActividad ) );
			idTipoActividad = crearActividad.getCbxTipo ( ).getSelectedIndex ( );

			if ( idTipoActividad == 1 )
			{
				guardarActividadPrincipal ( );
			}
			else
			{
				guardarActividadSecundaria ( );
			}
			listen = "";

		}
		else if ( listen.equals ( "cbxTipo" ) )
		{
			idTipoActividad = crearActividad.getCbxTipo ( ).getSelectedIndex ( );
			Object [ ] [ ] datosTabla = new Object [ 1 ] [ crearActividad.tablaGeneral.getColumnCount ( ) ];
			if ( idTipoActividad == 2 )
			{
				crearActividad.scrollPanelGeneral.setVisible ( false );
				crearActividad.scrollPanelPrincipal.setVisible ( true );
				crearActividad.scrollPanelDetalle.setVisible ( true );

				crearActividad.lblNivelActividad.setBounds ( 60, 212, 108, 17 );
				crearActividad.cbxNivel.setBounds ( 202, 212, 164, 20 );

				crearActividad.lblNombreactividad.setBounds ( 60, 250, 129, 17 );
				crearActividad.txtNombreactividad.setBounds ( 204, 250, 268, 20 );

				crearActividad.lblIdActPrincipal.setVisible ( true );
				crearActividad.cbxIdAct.setVisible ( true );
				crearActividad.cbxIdAct.setModel ( new DefaultComboBoxModel ( crearActividad.iniciarComponente ( ) ) );
				llenarTablaPrincipal ( );
				listen = "";
			}
			else if ( idTipoActividad == 1 )
			{
				crearActividad.scrollPanelGeneral.setVisible ( true );
				crearActividad.scrollPanelPrincipal.setVisible ( false );
				crearActividad.scrollPanelDetalle.setVisible ( false );

				crearActividad.lblNivelActividad.setBounds ( 60, 191, 108, 17 );
				crearActividad.cbxNivel.setBounds ( 202, 191, 164, 20 );

				crearActividad.lblNombreactividad.setBounds ( 60, 238, 129, 17 );
				crearActividad.txtNombreactividad.setBounds ( 204, 238, 268, 20 );

				crearActividad.lblIdActPrincipal.setVisible ( false );
				crearActividad.cbxIdAct.setVisible ( false );
				listen = "";
			}

		}
		else if ( listen.equals ( "cbxIdAct" ) )
		{
			idActPpal = crearActividad.cbxIdAct.getSelectedIndex ( );

			if ( crearActividad.modeloTablaPrincipal.getRowCount ( ) > 0 )
			{
				for ( int i = 0; i < crearActividad.modeloTablaPrincipal.getRowCount ( ); i++ )
				{
					crearActividad.modeloTablaPrincipal.removeRow ( i );
				}
			}

			llenarTablaPrincipal ( );
			listen = "";
		}
	}

	private void llenarTablaPrincipal ( )
	{
		if ( crearActividad.modeloTablaPrincipal.getRowCount ( ) > 0 )
		{
			for ( int i = 0; i < crearActividad.modeloTablaPrincipal.getRowCount ( ); i++ )
			{
				crearActividad.modeloTablaPrincipal.removeRow ( i );
			}
		}
		// idAct = crearActividad.getCbxIdAct ( ).getSelectedIndex ( );
		// crearActividad.getCbxIdAct ( ).setSelectedIndex ( idAct );
		idAct = crearActividad.getCbxIdAct ( ).getSelectedIndex ( );
		tipoidAct = crearActividad.getCbxIdAct ( ).getSelectedItem ( ).toString ( );

		int fila = crearActividad.modeloTabla.getRowCount ( );
		int columna = crearActividad.modeloTabla.getColumnCount ( );

		primeraFila = new Object [ columna ];
		for ( int i = 0; i < fila; i++ )
		{
			if ( idAct == i )
			{
				for ( int j = 0; j < columna; j++ )
				{
					primeraFila [ j ] = crearActividad.modeloTabla.getValueAt ( i, j ).toString ( );
				}
				break;
			}
		}

		for ( int i = 0; i < primeraFila.length; i++ )
		{
			if ( i == 0 )
			{
				idActividad = Integer.parseInt ( primeraFila [ i ].toString ( ) );
			}
			else if ( i == 1 )
			{
				tipoActividad = ( String ) primeraFila [ i ];
			}
			else if ( i == 2 )
			{
				nivel = Integer.parseInt ( primeraFila [ i ].toString ( ) );
			}
			else if ( i == 3 )
			{
				nombreActividad = ( String ) primeraFila [ i ];
			}
			else if ( i == 4 )
			{
				undMedida = ( String ) primeraFila [ i ];
			}
			else if ( i == 5 )
			{
				valorEsperado = Double.parseDouble ( primeraFila [ i ].toString ( ) );
			}
			else if ( i == 6 )
			{
				valorAlcanzado = Double.parseDouble ( primeraFila [ i ].toString ( ) );
			}
			else if ( i == 7 )
			{

				nombreImg = "/img/semRojo.png";
			}
		}
		crearActividad.modeloTablaPrincipal.addRow ( new Object [ ] { idActividad, tipoActividad, nivel, nombreActividad, undMedida, valorEsperado,
				valorAlcanzado, new JLabel ( new ImageIcon ( getClass ( ).getResource ( nombreImg ) ) ) } );
	}

	private void guardarActividadSecundaria ( )
	{

		idActividad = crearActividad.tablaDetalleSecundaria.getRowCount ( ) + 1;

		// Id Actividad Principal

		// Id Actividad
		idTipoActividad = crearActividad.getCbxTipo ( ).getSelectedIndex ( );
		crearActividad.getCbxTipo ( ).setSelectedIndex ( control.validarValores ( idTipoActividad ) );
		idTipoActividad = crearActividad.getCbxTipo ( ).getSelectedIndex ( );
		tipoActividad = crearActividad.getCbxTipo ( ).getSelectedItem ( ).toString ( );

		// Id Nivel
		idNivel = crearActividad.getCbxTipo ( ).getSelectedIndex ( );
		crearActividad.getCbxNivel ( ).setSelectedIndex ( control.validarValores ( idNivel ) );
		idTipoActividad = crearActividad.getCbxTipo ( ).getSelectedIndex ( );
		nivel = Integer.parseInt ( crearActividad.getCbxNivel ( ).getSelectedItem ( ).toString ( ) );

		// Nombre Actividad
		nombreActividad = crearActividad.getTxtNombreactividad ( ).getText ( );

		// Unidad Medida
		undMedida = crearActividad.getTxtUnidadmedida ( ).getText ( );

		// Valor Esperado
		valorEsperado = Double.parseDouble ( control.validarValores ( crearActividad.getTxtValoresperado ( ).getText ( ) ) );

		// Valor Alcanzado
		valorAlcanzado = Double.parseDouble ( control.validarValores ( "" ) );

		// Semaforo
		nombreImg = "/img/semRojo.png";

		// Guardar Datos
		// crearActividad.modeloTabla.addRow ( new Object [ ] { idActividad,
		// tipoActividad, nivel, nombreActividad, undMedida, valorEsperado,
		// null,
		// new JLabel ( new ImageIcon ( getClass ( ).getResource ( nombreImg ) )
		// ) } );
		crearActividad.modeloTablaSecundaria.addRow ( new Object [ ] { idActPpal, idActividad, tipoActividad, nivel, nombreActividad, undMedida,
				valorEsperado, valorAlcanzado, new JLabel ( new ImageIcon ( getClass ( ).getResource ( nombreImg ) ) ) } );

		// Reiniciar Campos Formulario
		resetCampos ( idTipoActividad );
	}

	private void guardarActividadPrincipal ( )
	{
		idActividad = crearActividad.tablaGeneral.getRowCount ( ) + 1;

		// Id Actividad
		idTipoActividad = crearActividad.getCbxTipo ( ).getSelectedIndex ( );
		crearActividad.getCbxTipo ( ).setSelectedIndex ( control.validarValores ( idTipoActividad ) );
		idTipoActividad = crearActividad.getCbxTipo ( ).getSelectedIndex ( );
		tipoActividad = crearActividad.getCbxTipo ( ).getSelectedItem ( ).toString ( );

		// Id Nivel
		idNivel = crearActividad.getCbxTipo ( ).getSelectedIndex ( );
		crearActividad.getCbxNivel ( ).setSelectedIndex ( control.validarValores ( idNivel ) );
		idTipoActividad = crearActividad.getCbxTipo ( ).getSelectedIndex ( );
		nivel = Integer.parseInt ( crearActividad.getCbxNivel ( ).getSelectedItem ( ).toString ( ) );

		// Nombre Actividad
		nombreActividad = crearActividad.getTxtNombreactividad ( ).getText ( );

		// Unidad Medida
		undMedida = crearActividad.getTxtUnidadmedida ( ).getText ( );

		// Valor Esperado
		valorEsperado = Double.parseDouble ( control.validarValores ( crearActividad.getTxtValoresperado ( ).getText ( ) ) );

		// Valor Alcanzado
		valorAlcanzado = Double.parseDouble ( control.validarValores ( "" ) );

		// Semaforo
		nombreImg = "/img/semRojo.png";

		// Guardar Datos
		// crearActividad.modeloTabla.addRow ( new Object [ ] { idActividad,
		// tipoActividad, nivel, nombreActividad, undMedida, valorEsperado,
		// null,
		// new JLabel ( new ImageIcon ( getClass ( ).getResource ( nombreImg ) )
		// ) } );
		crearActividad.modeloTabla.addRow ( new Object [ ] { idActividad, tipoActividad, nivel, nombreActividad, undMedida, valorEsperado,
				valorAlcanzado, new JLabel ( new ImageIcon ( getClass ( ).getResource ( nombreImg ) ) ) } );

		// Reiniciar Campos Formulario
		resetCampos ( idTipoActividad );

	}

	private void resetCampos ( int idTipoActividad )
	{
		if ( idTipoActividad == 1 )
		{
			crearActividad.txtNombreactividad.setText ( "" );
			crearActividad.txtValoresperado.setText ( "" );
			crearActividad.cbxNivel.setSelectedIndex ( 0 );
			crearActividad.cbxTipo.setSelectedIndex ( 0 );
		}
		else
		{
			crearActividad.txtNombreactividad.setText ( "" );
			crearActividad.txtValoresperado.setText ( "" );
			crearActividad.cbxNivel.setSelectedIndex ( 0 );
			crearActividad.cbxTipo.setSelectedIndex ( 0 );
			crearActividad.cbxIdAct.setSelectedIndex ( 0 );
		}

	}

}
