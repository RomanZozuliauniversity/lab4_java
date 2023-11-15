package ua.lpnu.lab4_java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ua.lpnu.lab4_java.methods.Akm;
import ua.lpnu.lab4_java.methods.Distribution;
import ua.lpnu.lab4_java.methods.Mkm;
import ua.lpnu.lab4_java.methods.Zkm;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        stage.setResizable(false);
        stage.setTitle("Лабораторна робота №4 Дикий Дмитро КН-303");
        // ====================
        Scene scene = new Scene(table(), 900, 700);
        stage.setScene(scene);
        // ====================
        stage.getIcons().add(new Image("file:src/main/resources/images/lab4.png"));
        akm.setOnAction(actionEvent -> akmButtonClick());
        mkm.setOnAction(actionEvent -> mkmButtonClick());
        zkm.setOnAction(actionEvent -> zkmButtonClick());
        stage.show();
    }

    private static TextField N = new TextField();
    private static TextField X0 = new TextField();
    private static TextField X1 = new TextField();
    private static TextField A = new TextField();
    private static TextField C = new TextField();
    private static TextField B = new TextField();
    private static CheckBox checkBoxPeriod = new CheckBox("З врахуванням періоду");
    private static Button akm = new Button("Адитивний\nконгруентний метод");
    private static Button mkm = new Button("Мультиплікативний\nконгруентний метод");
    private static Button zkm = new Button("Змішаний\nконгруентний метод");
    private static TextArea resultArea = new TextArea("");
    private static BarChart bc;
    private static XYChart.Series ds;
    public static FlowPane table(){
        resultArea.setEditable(false);
        // txt fields
        Label labelN = new Label("N  ");
        labelN.setLabelFor(N);
        Label labelX0 = new Label("X0");
        labelX0.setLabelFor(X0);
        Label labelX1 = new Label("X1");
        labelX1.setLabelFor(X1);
        Label labelA = new Label("A  ");
        labelA.setLabelFor(A);
        Label labelC = new Label("C  ");
        labelC.setLabelFor(C);
        Label labelB = new Label("B  ");
        labelB.setLabelFor(B);
        N.setPrefWidth(60);
        X0.setPrefWidth(60);
        X1.setPrefWidth(60);
        A.setPrefWidth(60);
        C.setPrefWidth(60);
        B.setPrefWidth(60);
        FlowPane rootTemp2 = new FlowPane(Orientation.HORIZONTAL, 10, 15);
        FlowPane rootTemp3 = new FlowPane(Orientation.HORIZONTAL, 10, 15);
        rootTemp2.getChildren().addAll(labelN, N,labelX0,X0,labelX1,X1);
        rootTemp3.getChildren().addAll(rootTemp2
                ,labelA,A,labelC,C,labelB,B);

        // buttons
        FlowPane rootTemp4 = new FlowPane(Orientation.HORIZONTAL, 10, 10);
        rootTemp4.getChildren().addAll(akm, mkm, zkm);
        zkm.setTranslateY(-52);
        zkm.setTranslateX(280);

        // bar chart
        bc = new BarChart(x, y);
        bc.setPrefWidth(150);
        bc.setPrefHeight(300);
        ds = new XYChart.Series();

        FlowPane root = new FlowPane(Orientation.VERTICAL, 22, 22);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(rootTemp3,checkBoxPeriod,rootTemp4,bc,resultArea);
        resultArea.setPrefRowCount(17);
        resultArea.setPrefColumnCount(25);
        resultArea.setTranslateY(110);
        return root;
    }

    private static void akmButtonClick(){
        if (!checkBoxPeriod.isSelected()) {
            Akm m = new Akm(Integer.parseInt(X0.getText()), Integer.parseInt(X1.getText()),
                    Integer.parseInt(B.getText()), Integer.parseInt(N.getText()), false);
            List<Double> arr = m.getArray();
            Distribution dis = new Distribution(arr);

            resultArea.setText("Адитивний конгруентний метод:\n");
            resultArea.appendText("Без врахування періоду\n");
            chartCreate(dis, arr.size());
            showInfo(dis, checkBoxPeriod.isSelected(), m.getCountIter());
        }
        else {
            Akm m = new Akm(Integer.parseInt(X0.getText()), Integer.parseInt(X1.getText()), 16, 0, true);
            List<Double> arr = m.getArray();
            Distribution dis = new Distribution(arr);

            resultArea.setText("Адитивний конгруентний метод:\n");
            resultArea.appendText("З врахування періоду\n");
            chartCreate(dis, arr.size());
            showInfo(dis, checkBoxPeriod.isSelected(), m.getCountIter());
        }
    }

    private static void mkmButtonClick(){
        if (!checkBoxPeriod.isSelected()) {
            Mkm m = new Mkm(Integer.parseInt(X0.getText()), Integer.parseInt(A.getText()),
                    Integer.parseInt(B.getText()), Integer.parseInt(N.getText()), false);
            List<Double> arr = m.getArray();
            Distribution dis = new Distribution(arr);

            resultArea.setText("Мультиплікативний конгруентний метод:\n");
            resultArea.appendText("Без врахування періоду\n");
            chartCreate(dis, arr.size());
            showInfo(dis, checkBoxPeriod.isSelected(), m.getCountIter());
        }
        else {
            Mkm m = new Mkm(Integer.parseInt(X0.getText()), Integer.parseInt(A.getText()), 16, 0, true);
            List<Double> arr = m.getArray();
            Distribution dis = new Distribution(arr);

            resultArea.setText("Мультиплікативний конгруентний метод:\n");
            resultArea.appendText("З врахування періоду\n");
            chartCreate(dis, arr.size());
            showInfo(dis, checkBoxPeriod.isSelected(), m.getCountIter());
        }
    }

    private static void zkmButtonClick(){
        if (!checkBoxPeriod.isSelected()) {
            Zkm m = new Zkm(Integer.parseInt(X0.getText()), Integer.parseInt(A.getText()),
                    Integer.parseInt(C.getText()), Integer.parseInt(B.getText()),
                    Integer.parseInt(N.getText()), false);
            List<Double> arr = m.getArray();
            Distribution dis = new Distribution(arr);

            resultArea.setText("Змішаний конгруентний метод:\n");
            resultArea.appendText("Без врахування періоду\n");
            chartCreate(dis, arr.size());
            showInfo(dis, checkBoxPeriod.isSelected(), m.getCountIter());
        }
        else {
            Zkm m = new Zkm(Integer.parseInt(X0.getText()), Integer.parseInt(A.getText()),
                    Integer.parseInt(C.getText()), 16,
                    0, true);
            List<Double> arr = m.getArray();
            Distribution dis = new Distribution(arr);

            resultArea.setText("Змішаний конгруентний метод:\n");
            resultArea.appendText("З врахування періоду\n");
            chartCreate(dis, arr.size());
            showInfo(dis, checkBoxPeriod.isSelected(), m.getCountIter());
        }
    }

    private static CategoryAxis x = new CategoryAxis();
    private static NumberAxis y = new NumberAxis();
    private static void chartCreate(Distribution dis, int countNumbers) {
        List<Double> intervals = dis.getIntervals();
        List<Integer> countElem = dis.getCountElementOnInterval();

        bc.getData().clear();
        bc.layout();
        ds = new XYChart.Series();

        for(int i = 0; i < intervals.size()- 1; i++) {
            ds.getData().add(new XYChart.Data("" + new DecimalFormat("#.#####").format(intervals.get(i)),
                    countElem.get(i) / (double) countNumbers));
        }
        bc.getData().add(ds);

//        for(int i = 0; i < intervals.Count- 1; i++)
//        {
//            chart1.Series["Histogram"].Points.AddXY("" + Math.Round(intervals[i], 5), countElem[i] / (double)countNumbers);
//        }
    }

    private static void showInfo(Distribution dis, boolean check, int countIter) {
        List<Double> intervals = dis.getIntervals();
        List<Integer> countElem = dis.getCountElementOnInterval();

        for (int i = 0; i < intervals.size() - 1; i++) {
            resultArea.appendText("На інтервалі: " + new DecimalFormat("#.###").format(intervals.get(i)) +
                    "..." + new DecimalFormat("#.###").format(intervals.get(i+1)));
            resultArea.appendText(" знаходиться: " + countElem.get(i) + " згенерованих чисел\n");
        }
        resultArea.appendText("Кількість інтервалів: " + intervals.size());
        if (check) {
            if (countIter % 100000 == 0)
                System.out.println(countIter);
            resultArea.appendText("\nПеріод генерування випадкових чисел: " + countIter);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}