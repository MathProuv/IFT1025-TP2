import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Vue extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 350, 480, Color.MIDNIGHTBLUE);

        Integer score = 0;
        Text texteScore = new Text(score.toString());
        texteScore.setFont(Font.font(25));
        texteScore.setFill(Color.RED);

        HBox scoreBox = new HBox();
        scoreBox.setAlignment(Pos.CENTER);
        scoreBox.getChildren().add(texteScore);
        root.setTop(scoreBox);

        primaryStage.setScene(scene);
        primaryStage.setTitle("High Sea Tower");
        primaryStage.setResizable(false);
        Image imgMeduse = new Image("/images/jellyfish1.png");
        primaryStage.getIcons().add(imgMeduse);

        primaryStage.show();
    }
}
