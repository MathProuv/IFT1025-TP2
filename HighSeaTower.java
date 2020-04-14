import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Mathilde Prouvost et Augustine Poirier
 */
public class HighSeaTower extends Application {
    private int width = 350, height = 480;

    private Controleur controleur;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.controleur = new Controleur();

        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, width, height, Color.rgb(0, 25, 99));

        Canvas canvas = new Canvas(width, height);
        root.getChildren().add(canvas);

        AnimationTimer timerBulles = new AnimationTimer() {
            private long startTime;
            private int nbBulles;

            @Override
            public void handle(long now) {
                if (startTime == 0) {
                    startTime = now;
                    return;
                }

                double deltaT = (now - startTime)*1e-9;

                if (deltaT > nbBulles * 3) {
                    controleur.creerBulles();
                    nbBulles ++;
                }
            }
        };

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()){
                case T:
                    controleur.deboguer();
                    break;
                case ESCAPE:
                    Platform.exit();
                    break;
                case SPACE:
                case UP:
                    controleur.commencer();
                    timerBulles.start();
                    controleur.sauter();
                    break;
                case LEFT:
                    controleur.commencer();
                    timerBulles.start();
                    controleur.tourner(false);
                    break;
                case RIGHT:
                    controleur.commencer();
                    timerBulles.start();
                    controleur.tourner(true);
                    break;
            }
        });
        scene.setOnKeyReleased(e -> {
            switch (e.getCode()){
                case UP:
                case SPACE:
                    break;
                case LEFT:
                case RIGHT:
                    controleur.stopTourn();
            }
        });

        GraphicsContext context = canvas.getGraphicsContext2D();

        AnimationTimer timer = new AnimationTimer() {
            private long startTime = 0;
            private long lastTime = 0;

            @Override
            public void handle(long now) {
                if(startTime == 0 || lastTime == 0) {
                    startTime = now;
                    lastTime = now;
                    return;
                }

                double deltaT = now - startTime;

                double dt = (now - lastTime) * 1e-9;

                context.clearRect(0, 0, width, height);
                controleur.update(dt, deltaT);
                controleur.draw(context);

                lastTime = now;
            }
        };

        timer.start();

        primaryStage.setScene(scene);
        primaryStage.setTitle("High Sea Tower");
        primaryStage.setResizable(false);
        Image logoMeduse = new Image("/images/jellyfish1.png");
        primaryStage.getIcons().add(logoMeduse);

        primaryStage.show();
    }
}
