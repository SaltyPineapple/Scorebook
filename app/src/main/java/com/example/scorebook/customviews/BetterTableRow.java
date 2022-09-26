package com.example.scorebook.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TableRow;

import com.example.scorebook.R;

public class BetterTableRow extends TableRow {

    public int rowColorEnum;
    public int columns;
    public int rowColor;

    public Paint paint = new Paint();

    public BetterTableRow(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.BetterTableRow,
                0,0);

        try {
            rowColorEnum = a.getInteger(R.styleable.BetterTableRow_rowColor, 0);
            columns = a.getInteger(R.styleable.BetterTableRow_columns, 0);
        }
        finally {
            a.recycle();
        }

        if(rowColorEnum == 1)
            rowColor = Color.BLUE;

        paint.setColor(rowColor);
        //paint.setStyle(style);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0,0,200,200,  paint);
    }

    public int getRowColor(){
        return rowColorEnum;
    }

    public void setRowColor(int colorEnum){
        rowColorEnum = colorEnum;
        invalidate();
        requestLayout();
    }

    public int getColumns(){
        return this.columns;
    }

    public void setColumns(int columns){
        this.columns = columns;
        invalidate();
        requestLayout();
    }



}
