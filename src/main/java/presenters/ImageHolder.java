package presenters;
import java.awt.image.BufferedImage;

public class ImageHolder {
    public BufferedImage image;
    public int x, y;

    public ImageHolder(BufferedImage image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }
}

