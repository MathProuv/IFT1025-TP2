import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * @author Mathilde Prouvost et Augustine Poirier
 */
public class Vue extends Application {
    private int width = 350, height = 480;

    private Controleur controleur;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.controleur = new Controleur();

        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, width, height, Color.BLUE);

        Canvas canvas = new Canvas(width, height);
        root.getChildren().add(canvas);

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
                    controleur.sauter();
                    break;
                case LEFT:
                    controleur.bouger(false);
                    break;
                case RIGHT:
                    controleur.bouger(true);
                    break;
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

        // **********************************************
        // Score
        HBox scoreBox = new HBox();
        scoreBox.setAlignment(Pos.CENTER);
        Text texteScore = new Text(controleur.jeu.getScore().toString());
        texteScore.setFont(Font.font(25));
        texteScore.setFill(Color.RED);
        scoreBox.getChildren().add(texteScore);
        root.setTop(scoreBox);
        // *********************************************

        primaryStage.setScene(scene);
        primaryStage.setTitle("High Sea Tower");
        primaryStage.setResizable(false);
        Image logoMeduse = new Image("/images/jellyfish1.png");
        primaryStage.getIcons().add(logoMeduse);

        primaryStage.show();
    }
}
