package ru.javabegin.javafx.addressbook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.javabegin.javafx.addressbook.controllers.MainController;
import ru.javabegin.javafx.addressbook.interfaces.impls.CollectionAddressBook;
import ru.javabegin.javafx.addressbook.objects.Lang;

import java.io.IOException;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class Main extends Application implements Observer {
    public static final Locale DEFAULT_LOCALE = new Locale("ru");
    public static final String BUNDLES_FOLDER = "ru.javabegin.javafx.addressbook.locales.Locale";
    public static final String MAIN_FXML = "main.fxml";

    private MainController mainController;
    private FXMLLoader fxmlLoader;
    private Stage stage;
    private VBox currentRoot;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        createGUI(DEFAULT_LOCALE);
    }

    // загружает дерево компонентов и возвращает в виде VBox (корневой элемент в FXML)
    private VBox loadFXML(Locale locale) {
        fxmlLoader = new FXMLLoader();

        fxmlLoader.setLocation(getClass().getResource(MAIN_FXML));
        fxmlLoader.setResources(ResourceBundle.getBundle(BUNDLES_FOLDER, locale));

        VBox node = null;

        try {
            node = (VBox) fxmlLoader.load();

            mainController = fxmlLoader.getController();
            mainController.addObserver(this);
            stage.setTitle(fxmlLoader.getResources().getString("address_book"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return node;
    }

    private void createGUI(Locale locale) {
        currentRoot = loadFXML(locale);
        Scene scene = new Scene(currentRoot, 300, 275);
        stage.setMinHeight(700);
        stage.setMinWidth(600);
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void update(Observable o, Object arg) {
        Lang lang = (Lang) arg;
        VBox newNode = loadFXML(lang.getLocale()); // получить новое дерево компонентов с нужной локалью
        currentRoot.getChildren().setAll(newNode.getChildren()); // заменить старые дочерние компонента на новые - с другой локалью
    }

    @Override
    public void stop() throws Exception {
        System.exit(0);
    }
}