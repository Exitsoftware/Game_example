package com.exitsoft.game_example;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {
    
    // queue라는 이름의 Integer ArrayList 선언하기
    // your code!
    ArrayList<Integer> queue = new ArrayList<Integer>();
    ArrayList<ImageButton> btnList = new ArrayList<ImageButton>();

    ImageButton btn1;
    ImageButton btn2;
    ImageButton btn3;
    ImageButton btn4;
    ImageButton btn5;

    int score = 0;
    int combo = 0;
    
    TextView scoreView;
    TextView comboView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreView = (TextView) findViewById(R.id.scoreView);
        comboView = (TextView) findViewById(R.id.comboView);

        btn1 = (ImageButton) findViewById(R.id.imageButton);
        btn2 = (ImageButton) findViewById(R.id.imageButton2);
        btn3 = (ImageButton) findViewById(R.id.imageButton3);
        btn4 = (ImageButton) findViewById(R.id.imageButton4);
        btn5 = (ImageButton) findViewById(R.id.imageButton5);

        btnList.add(btn1);
        btnList.add(btn2);
        btnList.add(btn3);
        btnList.add(btn4);
        btnList.add(btn5);


        // 난수 5개를 ArrayList에 추가하기 (단, 난수는 0~3 까지의 정수) (Math.random 함수 사용하기)
        // your code!
        
        for(int i = 0; i < 5; i++){
            int rndInt = (int)(Math.random() * 4);
            queue.add(rndInt);
        }        

        refreshImage();


    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        ImageButton btn1 = (ImageButton) findViewById(R.id.imageButton2);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        int action = event.getAction();


        if(action == MotionEvent.ACTION_DOWN){
            int rndInt = (int) (Math.random()*4);
            
            if(event.getX() < width){
                if(queue.get(0)%2 == 0){
                    score += 100;
                    combo++;
                }
                else{
                    score = 0;
                    combo = 0;
                }
            }
            else{
                if(queue.get(0)%2 == 1){
                    score += 100;
                    combo++;
                }
                else{
                
                    score = 0;
                    combo = 0;
                }
            }
            // 왼쪽 오른쪽 나눠서 맞으면 100점 추가 콤보 추가 아니면, -50점 콤보 0
            // your code!

            // queue의 0번째 값을 지우고, 맨 뒤에 난수를 add시키기.
            // your code!
            
            
            queue.remove(0);
            int rndInt = (int) (Math.random() * 4);
            queue.add(rndInt);
            refreshImage();



        }

        return super.onTouchEvent(event);
    }

    public void refreshImage(){
        for(int i = 0; i < btnList.size(); i++){
            int rndInt = queue.get(i);
            ImageButton curBtn = btnList.get(i);
            
            
           // Your Code!
        }
        if(combo == 0) comboView.setVisibility(View.INVISIBLE);
        else {
            comboView.setVisibility(View.VISIBLE);
            comboView.setText(combo + " Combo!");
        }
        scoreView.setText("Your Score\n"+ score +"점");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    
     private void saveHighScore(){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("highScore", String.valueOf(score));
        editor.commit();
    }

    private String getHighScore(){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        return pref.getString("highScore", "NULL").toString();
    }
}
