package com.example.administrator.mytouch;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);
        root = (FrameLayout) findViewById(R.id.container);

        //获取要移动的图片
        iv = (ImageView) findViewById(R.id.iv);

        root.setOnTouchListener(new View.OnTouchListener() {

            float currentDistance;//两点之间的距离
            float lastDistance = -1;//最后的距离



            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("action down");
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("action up");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("action move");

                        if(event.getPointerCount()>=2) {
                            float offsetX = event.getX(0) - event.getX(1);
                            float offsetY = event.getY(0) - event.getY(1);

                            currentDistance = (float) Math.sqrt(offsetX * offsetX + offsetY * offsetY);

                            float width = iv.getWidth();
                            float height = iv.getHeight();
                            if(lastDistance<0){
                                lastDistance = currentDistance;
                            }else{
                                //提高容错率
                                if(currentDistance-lastDistance>5){
                                    System.out.println("放大");
                                    lastDistance = currentDistance;
                                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) iv.getLayoutParams();
                                    lp.width=(int)(1.1f*iv.getWidth());
                                    lp.height=(int)(1.1f*iv.getHeight());
                                    iv.setLayoutParams(lp);

                                }else if(lastDistance-currentDistance>5){
                                    System.out.println("缩小");
                                    lastDistance = currentDistance;
                                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) iv.getLayoutParams();
                                    lp.width=(int)(0.9f*iv.getWidth());
                                    lp.height=(int)(0.9f*iv.getHeight());
                                    iv.setLayoutParams(lp);
                                    if (lp.width==0||lp.height==0){
                                        lp.width= (int) width;
                                        lp.height= (int) height;
                                        iv.setLayoutParams(lp);
                                    }

                                }
                            }
                        }

                        //获取点击移动时的点击位置
                        //System.out.println(String.format("x:%f,y:%f", event.getX(),event.getY()));

//                        //获取焦点的数目
//                        System.out.println("焦点的数目"+event.getPointerCount());
//
//                          FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) iv.getLayoutParams();
//                        //获得x,y点的坐标位置
//                        lp.leftMargin = (int) event.getX();
//                        lp.topMargin = (int) event.getY();
//                        iv.setLayoutParams(lp );
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        System.out.println("action cancel");
                        break;
                }
                return true;
            }
        });
    }
    private FrameLayout root;
    private ImageView iv;
}
