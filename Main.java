package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        stage.setTitle("Pie Chart");
        Canvas canvas = new Canvas(800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        String s = "C:\\Users\\Maui Arcuri\\Desktop\\emma.txt";
        HistogramLetters emma = new HistogramLetters(s);
        emma.setLetters_probabilities(emma.letters_frequencies);
        emma.setLetters_angles(emma.letters_probabilities);
        emma.setNSorted(emma.letters_frequencies);

        PieChart chart = new PieChart(emma.getLetter_probabilites(), emma.getLetters_angles(), emma.getNSorted());

        Pane root = new Pane();
        root.getChildren().add(canvas);
        chart.nInput(gc, root, gc.getCanvas().getWidth(), gc.getCanvas().getWidth());//Input and button calls drawChart

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}

