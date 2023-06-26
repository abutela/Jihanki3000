package com.example.jihanki3000;
import javafx.scene.image.Image;
import java.io.InputStream;

public class Coins {
    int value;
    Image image;
    int imageWidth;
    int imageHeight;

    public Coins(int v, InputStream i, int w, int h){
        image = new Image(i);
        value = v;
        imageWidth = w;
        imageHeight = h;
    }

    static Coins coin500 = new Coins(500, Coins.class.getResourceAsStream("/Images/coin500.png"), 137,129);
    static Coins coin100 = new Coins(100, Coins.class.getResourceAsStream("/Images/coin100.png"), 114,109);
    static Coins coin50 = new Coins(50, Coins.class.getResourceAsStream("/Images/coin50.png"), 109, 99);
    static Coins coin10 = new Coins(10, Coins.class.getResourceAsStream("/Images/coin10.png"),109, 109);
}
