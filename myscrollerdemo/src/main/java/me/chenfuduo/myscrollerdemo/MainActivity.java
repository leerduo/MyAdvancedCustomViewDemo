package me.chenfuduo.myscrollerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class MainActivity extends AppCompatActivity {

    /** Called when the activity is first created. */

    LinearLayout lay1,lay2,lay;

    private Scroller mScroller;

    private boolean s1,s2;

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mScroller = new Scroller(this);

        lay1 = new LinearLayout(this){

            @Override

            public void computeScroll() {

                if (mScroller.computeScrollOffset()) {

                    scrollTo(mScroller.getCurrX(), 0);

                    postInvalidate();

                }

            }

        };

        lay2 = new LinearLayout(this){

            @Override

            public void computeScroll() {

                if (mScroller.computeScrollOffset()) {

                    // mScrollX = mScroller.getCurrX();

                    scrollTo(mScroller.getCurrX(), 0);

                    postInvalidate();

                }

            }

        };

        lay1.setBackgroundColor(this.getResources().getColor(android.R.color.darker_gray));

        lay2.setBackgroundColor(this.getResources().getColor(android.R.color.white));

        lay = new LinearLayout(this);

        lay.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams p0 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT);

        this.setContentView(lay, p0);



        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT);

        p1.weight=1;

        lay.addView(lay1,p1);

        LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT);

        p2.weight=1;

        lay.addView(lay2,p2);

        Button tx = new Button(this);

        Button tx2 = new Button(this);

        tx.setText("Button1");

        tx2.setText("Button2");

        tx.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View v) {

                if(!s1){

                    mScroller.startScroll(0, 0, 5, 10, 10);

                    s1 = true;

                }else{

                    mScroller.startScroll(0, 0, -50, -10,10);

                    s1 = false;

                }

            }



        });

        tx2.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View v) {

                if(!s2){

                    mScroller.startScroll(0, 0, 5, 20,10);

                    s2=true;

                }else{

                    mScroller.startScroll(20, 20, -50, -20,10);

                    s2=false;

                }

            }

        });

        lay1.addView(tx);

        lay2.addView(tx2);

    }

}