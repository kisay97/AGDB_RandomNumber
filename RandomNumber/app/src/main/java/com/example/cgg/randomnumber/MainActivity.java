package com.example.cgg.randomnumber;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //전역변수 선언 영역
    Random rand = new Random();         // 난수 클래스
    int count = 0;                      // 사용자 입력 횟수
    int num = 0;                        // 난수

    EditText userInputEditText;       // 입력 위젯
    TextView textCount, textResult;   // 사용자 입력 횟수 출력, 판정 결과 출력

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //초기화 부분
        //Listener 설정
        findViewById(R.id.fab).setOnClickListener(onClickListener);
        findViewById(R.id.confirmButton).setOnClickListener(onClickListener);

        //위젯 저장
        userInputEditText = (EditText) findViewById(R.id.userInputEditText);
        textCount = (TextView) findViewById(R.id.textCount);
        textResult = (TextView) findViewById(R.id.textResult);

        //게임 초기화
        initGame();
    }

    //프로그램에 필요한 함수
    Button.OnClickListener onClickListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab : //FloatingActionButton 처리(게임 초기화)
                    initGame();
                    break;

                case R.id.confirmButton : //confirmButton 처리(정답 확인후 위젯 처리)
                    checkValue();
                    break;
            }
        }
    };

    private void initGame() {
        num = rand.nextInt(1000) + 1; //1~1000 사이의 난수
        count = 0; //사용자 입력 횟수 초기화
        clearFields(); //위젯 처리
    }

    private void clearFields() {
        userInputEditText.setText(""); //아무것도 입력되지 않은 상태로 변경
        textCount.setText("입력횟수 : " + count); //입력횟수 출력
        textResult.setText(""); //판정결과는 checkValue에서 따로 처리함
    }

    private void checkValue() {
        //입력 받은 값 읽기
        String str = userInputEditText.getText().toString();

        //빈문자열인지 판단
        if (str.equals("")) {
            Toast.makeText(this, "정답을 입력해주세요!", Toast.LENGTH_SHORT).show();
            return;
        }

        //입력값 정수로 변환
        int input = Integer.parseInt(str);

        //난수 범위의 수인지 판단
        if (input < 0 || input > 1000) {
            Toast.makeText(this, "범위대로 입력해주세요!", Toast.LENGTH_SHORT).show();
            return;
        }

        //입력횟수++
        count++;

        //위젯처리
        clearFields();

        //정답 여부 판단
        if (input == num) {
            textResult.setText("정답");
        } else if(input < num) {
            textResult.setText("정답보다 작음");
        } else {
            textResult.setText("정답보다 큼");
        }
    }
}
