package com.example.jihanki3000;
import javafx.scene.image.Image;
import java.io.InputStream;

public class Goods {
    String flavor;
    Image image;
    int price;
    double milliliters;
    int stock;

    public Goods(String f, InputStream i, int p, double ml){
        flavor = f;
        image = new Image(i);
        price = p;
        milliliters = ml;
        stock = 6;
    }

    static Goods water = new Goods("water", Goods.class.getResourceAsStream("/Images/water.png"), 100, 1000);
    static Goods ocha = new Goods("green tea", Goods.class.getResourceAsStream("/Images/ocha.png"), 120, 1000);
    static Goods mugicha = new Goods("wheat tea", Goods.class.getResourceAsStream("/Images/mugicha.png"), 120, 1000);
    static Goods cider = new Goods("cider",Goods.class.getResourceAsStream("/Images/cider.png"), 150, 1000);
    static Goods cola = new Goods("cola",Goods.class.getResourceAsStream("/Images/soda.png"), 150, 1000);
    static Goods sportsDrink = new Goods("sports drink",Goods.class.getResourceAsStream("/Images/sports.png"), 150, 1000);

    public int getStock() {
        return stock;
    }

    public void setStock(int i){
        stock = i;
    }

}
