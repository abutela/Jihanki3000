package com.example.jihanki3000;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Objects;
import java.util.Stack;

public class JihankiController {

    protected Stack<Coins> cashInHand = new Stack<Coins>();
    protected int cashInHandTotal = 0;
    protected static int returnedCoins = 0;
    SoundsClass sounds = SoundsClass.getInstance();

    int available500s, available100s, available50s, available10s;

    @FXML
    Rectangle waterButton, ochaButton, mugichaButton, bayDoor, returnLever;
    @FXML
    Label cashInHandLabel, insertedCashLabel, insertedCashLabelBig, available500Label,
            pullLabel, available100Label, available50Label, available10Label;
    @FXML
    ImageView heldCoin1, heldCoin2, heldCoin3, heldCoin4, heldCoin5, heldCoin6,
            bottle1, bottle2, bottle3, coinPile;

    @FXML
    protected void onWaterButtonClick(){

        vendGoods(Goods.water, waterButton);
    }

    @FXML
    protected void onOchaButtonClick(){

        vendGoods(Goods.ocha, ochaButton);
    }

    @FXML
    protected void onMugichaButtonClick(){

        vendGoods(Goods.mugicha, mugichaButton);
    }

    @FXML
    protected void vendGoods(Goods item, Rectangle button){
        System.out.println(item.flavor + " button clicked.");
        if (VendingMachine.getStock(item) > 0 && VendingMachine.cashInMachine >= item.price && !bottle3.isVisible()){
            item.setStock(item.stock -= 1);
            System.out.println(item.flavor + " dispensed.");

            VendingMachine.cashInMachine -= item.price;
            updateCashDisplay();

            if(!bottle1.isVisible()) {
                bottle1.setImage(item.image);
                bottle1.setVisible(true);
            }
            else if(!bottle2.isVisible()){
                bottle2.setImage(item.image);
                bottle2.setVisible(true);
                }
            else if (!bottle3.isVisible()){
                bottle3.setImage(item.image);
                bottle3.setVisible(true);
                }
            sounds.playThunk();
        }
        else if (item.getStock() == 0){
            System.out.println("There's no stock. Can't dispense that which doesn't exist.");
            setInfoText("no stock");
        }
        else if(VendingMachine.cashInMachine < item.price){
            System.out.println("Gotta insert more cash, my dude.");
            setInfoText("add cash");
        }
        else if (bottle3.isVisible()){
            System.out.println("Too many bottles in bay, must remove to continue.");
            setInfoText("bay full");
            }
        VendingMachine.checkStock(item, button);
    }

