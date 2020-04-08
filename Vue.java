import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Vue extends Application {
    private int width = 350, height = 480;
//*******************************
    private Integer score;
    private Meduse meduse;
//*******************************
    private Controleur controleur;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //**************************
        this.meduse = new Meduse();
        this.score = 0;
        //**************************
        this.controleur = new Controleur(this);

        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, width, height, Color.BLUE);

        Canvas canvas = new Canvas(width, height);
        root.getChildren().add(canvas);

        Jeu jeu = new Jeu();

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
            private long lastTime = 0;

            @Override
            public void handle(long now) {
                if (lastTime == 0) {
                    lastTime = now;
                    return;
                }

                double dt = (now - lastTime) * 1e-9;

                context.clearRect(0, 0, width, height);
                jeu.update(dt);
                jeu.draw(context);

                lastTime = now;
            }
        };

        timer.start();

        // **********************************************
        // Score
        HBox scoreBox = new HBox();
        scoreBox.setAlignment(Pos.CENTER);
        Text texteScore = new Text(score.toString());
        texteScore.setFont(Font.font(25));
        texteScore.setFill(Color.RED);
        scoreBox.getChildren().add(texteScore);
        root.setTop(scoreBox);

        // Méduse
        Pane pane = new Pane();
        Image imgMeduse = meduse.getImage();
        root.setCenter(pane);
        // *********************************************

        primaryStage.setScene(scene);
        primaryStage.setTitle("High Sea Tower");
        primaryStage.setResizable(false);
        Image logoMeduse = new Image("/images/jellyfish1.png");
        primaryStage.getIcons().add(logoMeduse);

        primaryStage.show();
    }
}
