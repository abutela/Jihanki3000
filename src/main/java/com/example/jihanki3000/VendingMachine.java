package com.example.jihanki3000;

import javafx.scene.shape.Rectangle;

public class VendingMachine {
    final static VendingMachine INSTANCE = new VendingMachine();
    static int cashInMachine;
    static int cashInCoinReturn;

    public static void addCash(int money){
        cashInMachine += money;
    }

    public static void checkStock(Goods item, Rectangle button){
        if (item.getStock() == 0){
            button.setOpacity(1.0);
        }
        else{
            button.setOpacity(0.1);
        }
    }

    public static void vend(Goods item){
        if (item.stock > 0) {
            item.stock -= 1;
        }
        else{
            System.out.println("Cannot vend without stock.");
        }
    }

    public static void addStock(Goods item){
        if (item.stock == 0) {
            item.stock += 6;
        }
        else {
            System.out.println("Cannot add stock, machine too full.");
        }
    }

    public static int getStock(Goods item) {
        return item.getStock();
    }

}

