package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;

import java.util.*;

public class PieChart {

    HashMap<Character, Double> probabilities, angles;
    ArrayList<Character> n_sorted;

    public PieChart(HashMap probabilities, HashMap angles, ArrayList<Character> n_sorted) {
        this.probabilities = probabilities;
        this.angles = angles;
        this.n_sorted = n_sorted;
    }

    public void drawChart (GraphicsContext gc, Pane root, int n, double canvasW, double canvasH) {
        double start_angle = 0;
        double end_angle = 0;
        double total_probabilities = 0;
        double centerX = canvasW/2;
        double centerY = canvasH/2;
        double RADIUS = 300;


        ArrayList<Text> labels = new ArrayList<Text>();

        for (int i = 0; i < n; i ++) {

            Random rand = new Random();

            gc.setFill((Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255))));

            end_angle = angles.get(n_sorted.get(i));
            //Next lines for testing purposes
            //System.out.println("Letter: " + n_sorted.get(i) +  " Angle: " + end_angle + " Start Angle: " +
            // start_angle + " Color: " + gc.getFill());


            gc.fillArc(canvasW / 2 - RADIUS, canvasH / 2 - RADIUS, 2*RADIUS, 2*RADIUS,
                    start_angle, end_angle, ArcType.ROUND);


            //Text constructor takes x, y coordinates, and the string
            //The X field uses the function: CenterX + (radius * cos(angle)) to find the coordinate,
            //it's also similar for the y coordinate. I added half of the next angle to center the text.

            labels.add(new Text(centerX + ((RADIUS+50) * (Math.cos((start_angle + .5 * end_angle )* (Math.PI/180)))),
                    centerY + ((RADIUS+50) * -Math.sin((start_angle + .5 * end_angle )* (Math.PI/180))),
                    n_sorted.get(i) + " , " +
                            Math.round((probabilities.get((n_sorted.get(i)))) * 1000.0)/ 1000.0));
                            //Probabilities are rounded of to thousands place

            start_angle += angles.get(n_sorted.get(i));
            total_probabilities += probabilities.get(n_sorted.get(i));

            root.getChildren().add(labels.get(i));

        }
        //If n doesn't include all letters, make extra slice for all other letters and a text object

        Random rand = new Random();

        gc.setFill((Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255))));
        if (n < n_sorted.size()) {
            gc.fillArc(canvasW / 2 - RADIUS, canvasH / 2 - RADIUS, 2*RADIUS, 2*RADIUS,
                    start_angle, 360 - start_angle, ArcType.ROUND);

            labels.add(new Text(centerX + ((RADIUS+50) *
                    (Math.cos((start_angle + .5 * (360 - start_angle) )* (Math.PI/180)))),
                    centerY + ((RADIUS+50) * -Math.sin((start_angle + .5 * (360 - start_angle) )* (Math.PI/180))),
                    "All other letters, " + Math.round((1 - total_probabilities) * 1000.0)/ 1000.0));

            root.getChildren().add(labels.get(n));
        }


    }

    public void nInput(GraphicsContext gc, Pane root, double canvasW, double canvasH) {

        // create a textfield
        TextField b = new TextField("Enter n");
        Button button = new Button("Enter");
        button.setLayoutX(150);
        root.getChildren().add(b);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                drawChart(gc, root, Integer.parseInt(b.getText()), canvasW, canvasH);
            }
        };
        button.setOnAction(event);
        root.getChildren().add(button);

    }



}
