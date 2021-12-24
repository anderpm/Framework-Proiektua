package test.gui;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.swing.core.matcher.JButtonMatcher.withName;
import static org.assertj.swing.core.matcher.JButtonMatcher.withText;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.core.matcher.FrameMatcher;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.data.TableCell;

import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JComboBoxFixture;
import org.assertj.swing.fixture.JTableCellFixture;
import org.assertj.swing.fixture.JTableFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.assertj.swing.junit.runner.GUITestRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dao.DAOManager;
import dataAccess.DataAccess;
import dataAccess.DataAccessDAO;
import dataAccess.DataAccessInterface;
import gui.MainGUI;
/**
 * @see https://www.jc-mouse.net/
 * @author mouse
 */
//@RunWith(GUITestRunner.class)
public class TestApp {

    private static FrameFixture mainWindowFixture;
    private static JFrame mainGUI;
    private static FrameFixture frame2;
    private static Robot robot;
    public static int v=0;


    /**
     * Constructor de clase
     */
    public TestApp() {}

    /**
     * Fuerza a una prueba a fallar si el acceso a los componentes de la GUI
     * no se realiza en el EDT (Event Dispatch Thread)
     */
    @BeforeClass
    public  static void setUpOnce() {
        robot = BasicRobot.robotWithNewAwtHierarchy();

        mainGUI= createMainGUI();
		mainGUI.setVisible(true);

        mainWindowFixture = new FrameFixture(robot,mainGUI);

    }

    /**
     * Inicializa los dispositivos de prueba, se ejecuta cada vez 
     * que se ejecute un método de prueba
     */
    @Before
    public void setUp() {
    	
        
        
    }

    /**     
     * Limpia los recursos utilizados después de ejecutar cada método de prueba
     * y libera el bloqueo de teclado y moyse para la siguiente prueba     
     */
    @After
    public void tearDown() {
        mainWindowFixture.cleanUp();
    }
    
