package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Controller {

    @FXML
    public ImageView imageView;
    @FXML
    private Slider zoomSlider;
    @FXML
    private Label label;
    @FXML
    public String filePath;
    @FXML
    public FileChooser fileChooser;
    @FXML
    public File file;
    @FXML
    private Stage stage;
    @FXML
    private Button Save;
    @FXML
    private Button delete;
    @FXML
    private Button rotate;
    @FXML
    private Button moins;
    @FXML
    private Button plus;
    @FXML
    private Button avance;
    @FXML
    private Button recule;
    @FXML
    public Image image;


    @FXML
    public void Open() {

        fileChooser = new FileChooser();
        fileChooser.getInitialDirectory();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("choose a png", "*png"));
        file = fileChooser.showOpenDialog(null);

        filePath = file.toURI().toString();
        if (filePath != null) {
            image = new Image(filePath);
            imageView.setImage(image);
            imageView.setPreserveRatio(true);

            DoubleProperty width = imageView.fitWidthProperty();
            DoubleProperty height = imageView.fitHeightProperty();
            width.bind(Bindings.selectDouble(imageView.sceneProperty(), "width"));
            height.bind(Bindings.selectDouble(imageView.sceneProperty(), "height"));
            label.setText("" + file.getName());
            Save.setDisable(false);
            delete.setDisable(false);
            rotate.setDisable(false);
            moins.setDisable(false);
            plus.setDisable(false);
            avance.setDisable(false);
            recule.setDisable(false);
        }
    }

    @FXML
    private void Save() { //Save
        Stage secondStage = new Stage();
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("save a png", "*png"));
        file = fileChooser.showSaveDialog(secondStage);
        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(), null), "png", file);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void erease() {
        try {
            if (file.delete()) { //on utilise la fonction delete sur file
                imageView.setImage(null); //on efface l'image de l'affichage
                label.setText(null); //on efface le label de l'affichage
                System.out.println(file.getName() + "gg wp BOIIII file is deleted");
            }
        } catch (Exception e) {
            System.out.println("bg BAAAD");
        }
    }

    public void rotate() { //rotate
        double value = imageView.getRotate(); //on prends la value rotate de imageview
        imageView.setRotate(value + 30); //on incremente de 30 la value a chaque fois que le button est cliquer

    }

    @FXML
    public void zoomIn() { //zoomIn
        //  imageView.setFitWidth(imageView.getFitWidth() * 2);
        // imageView.setFitHeight(imageView.getFitHeight() * 2);
        imageView.setScaleX(imageView.getScaleX() * 2);
        imageView.setScaleY(imageView.getScaleY() * 2);
    }





    @FXML
    public void zoomout() { //zoomout
        // imageView.setFitWidth(imageView.getFitWidth() / 2);
        //imageView.setFitHeight(imageView.getFitHeight() / 2);
        imageView.setScaleX(imageView.getScaleX() / 2);
         imageView.setScaleY(imageView.getScaleY() / 2);

    }


    public void effect1 () throws IOException { //effect window
        Stage primaryStage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("edit.fxml"));
        primaryStage.setTitle("Editor");
        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("edit1.png")));
        primaryStage.setScene(new Scene(root, 629, 478));

        primaryStage.show();

    }








}


