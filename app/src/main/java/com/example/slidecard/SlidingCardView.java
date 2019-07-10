package com.example.slidecard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

/**
  * @class name：${PACKAGE_NAME}
  * @class describe
  * @author zll
  * @time ${DATE} ${TIME}
  * @change
  * @chang time
  * @class describe
  */
@CoordinatorLayout.DefaultBehavior(SlidingCardBehavior.class)
public class SlidingCardView extends FrameLayout {

    private int headerHeight;

    public SlidingCardView(Context context) {
        this(context, null);
    }

    public SlidingCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(final Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.view_slidingcard, this);
        TextView textView = (TextView) findViewById(R.id.tv);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SlidingCardView, defStyleAttr, 0);
        int color = array.getColor(R.styleable.SlidingCardView_card_color, Color.GREEN);
        String text = array.getString(R.styleable.SlidingCardView_card_text);
        array.recycle();

        textView.setText(text);
        textView.setBackgroundColor(color);

        SingleAdapter adapter = new SingleAdapter(context);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view,
                                       RecyclerView parent, RecyclerView.State state) {
                final int position = parent.getChildViewHolder(view).getAdapterPosition();
                final int offset = parent.getResources()
                        .getDimensionPixelOffset(R.dimen.activity_vertical_margin);
                outRect.set(offset,
                        position == 0 ? offset : 0,
                        offset,
                        offset);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        adapter.setData(getData());
    }

    private List<String> getData() {
        List<String> data = new ArrayList<>();
        data.add("Android");
        data.add("Java");
        data.add("Web");
        data.add("PHP");
        data.add("Python");
        data.add("Html");
        data.add("Css");
        data.add("Js");
        return data;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw || h != oldh) {
            headerHeight = findViewById(R.id.tv).getMeasuredHeight();
        }
    }

    public int getHeaderHeight() {
        return headerHeight;
    }

}