 /*   @Test
    public void SearchEventQueries() {      
    	window.button(JButtonMatcher.withText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries")).andShowing()).click();
        FrameFixture frame = WindowFinder.findFrame(FrameMatcher.withTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"))).using(robot).requireVisible();
        Utils.setDateCalendar(frame,2021,11,17);
  	//lo(6);
     JTableFixture tableEventFixture=frame.table("tableEvents");
     tableEventFixture.cell(TableCell.row(0).column(0)).click();
     //tableEventFixture.cell("Atletico-Athletic").click();

     //tableEventFixture.click();
     //tableEventFixture.cell(TableCell.row(1).column(0)).click();
     JTableFixture tableQueriesFixture=frame.table("tableQueries");
     System.out.println("rows "+ tableQueriesFixture.rowCount());
     JTableCellFixture cell;
     for (int r=0; r<tableQueriesFixture.rowCount(); r++) {
         for (int c=0; c<2; c++) {
    	  cell=tableQueriesFixture.cell(TableCell.row(r).column(c));
    	  System.out.print(cell.value()+" ");
         }
     	System.out.println();
    }
    

  //JButtonFixture buttonClose=frame.button(JButtonMatcher.withText(ResourceBundle.getBundle("Etiquetas").getString("Close")).andShowing());
  	//buttonClose.click();
    	
    	//JPanelFixture bf=dayFixture.button(andText("25"));
    	
    	
    
    	//jCalendar.getMonthChooser().getComboBox()
    	//ComponentFinder finder = BasicComponentFinder.finderWithNewAwtHierarchy();
    	//finder.findByName("login", true); // will fail finding component of login frame
    	// new MainFrame();
    	//finder.findByName(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"), true); // will work finding label of main frame
    	//window.textBox("numero").enterText("45");
        //window.comboBox("opcion").selectItem("Octal");        
        //window.button(withName("convertir")).click();       
        //realiza la comparación de resultados
        //assertThat(window.textBox("resultado").text()).isEqualTo("55");      
    	  
    }
    */
    @Test
    public void createAndThenQueryEvent() {  
    	int row=3; //selected Event
    	mainWindowFixture.button(JButtonMatcher.withText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery")).andShowing()).click();
        FrameFixture frame = WindowFinder.findFrame(FrameMatcher.withTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"))).using(robot).requireVisible();
        
        
        Utils.setDateCalendar(frame,2021,11,17);  
        JComboBoxFixture comboFixture=frame.comboBox("comboEvents");
        comboFixture.selectItem(row);
        
        
        frame.textBox("QueryText").enterText("Hello World!");    
        frame.textBox("BidPrice").enterText("4");    
        
        JButtonFixture buttonCreate=frame.button(JButtonMatcher.withText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery")).andShowing());
        buttonCreate.click();
        Utils.lo(6);
        JButtonFixture buttonClose=frame.button(JButtonMatcher.withText(ResourceBundle.getBundle("Etiquetas").getString("Close")).andShowing());
        buttonClose.click();

        mainWindowFixture.button(JButtonMatcher.withText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries")).andShowing()).click();
        FrameFixture frame2 = WindowFinder.findFrame(FrameMatcher.withTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"))).using(robot).requireVisible();
        Utils.setDateCalendar(frame2,2021,11,17);
  	
     JTableFixture tableEventFixture=frame2.table("tableEvents");
     tableEventFixture.cell(TableCell.row(row).column(0)).click();
     //tableEventFixture.cell("Atletico-Athletic").click();

     //tableEventFixture.click();
     //tableEventFixture.cell(TableCell.row(1).column(0)).click();
     JTableFixture tableQueriesFixture=frame2.table("tableQueries");
    
     JTableCellFixture cell;
     for (int r=0; r<tableQueriesFixture.rowCount(); r++) {
         for (int c=0; c<2; c++) {
    	  cell=tableQueriesFixture.cell(TableCell.row(r).column(c));
    	  System.out.print(cell.value()+" ");
         }
     	System.out.println();
    }
    Utils.lo(6);
        
        

    }  
   
    public static JFrame createMainGUI() {
    	
    	ConfigXML c=ConfigXML.getInstance();
    	
		System.out.println(c.getLocale());
		
		Locale.setDefault(new Locale(c.getLocale()));
		
		System.out.println("Locale: "+Locale.getDefault());
		
		MainGUI mainGUI=new MainGUI();



		try {
			
			BLFacade appFacadeInterface;
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			
			if (c.isBusinessLogicLocal()) {
				
				//In this option the DataAccess is created by FacadeImplementationWS
				//appFacadeInterface=new BLFacadeImplementation();

				//In this option, you can parameterize the DataAccess (e.g. a Mock DataAccess object)
				String dbManagerClassName=c.getDAOManagerClassName();
				DataAccessInterface da;
				if (dbManagerClassName.length()==0)
					 da= new DataAccess();
				else {
					DAOManager daoManager=(DAOManager)Class.forName("dao."+dbManagerClassName).newInstance();
					da=new DataAccessDAO(daoManager);
				} 					
				
				appFacadeInterface=new BLFacadeImplementation(da);

				
			}
			
			else { //If remote
				
				 String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
				 
				//URL url = new URL("http://localhost:9999/ws/ruralHouses?wsdl");
				URL url = new URL(serviceName);

		 
		        //1st argument refers to wsdl document above
				//2nd argument is service name, refer to wsdl document above
//		        QName qname = new QName("http://businessLogic/", "FacadeImplementationWSService");
		        QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
		 
		        Service service = Service.create(url, qname);

		         appFacadeInterface = service.getPort(BLFacade.class);
			} 
			/*if (c.getDataBaseOpenMode().equals("initialize")) 
				appFacadeInterface.initializeBD();
				*/
			MainGUI.setBussinessLogic(appFacadeInterface);
 } catch (Exception e) {
		//a.jLabelSelectOption.setText("Error: "+e.toString());
		//a.jLabelSelectOption.setForeground(Color.RED);	
		
		System.out.println("Error in ApplicationLauncher: "+e.toString());
	}
		return mainGUI;
}
   
    
}


