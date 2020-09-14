package com.je1224.clickclick;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    LinearLayout ln;
    TextView tv;
    ImageView iv;

    ImageView[] imgs = new ImageView[12];

    int cnt = 0;
    int stage = 1;

    Random rnd = new Random();
    int[] arr = new int[12];

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random();

        ln = findViewById(R.id.ln);
        tv = findViewById(R.id.tv);
        iv = findViewById(R.id.iv);
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = findViewById(R.id.img01 + i);
            imgs[i].setImageResource(R.drawable.num01+arr[i]);
            imgs[i].setTag(arr[i]);
        }


    }

    // 랜덤값 만들기
    public void Random() {
        for (int i = 0; i < 12; i++) {
            arr[i] = rnd.nextInt(12);
            for (int k = 0; k < i; k++) {
                if (arr[i] == arr[k]) {
                    i--;
                    break;
                }
            }
        }
    }

    // start 버튼 누르면
    public void clickBtn(View v) {
        tv.setText("숫자를 순서대로 누르세요.");
        iv.setImageResource(R.drawable.ing);
        for (int i = 0; i < imgs.length; i++) {
            imgs[i].setVisibility(View.VISIBLE);
            imgs[i].isClickable();
        }
    }

    // imgs 누르면
    public void select(View v) {
        String tag = v.getTag().toString();
        int num = Integer.parseInt(tag);

        if (num == cnt) {
            v.setVisibility(View.INVISIBLE);
            cnt++;

            if (cnt >= 12) {
                stageChange();
                cnt = 0;
            }
        }
    }

    // 스테이지 변경하기
    public void stageChange() {
        stage++;

        // 2단계
        Random();
        if (stage == 2) {
            ln.setBackgroundResource(R.drawable.bg2);
            tv.setText("알파벳 순서대로 누르세요.");
            for (int i = 0; i < 12; i++) {
                imgs[i].setVisibility(View.VISIBLE);
                imgs[i].setImageResource(R.drawable.alpa01 + arr[i]);
                imgs[i].setTag(arr[i]);
            }
        }

        // 3단계
        Random();
        if (stage == 3) {
            ln.setBackgroundResource(R.drawable.bg3);
            tv.setText("십이지신 순서대로 누르세요.");
            for (int i = 0; i < 12; i++) {
                imgs[i].setVisibility(View.VISIBLE);
                imgs[i].setImageResource(R.drawable.cha01 + arr[i]);
                imgs[i].setTag(arr[i]);
            }
        }

        // 게임 끝
        if(stage>3){
            ln.setBackgroundResource(R.drawable.bg4);
            tv.setVisibility(View.GONE);
            findViewById(R.id.iv).setVisibility(View.GONE);
            return;
        }
    }
}

