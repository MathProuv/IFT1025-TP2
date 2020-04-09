import javafx.scene.canvas.GraphicsContext;

public class Jeu {
    private Meduse meduse;

    public Jeu(){
        this.meduse = new Meduse();
    }

    public void update(double dt){ }

    public void draw(GraphicsContext context){
        meduse.draw(context);
    };
}
