package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import listener.Listener;

public class CrearActividad extends JFrame {

	private static final long	serialVersionUID		= 1L;
	// Paneles
	private JPanel				contentPane;
	public JScrollPane			scrollPanelGeneral;
	public JScrollPane			scrollPanelPrincipal;
	public JScrollPane			scrollPanelDetalle;

	// Tablas
	public JTable				tablaGeneral;
	public JTable				tablaDetallePrincipal;
	public JTable				tablaDetalleSecundaria;

	// Encabezados Tablas
	public String [ ]			encabezadoGeneral		= { "Id Act", "Tipo Actividad", "Nivel", "Nombre Actividad", "Und Medida", "Valor Esperado",
			"Valor Alcanzado", "Semáforo" };
	public String [ ]			encabezadoSecundario	= { "Id Ppal", "Id Act", "Tipo Actividad", "Nivel", "Nombre Actividad", "Und Medida",
			"Valor Esperado", "Valor Alcanzado", "Semáforo" };

	String [ ] [ ]				datosTabla				= null;

	// modelo Tabla
	public DefaultTableModel	modeloTabla				= new DefaultTableModel ( datosTabla, encabezadoGeneral );
	public DefaultTableModel	modeloTablaPrincipal	= new DefaultTableModel ( datosTabla, encabezadoGeneral );
	public DefaultTableModel	modeloTablaSecundaria	= new DefaultTableModel ( datosTabla, encabezadoSecundario );

	// TextField
	public JTextField			txtNombreactividad;
	public JTextField			txtUnidadmedida;
	public JTextField			txtValoresperado;
	private Listener			listener				= new Listener ( this );

	// Labels
	private JLabel				lblTipoDeActividad;
	public JLabel				lblNivelActividad;
	public JLabel				lblIdActPrincipal;
	public JLabel				lblNombreactividad;
	private JLabel				lblUnidadMedida;
	private JLabel				lblValorEsperado;
	private JLabel				lblFondoactividad;

	// Button
	private JButton				btnCrearactividad;

	// ComboBox
	public JComboBox			cbxTipo;
	public JComboBox			cbxIdAct;
	public JComboBox			cbxNivel;

