package pt.isec.pa.tinypac.ui.gui.javafx;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import pt.isec.pa.tinypac.model.TinyPacStateMachineObservable;

public class MainPane extends BorderPane  {
    private TinyPacStateMachineObservable jogoObservavel;

    private Label tabuleiroLabel;
    private Label jogador1Label;
    private Label jogador2Label;


    public MainPane(TinyPacStateMachineObservable jogoObservavel) {
        this.jogoObservavel = jogoObservavel;

        criarVista();
        registarObservador();
        atualiza();
    }

    private void registarObservador(){
        // regista um observador do jogoObservavel
       /* jogoObservavel.addPropertyChangeListener(propriedade_avanca_estado, evt -> {
            atualiza();
        });*/
    }
 
    private void criarVista(){
/*
   *//*     setMaxSize(DIMENSAO_JANELA_X, DIMENSAO_JANELA_Y);
//        setPrefSize(DIM_X_FRAME, DIM_Y_FRAME);
        setMinSize(DIMENSAO_JANELA_X, DIMENSAO_JANELA_Y);*//*

        setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID,
                null, new BorderWidths(2))));

        // BOX TABULEIRO
        tabuleiroLabel =new Label("Tabuleiro");
        tabuleiroLabel.setFont(LETRA);
        tabuleiroLabel.setTextFill(Color.DEEPSKYBLUE);
*//*
        TabuleiroPane tabuleiroPane = new TabuleiroPane(jogoObservavel);*//*

        HBox leftBox = new HBox(10);

//        leftBox.setPrefSize(LEFT_VBOX_X, LEFT_VBOX_Y);
//        leftBox.setMinSize(LEFT_VBOX_X, LEFT_VBOX_Y);
        leftBox.setPadding(new Insets(10, 10, 10, 10));
        leftBox.setAlignment(Pos.TOP_LEFT);
        leftBox.getChildren().addAll(tabuleiroLabel, tabuleiroPane);

        // BOX JOGADORES

        jogador1Label = new Label("Jogador1");
        jogador1Label.setFont(LETRA);
        jogador1Label.setTextFill(Color.RED);

        Jogador1Pane jogador1Pane = new Jogador1Pane(jogoObservavel);

        jogador2Label = new Label("Jogador2");
        jogador2Label.setFont(LETRA);
        jogador2Label.setTextFill(Color.YELLOW);

        Jogador2Pane jogador2Pane = new Jogador2Pane(jogoObservavel);


        HBox rightBox = new HBox(10);
        rightBox.setPadding(new Insets(10, 10, 10, 10));
//        rightBox.setAlignment(Pos.TOP_RIGHT);

        rightBox.getChildren().addAll(jogador1Label, jogador1Pane,  jogador2Label, jogador2Pane);

        InfoJogoPane infoJogoPane = new InfoJogoPane(jogoObservavel);
        TimerMiniJogoPane timerMiniJogoPane = new TimerMiniJogoPane(jogoObservavel);

        // BOX COM TABULEIRO E JOGADORES
        VBox boxDireita = new VBox(10);

        boxDireita.setPadding(new Insets(10, 10, 10, 10));
        boxDireita.setAlignment(Pos.TOP_CENTER);
        boxDireita.getChildren().addAll(rightBox, leftBox);


        // BOX PARA INFO DO JOGO


        VBox infoGameBox = new VBox(10);
        infoGameBox.setPadding((new Insets(10,10,10,10)));
        infoGameBox.setAlignment(Pos.TOP_LEFT);
        infoGameBox.getChildren().addAll(infoJogoPane, timerMiniJogoPane);


        // BOX CENTRAL ENVOLVENTE CONTENDO A ESQUERDA E A DIREITA
        HBox center = new HBox(10);
        center.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID,
                null, new BorderWidths(2))));
        center.setAlignment(Pos.CENTER);
        center.getChildren().addAll(infoGameBox, boxDireita);
        setCenter(center);

        // PAINEIS DOS ESTADOS
*//*        InicioPane aguardaInicioPane = new InicioPane(jogoObservavel);
        AguardaModoJogoPane aguardaModoJogoPane = new AguardaModoJogoPane(jogoObservavel);
        NomeJogador1Pane nomeJogador1Pane = new NomeJogador1Pane(jogoObservavel);
        NomeJogador2Pane nomeJogador2Pane = new NomeJogador2Pane(jogoObservavel);
        AguardaJogadaPane aguardaJogadaPane = new AguardaJogadaPane(jogoObservavel);
        AguardaDecisaoMiniJogoPane aguardaDecisaoMiniJogoPane = new AguardaDecisaoMiniJogoPane(jogoObservavel);
        PedeResultadoMiniJogoPane pedeResultadoMiniJogoPane = new PedeResultadoMiniJogoPane(jogoObservavel);
        PedePalavraMiniJogoPane pedePalavraMiniJogoPane =  new PedePalavraMiniJogoPane(jogoObservavel);
        QuestionaUsoPecaPane questionaUsoPecaPane = new QuestionaUsoPecaPane(jogoObservavel);
        FimJogoPane fimJogoPane = new FimJogoPane(jogoObservavel);
        CarregaJogoPane carregaJogoPane = new CarregaJogoPane(jogoObservavel);
        ReplayJogoPane replayJogoPane = new ReplayJogoPane(jogoObservavel);*//*
        // STACKPANE COM OS PAINEIS DOS ESTADOS
        StackPane bottom = new StackPane(aguardaInicioPane, aguardaModoJogoPane, nomeJogador1Pane,nomeJogador2Pane,
                aguardaJogadaPane, aguardaDecisaoMiniJogoPane,pedeResultadoMiniJogoPane,questionaUsoPecaPane, pedePalavraMiniJogoPane,
                fimJogoPane, carregaJogoPane, replayJogoPane);

        bottom.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID,
                null, new BorderWidths(2))));
        bottom.setPrefSize(DIMENSAO_X_SECCAO_BOT, DIMENSAO_Y_SECCAO_BOT);
        bottom.setMinSize(DIMENSAO_X_SECCAO_BOT, DIMENSAO_Y_SECCAO_BOT);

        setBottom(bottom);*/
    }

    
    private void atualiza() {
        tabuleiroLabel.setText("Tabuleiro: ");
        jogador1Label.setText("Jogador 1: ");
        jogador2Label.setText("Jogador 2: ");
    }
}
