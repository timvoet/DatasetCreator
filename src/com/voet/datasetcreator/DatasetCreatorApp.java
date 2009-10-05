/*
 * DatasetCreatorApp.java
 */
package com.voet.datasetcreator;

import com.voet.datasetcreator.swing.MyComboBoxModel;
import com.voet.datasetcreator.util.ConnectionStringUtil;
import com.voet.datasetcreator.util.DriverLocator;
import com.voet.datasetcreator.util.Tuple;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.infonode.gui.laf.InfoNodeLookAndFeel;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import org.jvnet.substance.SubstanceLegacyDefaultLookAndFeel;
import org.jvnet.substance.SubstanceLookAndFeel;

/**
 * The main class of the application.
 */
public class DatasetCreatorApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        show( new DatasetCreatorView( this ) );
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow( java.awt.Window root ) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of DatasetCreatorApp
     */
    public static DatasetCreatorApp getApplication() {
        return Application.getInstance( DatasetCreatorApp.class );
    }

    /**
     * Main method launching the application.
     */
    public static void main( String[] args ) {
        installCustomLAFs();
        launch( DatasetCreatorApp.class, args );
    }

    public static MyComboBoxModel getDriverList() {
        MyComboBoxModel drivers = new MyComboBoxModel();
        List<String> driverClasses = DriverLocator.locateDrivers();
        drivers.add( new Tuple( "", "Please Select from the availble drivers" ) );
        for ( String driver : driverClasses ) {
            if ( ConnectionStringUtil.isKnownDriver( driver ) ) {
                drivers.add( new Tuple( driver, ConnectionStringUtil.getDriverName( driver ) ) );
            }
        }
        return drivers;
    }

    public static void installCustomLAFs() {
        // Add custom LAF's
        UIManager.installLookAndFeel( "Info Node", InfoNodeLookAndFeel.class.getName() );
        UIManager.installLookAndFeel( "Substance", SubstanceLegacyDefaultLookAndFeel.class.getName() );
    }

    public static void updateLAF( String className ) {
        try {
            UIManager.setLookAndFeel( className );
            SwingUtilities.updateComponentTreeUI(
                    Application.getInstance( DatasetCreatorApp.class ).getMainFrame() );
        } catch ( ClassNotFoundException ex ) {
            Logger.getLogger( DatasetCreatorApp.class.getName() ).log( Level.SEVERE, null, ex );
        } catch ( InstantiationException ex ) {
            Logger.getLogger( DatasetCreatorApp.class.getName() ).log( Level.SEVERE, null, ex );
        } catch ( IllegalAccessException ex ) {
            Logger.getLogger( DatasetCreatorApp.class.getName() ).log( Level.SEVERE, null, ex );
        } catch ( UnsupportedLookAndFeelException ex ) {
            Logger.getLogger( DatasetCreatorApp.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }
}
