package sample;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Controller2 extends Controller {
    Controller C = new Controller();


    public ImageView imageView2;
    private FileChooser fileChooser2;
    private String filePath2;
    private File file2;
    private Image image2;

    @FXML
    public void afficher() {
        fileChooser2= new FileChooser();
        fileChooser2.getInitialDirectory();
        fileChooser2.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("choose a png", "*png"));
        file2 = fileChooser2.showOpenDialog(null);

        filePath2 = file2.toURI().toString();
        if (filePath2 != null) {
            image2 = new Image(filePath2);
            imageView2.setImage(image2);
            imageView2.setPreserveRatio(true);

            DoubleProperty width = imageView2.fitWidthProperty();
            DoubleProperty height = imageView2.fitHeightProperty();
            width.bind(Bindings.selectDouble(imageView2.sceneProperty(),"width"));
            height.bind(Bindings.selectDouble(imageView2.sceneProperty(),"height"));



        }


    }
    @FXML
    private void sepiaEffect(){
        SepiaTone sepia =new SepiaTone();
        imageView2.setEffect(sepia);
    }

    @FXML
    private void Save(){
        Stage secondStage = new Stage();

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("save a png", "*png"));
        file = fileChooser.showSaveDialog(secondStage);
        if (file != null){
            try{
               // ImageIO.write(SwingFXUtils.fromFXImage(imageView2.getImage(),null),"png",file); save image sans effet
                ImageIO.write(SwingFXUtils.fromFXImage(imageView2.snapshot(new SnapshotParameters(),null),null),"png",file); //save avec effet
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    }