    protected void setInfoText(String message){
        String regularMessage = insertedCashLabel.getText();
        insertedCashLabelBig.setStyle("-fx-font-family: 'DigitalICG'; -fx-font-size: 48.0");
        insertedCashLabel.setStyle("-fx-font-family: 'DigitalICG'; -fx-font-size: 10.0");
        insertedCashLabelBig.setText(message);
        insertedCashLabel.setText(message);
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> resetCashText(regularMessage));
        pause.play();
    }

    protected void resetCashText(String message){
        insertedCashLabelBig.setStyle("-fx-font-family: 'DigitalICG'; -fx-font-size: 64.0");
        insertedCashLabel.setStyle("-fx-font-family: 'DigitalICG'; -fx-font-size:12");
        insertedCashLabel.setText(message);
        insertedCashLabelBig.setText(message);
    }

    @FXML
    protected void onReturnLeverClick() {
        sounds.playClick();
        countChange();
        System.out.println("Return lever clicked.");

    }

    private void countChange(){
        PauseTransition pauseSounds = new PauseTransition(Duration.millis(500));
        PauseTransition pauseCoinDrop = new PauseTransition(Duration.millis(800));
        pauseSounds.setOnFinished(e->sounds.playCoinReturn());
        pauseCoinDrop.setOnFinished(e->displayReturnedCoins());

        while (VendingMachine.cashInMachine > 0){
            if (VendingMachine.cashInMachine >= 500){
                available500s += 1;
                VendingMachine.cashInMachine -= 500;
                setAvailableCoinLabels(available500s, available500Label);
                returnedCoins += 1;
            }
            else if (VendingMachine.cashInMachine >= 100){
                available100s += 1;
                VendingMachine.cashInMachine -= 100;
                setAvailableCoinLabels(available100s, available100Label);
                returnedCoins += 1;
            }
            else if (VendingMachine.cashInMachine >= 50){
                available50s += 1;
                VendingMachine.cashInMachine -= 50;
                setAvailableCoinLabels(available50s, available50Label);
                returnedCoins += 1;
            }
            else {
                available10s += 1;
                VendingMachine.cashInMachine -= 10;
                setAvailableCoinLabels(available10s, available10Label);
                returnedCoins += 1;
            }
            pauseSounds.play();
            pauseCoinDrop.play();
        }
    }

    private void updateCashDisplay() {
        insertedCashLabel.setText("¥" + (VendingMachine.cashInMachine));
        insertedCashLabelBig.setText(insertedCashLabel.getText());
    }

    private void displayReturnedCoins(){
        if (returnedCoins > 3){
            coinPile.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/coinPile.png"))));
        } else if (returnedCoins > 2) {
            coinPile.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/threeCoins.png"))));
        }
        else if (returnedCoins > 1){
            coinPile.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/twoCoins.png"))));
        }
        else if (returnedCoins > 0){
            coinPile.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/oneCoin.png"))));
        }
        coinPile.setVisible(true);
    }

    private void setAvailableCoinLabels(int numAvailable, Label coinLabel){
        if (numAvailable == 0){
            coinLabel.setText("x 00");
        }
        else if (numAvailable < 10) {
            coinLabel.setText("x 0" + numAvailable);
        }
        else{
            coinLabel.setText("x " + numAvailable);
        }
    }


    @FXML
    protected void onCoinSlotCLick() {
        while (!cashInHand.isEmpty()) {
            Coins coin = cashInHand.pop();
            cashInHandTotal -= coin.value;
            VendingMachine.addCash(coin.value);
            sounds.playInsertCoin();
            insertedCashLabel.setText("¥" + VendingMachine.cashInMachine);
            insertedCashLabelBig.setText(insertedCashLabel.getText());
            cashInHandLabel.setText("¥0");
        }
            clearHeldCoins();
    }

    @FXML
    private void clearHeldCoins(){
        heldCoin1.setVisible(false);
        heldCoin2.setVisible(false);
        heldCoin3.setVisible(false);
        heldCoin4.setVisible(false);
        heldCoin5.setVisible(false);
        heldCoin6.setVisible(false);
        cashInHandLabel.setText("¥0");
    }

    @FXML
    protected void on500YenClick() {
        if (available500s > 0) {
            System.out.println("You clicked the 500 endama.");
            available500s -= 1;
            setAvailableCoinLabels(available500s, available500Label);
            cashInHand.push(Coins.coin500);
            cashInHandTotal += Coins.coin500.value;
            cashInHandLabel.setText("¥" + cashInHandTotal);
            makeCoinVisible(Coins.coin500);
        }
        else{
            System.out.println("You don't have a 500 yen coin.");
        }
    }

    @FXML
    protected void on100YenClick(){
        if(available100s > 0){
            available100s -= 1;
            setAvailableCoinLabels(available100s, available100Label);
            cashInHandTotal += Coins.coin100.value;
            cashInHand.push(Coins.coin100);
            cashInHandLabel.setText("¥" + cashInHandTotal);
            makeCoinVisible(Coins.coin100);
        }
    }

    @FXML
    protected void on50YenClick(){
        if(available50s > 0){
            available50s -= 1;
            setAvailableCoinLabels(available50s, available50Label);
            cashInHandTotal += Coins.coin50.value;
            cashInHand.push(Coins.coin50);
            makeCoinVisible(Coins.coin50);
        }
    }

    @FXML
    protected void on10YenClick(){
        if(available10s > 0){
            available10s -= 1;
            setAvailableCoinLabels(available10s, available10Label);
            cashInHandTotal += Coins.coin10.value;
            cashInHand.push(Coins.coin10);
            makeCoinVisible(Coins.coin10);
        }
    }

    private void makeCoinVisible(Coins coin){
        if (heldCoin6.isVisible()){
            System.out.println("You can't hold any more.");
            // And actually do something to reflect that
        }
        else if (heldCoin5.isVisible()){
            heldCoin6.setImage(coin.image);
            heldCoin6.setFitWidth(coin.imageWidth);
            heldCoin6.setFitHeight(coin.imageHeight);
            heldCoin6.setVisible(true);
        }
        else if (heldCoin4.isVisible()){
            heldCoin5.setImage(coin.image);
            heldCoin5.setFitWidth(coin.imageWidth);
            heldCoin5.setFitHeight(coin.imageHeight);
            heldCoin5.setVisible(true);
        }
        else if (heldCoin3.isVisible()){
            heldCoin4.setImage(coin.image);
            heldCoin4.setFitWidth(coin.imageWidth);
            heldCoin4.setFitHeight(coin.imageHeight);
            heldCoin4.setVisible(true);
        }
        else if (heldCoin2.isVisible()){
            heldCoin3.setImage(coin.image);
            heldCoin3.setFitWidth(coin.imageWidth);
            heldCoin3.setFitHeight(coin.imageHeight);
            heldCoin3.setVisible(true);
        }
        else if (heldCoin1.isVisible()){
            heldCoin2.setImage(coin.image);
            heldCoin2.setFitWidth(coin.imageWidth);
            heldCoin2.setFitHeight(coin.imageHeight);
            heldCoin2.setVisible(true);
        }
        else{
            heldCoin1.setImage(coin.image);
            heldCoin1.setFitWidth(coin.imageWidth);
            heldCoin1.setFitHeight(coin.imageHeight);
            heldCoin1.setVisible(true);
        }
    }

    @FXML
    protected void add500Yen(){
        available500s += 1;
        setAvailableCoinLabels(available500s, available500Label);
    }

    @FXML
    protected void add100Yen(){
        available100s += 1;
        setAvailableCoinLabels(available100s, available100Label);
    }

    @FXML
    protected void add50Yen(){
        available50s += 1;
        setAvailableCoinLabels(available50s, available50Label);
    }

    @FXML
    protected void add10Yen(){
        available10s += 1;
        setAvailableCoinLabels(available10s, available10Label);
    }

    @FXML
    protected void onBayClicked(){
//
//        System.out.println(sodaSlot.getScaleY());
//        sodaSlot.setTranslateY(-25.0);
//        sodaSlot.setScaleY(0.30);
//        pullLabel.setTranslateY(-25.0);
//        pullLabel.setScaleY(0.30);
        bottle1.setVisible(false);
        bottle2.setVisible(false);
        bottle3.setVisible(false);
    }
}