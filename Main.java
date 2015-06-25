package br.priscilabarros.jogodavelha;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.support.v4.app.Fragment;


public class Main extends ActionBarActivity {

    //Const. da tag de cada botão
    //utilizamos essa const. para rcuperar o botão através do metodos getQuadrado
    private final String QUADRADO = "quadrado";
    // const. que vai ser impressa no text do menu
    private final String Bola     ="O";
    //const.que vai ser impressa no text do botao
    private final String Xis        = "X";

    private String lastPlay = "X";

    private View view;

    int[][] estadoFinal = new int[][]{
            {1,2,3},
            {4,5,6},
            {7,8,9},

            {1,4,7},
            {2,5,8},
            {3,6,9},

            {1,5,9},
            {3,5,7},

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(getLayoutInflater().inflate(R.layout.main,null));
        setContentView(getView());
    }

    public void clickQuadrado(View v){

        if(!isFim() ){
            if(((Button)v).getText().equals( "" )) {
                if (getLastPlay().equals(Xis)) {
                    ((Button) v).setText(Bola);
                    setLastPlay(Bola);
                } else {
                    ((Button) v).setText(Xis);
                    setLastPlay(Xis);
                }
            }else{

                Toast.makeText(getView().getContext(),"Opa! Escolha outro quadrado.",Toast.LENGTH_LONG).show();
            }

            isFim();
        }

    }

    public Button getQuadrado(int tagNum){
        return (Button)getView().findViewWithTag( QUADRADO+tagNum );
    }

    public void newGame(View v){

        setEnableQuadrado(true);
        setColorBlack();

        for(int i =1; i<=9; i++){
            if(getQuadrado(i)!= null){
                getQuadrado(i).setText("");
            }
        }
    }

    public void setEnableQuadrado(boolean b){
        for(int i=1;i<=9;i++){
            if(getQuadrado(i)!=null){
                getQuadrado(i).setEnabled(b);
            }
        }
    }

    public void setColorQuadrados(int btn, int colorX){
        getQuadrado(btn).setTextColor(getResources().getColor(colorX));
    }

    public void setColorBlack(){
        for(int i=0; i<=9; ++i){
            if(getQuadrado(i)!=null){
                setColorQuadrados(i,R.color.material_blue_grey_950);
            }
        }
    }

    public boolean isFim(){
        for(int x=0; x<=7; ++x){
            String s1 = getQuadrado( estadoFinal[x][0] ).getText().toString();
            String s2 = getQuadrado( estadoFinal[x][1] ).getText().toString();
            String s3 = getQuadrado( estadoFinal[x][2] ).getText().toString();


            if( (!s1.equals("") )  &&
                (!s2.equals("") )  &&
                (!s3.equals("") ) ) {

                if (s1.equals(s2) && (s2.equals(s3))) {
                    setColorQuadrados( estadoFinal[x][0], R.color.material_deep_teal_500);
                    setColorQuadrados( estadoFinal[x][1], R.color.material_deep_teal_500);
                    setColorQuadrados( estadoFinal[x][2], R.color.material_deep_teal_500);
                    Toast.makeText(getView().getContext(), "Congratulations You Win", Toast.LENGTH_LONG).show();

                    return true;
                }
            }

        }

        return false;
    }

    public View getView(){
        return view;
    }

    public void setView(View view){
        this.view = view;
    }

    public String getLastPlay(){
        return lastPlay;
    }

    public void setLastPlay(String lastPlay){
        this.lastPlay = lastPlay;
    }

}
