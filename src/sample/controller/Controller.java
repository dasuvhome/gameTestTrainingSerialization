package sample.controller;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.model.Player;

public class Controller implements Serializable {

    @FXML
    private ResourceBundle resources;
    @FXML
    private Button btn_clear1;

    @FXML
    private URL location;

    @FXML
    private Label text_name_player;

    @FXML
    private Label text_name_bot;

    @FXML
    private Button btn_fight;

    @FXML
    private Label txt_score_player;

    @FXML
    private Label txt_score_bot;

    @FXML Button  btn_exit;



    @FXML
    private Label text_wins_result;
    int scorePlayer;
    int scoreBot;
    int fightTemt;

    @FXML
    void initialize() throws IOException {
        loadScore();
        txt_score_player.setText("" + scorePlayer);
        txt_score_bot.setText("" + scoreBot);
         loadScore();


        btn_fight.setOnAction(event -> {
            fightTemt = fightRandom();
        if(fightTemt%2 ==0 && fightTemt>0){
            text_wins_result.setText("Вы выиграли");
            scorePlayer++;
            txt_score_player.setText("" + scorePlayer);
        }else{
            text_wins_result.setText("Бот выиграл");
        scoreBot++;
        txt_score_bot.setText("" + scoreBot);
        }
    }
        );

        btn_exit.setOnAction(event -> {

                    try {
                        writeScore();
                  Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                  stage.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


        );
        btn_clear1.setOnAction(event ->{
            clearResult();
        });
    }


    private void loadScore() throws IOException {
        File file = new File("score.txt");
        if(file.exists()){
            FileInputStream ios = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(ios);
            scorePlayer = ois.read();
            scoreBot = ois.read();
            ois.close();
        }


        }

      private int fightRandom(){
        Random random = new Random();
        int fight = random.nextInt(1000);

        return fight;
    }


    private void writeScore() throws IOException {
        FileOutputStream fos = new FileOutputStream("score.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.write(scorePlayer);
        oos.write(scoreBot);
        oos.close();
    }

        public void  clearResult(){
        scorePlayer = 0;
        scoreBot = 0;
        txt_score_player.setText(""+scorePlayer);
        txt_score_bot.setText(""+scoreBot);
        }


}
