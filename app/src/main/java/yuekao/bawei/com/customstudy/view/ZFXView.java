package yuekao.bawei.com.customstudy.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import yuekao.bawei.com.customstudy.R;

/**
 * 类的用途：
 *
 * @author 林慧强
 * @time 2017/4/28 20:09
 */

public class ZFXView extends View {

    private int centerx;
    private int pintx;
    private int textsize;
    private String text;
    private int color;
    private int cenY;
    private int cenX;
    private float x;
    private float y;
    public OnsrolleyClickLiener listener;
    public interface  OnsrolleyClickLiener{
        void srolleyClickLiener(String text);
    }
    public void setOnCircleClickListener(OnsrolleyClickLiener listener){
        this.listener=listener;
    }

    public ZFXView(Context context) {
        super(context);
    }
    public ZFXView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }
    public ZFXView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ZFXView);
        centerx = typedArray.getInt(R.styleable.ZFXView_centerx, -1);
        pintx = typedArray.getInt(R.styleable.ZFXView_pintx, -1);
        textsize = typedArray.getInt(R.styleable.ZFXView_textsize, -1);
        text = typedArray.getString(R.styleable.ZFXView_text);
        color = typedArray.getColor(R.styleable.ZFXView_centercolor, -1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(centerx*2,centerx*2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cenX = centerx;
        cenY = centerx;
        canvas.drawColor(Color.GREEN);

        Paint paint=new Paint();
        //设置画笔的样式为空心
        paint.setStyle(Paint.Style.FILL);
       paint.setColor(color);
        //抗锯齿
        paint.setAntiAlias(true);
        //画大圆
        canvas.drawCircle(cenX,cenY,centerx,paint);

        paint.setColor(Color.WHITE);
        //画小圆
        canvas.drawCircle(cenX,cenY,pintx,paint);

        paint.setTextSize(textsize);
        paint.setColor(Color.BLACK);
        //画字
        canvas.drawText(text,cenX-text.length()/2*textsize,cenY+textsize/2,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
           if(event.getAction()==MotionEvent.ACTION_DOWN){
               x = event.getX();
               y = event.getY();
               Log.i("xxx",x+"aaaaaaa"+y);
               double sqrt = Math.sqrt((cenX - x) * (cenX - x) + (cenY - y) * (cenY - y));
               Log.i("xxx",sqrt+"");
               if(sqrt<pintx){
                   Toast.makeText(getContext(), "小圆内", Toast.LENGTH_SHORT).show();
               }else if(sqrt>pintx&&sqrt<centerx){
                   Toast.makeText(getContext(), "圆环内", Toast.LENGTH_SHORT).show();
               }else {
                   Toast.makeText(getContext(), "圆环外", Toast.LENGTH_SHORT).show();
                   listener.srolleyClickLiener("圆环外");
               }
           }
        return super.onTouchEvent(event);

    }
}
