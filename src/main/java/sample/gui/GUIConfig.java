package sample.gui;

import com.github.vbauer.herald.ext.guice.LogModule;
import com.google.inject.AbstractModule;
import sample.database.DatabaseModule;

/**
 * Created by No3x on 01.02.2017.
 */
public class GUIConfig extends AbstractModule {
    @Override
    protected void configure() {
        install( new LogModule() );
        install( new DatabaseModule() );
    }
}