	/**
	 * Launch the application.
	 */
	public static void main ( String [ ] args )
	{
		EventQueue.invokeLater ( new Runnable ( ) {
			public void run ( )
			{
				try
				{
					CrearActividad frame = new CrearActividad ( );
					frame.setVisible ( true );
				}
				catch ( Exception e )
				{
					e.printStackTrace ( );
				}
			}
		} );
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings ( "unchecked" )
	public CrearActividad ( )
	{
		setTitle ( "Crear Actividad" );
		setResizable ( false );
		setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		setBounds ( 100, 100, 800, 524 );
		contentPane = new JPanel ( );
		contentPane.setBackground ( Color.WHITE );
		contentPane.setBorder ( new EmptyBorder ( 5, 5, 5, 5 ) );
		setContentPane ( contentPane );

		this.scrollPanelGeneral = new JScrollPane ( );
		this.scrollPanelGeneral.setBounds ( 15, 295, 758, 113 );
		this.scrollPanelGeneral.setFont ( new Font ( "Verdana", Font.PLAIN, 12 ) );

		this.scrollPanelPrincipal = new JScrollPane ( );
		this.scrollPanelPrincipal.setVisible ( false );
		this.scrollPanelPrincipal.setBounds ( 15, 295, 758, 54 );
		this.scrollPanelPrincipal.setFont ( new Font ( "Verdana", Font.PLAIN, 12 ) );

		this.scrollPanelDetalle = new JScrollPane ( );
		this.scrollPanelDetalle.setVisible ( false );
		this.scrollPanelDetalle.setBounds ( 15, 360, 758, 113 );
		this.scrollPanelDetalle.setFont ( new Font ( "Verdana", Font.PLAIN, 12 ) );

		this.lblTipoDeActividad = new JLabel ( "Tipo de Actividad" );
		this.lblTipoDeActividad.setForeground ( Color.WHITE );
		this.lblTipoDeActividad.setBounds ( 60, 143, 120, 17 );
		this.lblTipoDeActividad.setFont ( new Font ( "Franklin Gothic Medium", Font.ITALIC, 15 ) );

		this.lblIdActPrincipal = new JLabel ( "Id Act Principal" );
		this.lblIdActPrincipal.setForeground ( Color.WHITE );
		this.lblIdActPrincipal.setBounds ( 60, 176, 120, 17 );
		this.lblIdActPrincipal.setFont ( new Font ( "Franklin Gothic Medium", Font.ITALIC, 15 ) );
		this.lblIdActPrincipal.setVisible ( false );

		this.lblNivelActividad = new JLabel ( "Nivel Actividad" );
		this.lblNivelActividad.setForeground ( Color.WHITE );
		this.lblNivelActividad.setBounds ( 60, 191, 108, 17 );
		this.lblNivelActividad.setFont ( new Font ( "Franklin Gothic Medium", Font.ITALIC, 15 ) );

		this.lblNombreactividad = new JLabel ( "Nombre Actividad" );
		this.lblNombreactividad.setForeground ( Color.WHITE );
		this.lblNombreactividad.setBounds ( 60, 238, 129, 17 );
		this.lblNombreactividad.setFont ( new Font ( "Franklin Gothic Medium", Font.ITALIC, 15 ) );

		this.lblUnidadMedida = new JLabel ( "Unidad Medida" );
		this.lblUnidadMedida.setForeground ( Color.WHITE );
		this.lblUnidadMedida.setBounds ( 525, 134, 106, 17 );
		this.lblUnidadMedida.setFont ( new Font ( "Franklin Gothic Medium", Font.ITALIC, 15 ) );

		this.lblValorEsperado = new JLabel ( "Valor Esperado" );
		this.lblValorEsperado.setForeground ( Color.WHITE );
		this.lblValorEsperado.setBounds ( 525, 191, 110, 17 );
		this.lblValorEsperado.setFont ( new Font ( "Franklin Gothic Medium", Font.ITALIC, 15 ) );

		// Tabla General
		this.tablaGeneral = new JTable ( );
		this.tablaGeneral.setBackground ( Color.WHITE );
		this.tablaGeneral.setForeground ( Color.DARK_GRAY );
		this.tablaGeneral.setFont ( new Font ( "Verdana", Font.PLAIN, 12 ) );
		this.tablaGeneral.setCellSelectionEnabled ( true );
		this.tablaGeneral.setColumnSelectionAllowed ( true );
		this.tablaGeneral.setSurrendersFocusOnKeystroke ( true );
		inicializarTabla ( this.tablaGeneral, modeloTabla );
		this.scrollPanelGeneral.setViewportView ( this.tablaGeneral );

		// Tabla General
		this.tablaDetallePrincipal = new JTable ( );
		this.tablaDetallePrincipal.setBackground ( Color.WHITE );
		this.tablaDetallePrincipal.setForeground ( Color.DARK_GRAY );
		this.tablaDetallePrincipal.setFont ( new Font ( "Verdana", Font.PLAIN, 12 ) );
		this.tablaDetallePrincipal.setCellSelectionEnabled ( true );
		this.tablaDetallePrincipal.setColumnSelectionAllowed ( true );
		this.tablaDetallePrincipal.setSurrendersFocusOnKeystroke ( true );
		inicializarTabla ( this.tablaDetallePrincipal, modeloTablaPrincipal );
		this.scrollPanelPrincipal.setViewportView ( this.tablaDetallePrincipal );

		// Tabla Detalle
		this.tablaDetalleSecundaria = new JTable ( );
		this.tablaDetalleSecundaria.setVisible ( false );
		this.tablaDetalleSecundaria.setBackground ( Color.WHITE );
		this.tablaDetalleSecundaria.setForeground ( Color.DARK_GRAY );
		this.tablaDetalleSecundaria.setFont ( new Font ( "Verdana", Font.PLAIN, 12 ) );
		this.tablaDetalleSecundaria.setCellSelectionEnabled ( true );
		this.tablaDetalleSecundaria.setColumnSelectionAllowed ( true );
		this.tablaDetalleSecundaria.setSurrendersFocusOnKeystroke ( true );
		inicializarTabla ( this.tablaDetalleSecundaria, modeloTablaSecundaria );
		this.scrollPanelDetalle.setViewportView ( this.tablaDetalleSecundaria );

		contentPane.setLayout ( null );
		contentPane.add ( this.lblTipoDeActividad );
		contentPane.add ( this.lblIdActPrincipal );
		contentPane.add ( this.lblNivelActividad );
		contentPane.add ( this.lblNombreactividad );
		contentPane.add ( this.lblUnidadMedida );
		contentPane.add ( this.lblValorEsperado );

		this.cbxTipo = new JComboBox ( );
		this.cbxTipo.setCursor ( Cursor.getPredefinedCursor ( Cursor.HAND_CURSOR ) );
		this.cbxTipo.setBackground ( new Color ( 0, 204, 255 ) );
		this.cbxTipo.setForeground ( Color.WHITE );
		this.cbxTipo.setFont ( new Font ( "Verdana", Font.PLAIN, 12 ) );
		this.cbxTipo.setModel ( new DefaultComboBoxModel ( new String [ ] { "Seleccione un Tipo", "Principal", "Secundario" } ) );
		this.cbxTipo.setBounds ( 202, 143, 164, 20 );
		this.cbxTipo.setActionCommand ( "cbxTipo" );
		this.cbxTipo.addActionListener ( this.listener );
		contentPane.add ( this.cbxTipo );

		this.cbxIdAct = new JComboBox ( );
		this.cbxIdAct.setLightWeightPopupEnabled ( false );
		this.cbxIdAct.setBackground ( new Color ( 0, 204, 255 ) );
		this.cbxIdAct.setModel ( new DefaultComboBoxModel ( iniciarComponente ( ) ) );
		this.cbxIdAct.setCursor ( Cursor.getPredefinedCursor ( Cursor.HAND_CURSOR ) );
		this.cbxIdAct.setForeground ( Color.WHITE );
		this.cbxIdAct.setFont ( new Font ( "Verdana", Font.PLAIN, 12 ) );
		this.cbxIdAct.setBounds ( 202, 176, 164, 20 );
		this.cbxIdAct.setActionCommand ( "cbxIdAct" );
		this.cbxIdAct.addActionListener ( this.listener );
		this.cbxIdAct.setVisible ( false );
		contentPane.add ( this.cbxIdAct );

		this.cbxNivel = new JComboBox ( );
		this.cbxNivel.setLightWeightPopupEnabled ( false );
		this.cbxNivel.setBackground ( new Color ( 0, 204, 255 ) );
		this.cbxNivel.setModel ( new DefaultComboBoxModel ( new String [ ] { "Seleccione Nivel", "0", "1", "2", "3", "4", "5" } ) );
		this.cbxNivel.setCursor ( Cursor.getPredefinedCursor ( Cursor.HAND_CURSOR ) );
		this.cbxNivel.setForeground ( Color.WHITE );
		this.cbxNivel.setFont ( new Font ( "Verdana", Font.PLAIN, 12 ) );
		this.cbxNivel.setBounds ( 202, 191, 164, 20 );
		this.cbxNivel.setActionCommand ( "cbxNivel" );
		this.cbxNivel.addActionListener ( this.listener );
		contentPane.add ( this.cbxNivel );

		this.txtNombreactividad = new JTextField ( );
		this.txtNombreactividad.setFont ( new Font ( "Verdana", Font.PLAIN, 12 ) );
		this.txtNombreactividad.setBounds ( 204, 238, 268, 20 );
		this.txtNombreactividad.setActionCommand ( "txtNombreactividad" );
		this.txtNombreactividad.addActionListener ( this.listener );
		contentPane.add ( this.txtNombreactividad );
		this.txtNombreactividad.setColumns ( 10 );

		this.txtValoresperado = new JTextField ( );
		this.txtValoresperado.setBounds ( 525, 212, 164, 20 );
		this.txtValoresperado.setActionCommand ( "txtValoresperado" );
		this.txtValoresperado.addActionListener ( this.listener );
		contentPane.add ( this.txtValoresperado );
		this.txtValoresperado.setColumns ( 10 );

		this.txtUnidadmedida = new JTextField ( );
		this.txtUnidadmedida.setEnabled ( false );
		this.txtUnidadmedida.setEditable ( false );
		this.txtUnidadmedida.setText ( "Porcentaje" );
		this.txtUnidadmedida.setFont ( new Font ( "Verdana", Font.PLAIN, 12 ) );
		this.txtUnidadmedida.setBounds ( 525, 162, 164, 20 );
		this.txtUnidadmedida.setActionCommand ( "txtUnidadmedida" );
		this.txtUnidadmedida.addActionListener ( this.listener );
		contentPane.add ( this.txtUnidadmedida );
		this.txtUnidadmedida.setColumns ( 10 );

		this.btnCrearactividad = new JButton ( "" );
		this.btnCrearactividad.setRolloverIcon ( new ImageIcon ( CrearActividad.class.getResource ( "/img/crearAHover.png" ) ) );
		this.btnCrearactividad.setFocusPainted ( false );
		this.btnCrearactividad.setCursor ( Cursor.getPredefinedCursor ( Cursor.HAND_CURSOR ) );
		this.btnCrearactividad.setHorizontalTextPosition ( SwingConstants.CENTER );
		this.btnCrearactividad.addActionListener ( this.listener );
		this.btnCrearactividad.setActionCommand ( "btnCrearactividad" );

		this.btnCrearactividad.setContentAreaFilled ( false );
		this.btnCrearactividad.setBorderPainted ( false );
		this.btnCrearactividad.setIcon ( new ImageIcon ( CrearActividad.class.getResource ( "/img/crearA.png" ) ) );
		this.btnCrearactividad.setBounds ( 525, 244, 179, 40 );
		contentPane.add ( this.btnCrearactividad );
		contentPane.add ( this.scrollPanelGeneral );
		contentPane.add ( this.scrollPanelPrincipal );
		contentPane.add ( this.scrollPanelDetalle );

		this.lblFondoactividad = new JLabel ( "" );
		this.lblFondoactividad.setHorizontalAlignment ( SwingConstants.CENTER );
		this.lblFondoactividad.setIcon ( new ImageIcon ( CrearActividad.class.getResource ( "/img/fondoActividades.png" ) ) );
		this.lblFondoactividad.setBounds ( 0, 0, 795, 495 );
		this.contentPane.add ( this.lblFondoactividad );
	}

	public Object [ ] iniciarComponente ( )
	{
		String [ ] totAct = new String [ tablaGeneral.getRowCount ( ) ];
		for ( int i = 0; i < totAct.length; i++ )
		{
			totAct [ i ] = Integer.toString ( i + 1 );
		}
		return totAct;
	}

	public void inicializarTabla ( JTable table, DefaultTableModel modTabla )
	{

		DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer ( );
		modelocentrar.setHorizontalAlignment ( SwingConstants.CENTER );
		table.setDefaultRenderer ( Object.class, new imgTabla ( ) );

		// DefaultTableModel Tabla = new DefaultTableModel ( datosTabla,
		// encabezado );
		// this.modeloTabla.addRow ( new Object [ ] { 1, "Principal", 3,
		// "PROYECTO A", "Porcentaje", "100%", "100%",
		// new JLabel ( new ImageIcon ( getClass ( ).getResource (
		// "/img/semRojo.png" ) ) ) } );
		// this.modeloTabla.addRow ( new Object [ ] { 2, "Principal", 3,
		// "PROYECTO B", "Porcentaje", "100%", "100%",
		// new JLabel ( new ImageIcon ( getClass ( ).getResource (
		// "/img/semVerde.png" ) ) ) } );
		// this.modeloTabla.addRow ( new Object [ ] { 3, "Principal", 3,
		// "PROYECTO C", "Porcentaje", "100%", "100%",
		// new JLabel ( new ImageIcon ( getClass ( ).getResource (
		// "/img/semNaranja.png" ) ) ) } );

		table.setModel ( modTabla );
		table.setRowHeight ( 30 );

		table.getColumnModel ( ).getColumn ( 0 ).setCellRenderer ( modelocentrar );
		table.getColumnModel ( ).getColumn ( 1 ).setCellRenderer ( modelocentrar );
		table.getColumnModel ( ).getColumn ( 2 ).setCellRenderer ( modelocentrar );
		table.getColumnModel ( ).getColumn ( 4 ).setCellRenderer ( modelocentrar );
		table.getColumnModel ( ).getColumn ( 5 ).setCellRenderer ( modelocentrar );
		table.getColumnModel ( ).getColumn ( 6 ).setCellRenderer ( modelocentrar );
	}

	public JTable getTable ( )
	{
		return tablaGeneral;
	}

	public void setTable ( JTable table )
	{
		this.tablaGeneral = table;
	}

	public JTextField getTxtNombreactividad ( )
	{
		return txtNombreactividad;
	}

	public void setTxtNombreactividad ( JTextField txtNombreactividad )
	{
		this.txtNombreactividad = txtNombreactividad;
	}

	public JTextField getTxtUnidadmedida ( )
	{
		return txtUnidadmedida;
	}

	public void setTxtUnidadmedida ( JTextField txtUnidadmedida )
	{
		this.txtUnidadmedida = txtUnidadmedida;
	}

	public JTextField getTxtValoresperado ( )
	{
		return txtValoresperado;
	}

	public void setTxtValoresperado ( JTextField txtValoresperado )
	{
		this.txtValoresperado = txtValoresperado;
	}

	public JComboBox getCbxTipo ( )
	{
		return cbxTipo;
	}

	public void setCbxTipo ( JComboBox cbxTipo )
	{
		this.cbxTipo = cbxTipo;
	}

	public JComboBox getCbxNivel ( )
	{
		return cbxNivel;
	}

	public void setCbxNivel ( JComboBox cbxNivel )
	{
		this.cbxNivel = cbxNivel;
	}

	public DefaultTableModel getModeloTabla ( )
	{
		return modeloTabla;
	}

	public void setModeloTabla ( DefaultTableModel modeloTabla )
	{
		this.modeloTabla = modeloTabla;
	}

	public JComboBox getCbxIdAct ( )
	{
		return cbxIdAct;
	}

	public void setCbxIdAct ( JComboBox cbxIdAct )
	{
		this.cbxIdAct = cbxIdAct;
	}
}
