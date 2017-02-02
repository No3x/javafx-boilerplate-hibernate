package sample;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.h2.tools.Server;
import sample.gui.GUI;
import sample.gui.GUIConfig;

import java.sql.DriverManager;

/**
 * Created by No3x on 01.02.2017.
 */
public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                install(new GUIConfig());
            }
        });

        try {
            Server.createTcpServer("-tcpAllowOthers").start();
            Class.forName("org.h2.Driver");
            DriverManager.getConnection("jdbc:log4jdbc:h2:file:./database/sample;AUTO_SERVER=TRUE;TRACE_LEVEL_FILE=4");
            final Server webServer = Server.createWebServer();
            webServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        GUI gui = injector.getInstance(GUI.class);


        try {
            gui.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
