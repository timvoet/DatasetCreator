/*
 * DatasetCreatorApp.java
 */
package com.voet.datasetcreator;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import com.jgoodies.looks.plastic.theme.BrownSugar;
import com.jgoodies.looks.plastic.theme.DesertBlue;
import com.jgoodies.looks.plastic.theme.ExperienceGreen;
import com.jgoodies.looks.plastic.theme.ExperienceRoyale;
import com.jgoodies.looks.plastic.theme.SkyBlue;
import com.jgoodies.looks.plastic.theme.SkyYellow;
import com.jgoodies.looks.windows.WindowsLookAndFeel;
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
import org.jvnet.substance.api.skin.SubstanceGraphiteAquaLookAndFeel;
import org.jvnet.substance.skin.SubstanceAutumnLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel;
import org.jvnet.substance.skin.SubstanceCremeCoffeeLookAndFeel;

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
        LookAndFeel laf = null;
        laf = new InfoNodeLookAndFeel();
        if ( laf.isSupportedLookAndFeel() ) {
            UIManager.installLookAndFeel( new UIManager.LookAndFeelInfo( laf.getName(), laf.getClass().getName() ) );
        }
        laf = new SubstanceBusinessBlueSteelLookAndFeel();
        if ( laf.isSupportedLookAndFeel() ) {
            UIManager.installLookAndFeel( new UIManager.LookAndFeelInfo( laf.getName(), laf.getClass().getName() ) );
        }
        laf = new SubstanceCremeCoffeeLookAndFeel();
        if ( laf.isSupportedLookAndFeel() ) {
            UIManager.installLookAndFeel( new UIManager.LookAndFeelInfo( laf.getName(), laf.getClass().getName() ) );
        }
        laf = new SubstanceGraphiteAquaLookAndFeel();
        if ( laf.isSupportedLookAndFeel() ) {
            UIManager.installLookAndFeel( new UIManager.LookAndFeelInfo( laf.getName(), laf.getClass().getName() ) );
        }

        laf = new WindowsLookAndFeel();
        if ( laf.isSupportedLookAndFeel() ) {
            UIManager.installLookAndFeel( new UIManager.LookAndFeelInfo( laf.getName(), laf.getClass().getName() ) );
        }
        laf = new PlasticLookAndFeel();
        if ( laf.isSupportedLookAndFeel() ) {
            UIManager.installLookAndFeel( new UIManager.LookAndFeelInfo( laf.getName(), laf.getClass().getName() ) );
        }

        PlasticLookAndFeel.set3DEnabled( true );
        PlasticLookAndFeel.setPlasticTheme( new ExperienceGreen());
        laf = new Plastic3DLookAndFeel();
        if ( laf.isSupportedLookAndFeel() ) {
            UIManager.installLookAndFeel( new UIManager.LookAndFeelInfo( laf.getName(), laf.getClass().getName() ) );
        }

        laf = new PlasticXPLookAndFeel();
        if ( laf.isSupportedLookAndFeel() ) {
            UIManager.installLookAndFeel( new UIManager.LookAndFeelInfo( laf.getName(), laf.getClass().getName() ) );
        }

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
