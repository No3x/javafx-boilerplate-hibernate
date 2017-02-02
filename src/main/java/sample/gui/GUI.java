package sample.gui;

import com.gluonhq.ignite.guice.GuiceContext;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Arrays;

public class GUI extends Application {

    private GuiceContext guiceContext;

    @Override
    public void start(Stage primaryStage) throws Exception {

        guiceContext = new GuiceContext(this, () -> Arrays.asList(new GUIConfig()));
        guiceContext.init();
        final WindowManager stageController = guiceContext.getInstance(WindowManager.class);
        stageController.switchScene(WindowManager.SCENES.PERSON_LIST_SCENE);
    }

    public void run(String[] args) {
        launch(args);
    }
}
