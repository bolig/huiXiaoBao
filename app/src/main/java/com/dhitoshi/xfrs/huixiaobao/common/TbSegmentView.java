package com.dhitoshi.xfrs.huixiaobao.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.dhitoshi.xfrs.huixiaobao.R;

public class TbSegmentView extends View{
	public static int WRAP = 0;
	public static int EQUAL = 1;
	private Paint paint = new Paint();
	private RectF rectF = new RectF();	
	//属性
	private int padding;
	private int paddingLeft;
	private int paddingTop;
	private int paddingRight;
	private int paddingBottom;
	private int cornerRadius;//圆角半径
	private int border;
	private int textSize;
	private String[] buttonTexts;
	private String[] buttonValues;
	private int selectedColor;
	private int backgroundColor;
	private int style;//模式，WRAP时，每个button的宽度自适应，EQUAL时，每个buttong的宽度都一致
	//工作参数
	private int selectedIndex = 1;//当前选中的
	private OnSelectedListener onSelectedListener;
	
	public TbSegmentView(Context context) {
		super(context);
	}
	public TbSegmentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.tbsegmentview);
		padding = (int)a.getDimension(R.styleable.tbsegmentview_padding, dip2px(this.getContext(), 6));
		paddingLeft = (int)a.getDimension(R.styleable.tbsegmentview_paddingLeft, padding);
		paddingTop = (int)a.getDimension(R.styleable.tbsegmentview_paddingTop, padding);
		paddingRight = (int)a.getDimension(R.styleable.tbsegmentview_paddingRight, padding);
		paddingBottom = (int)a.getDimension(R.styleable.tbsegmentview_paddingBottom, padding);
		
		cornerRadius = (int)a.getDimension(R.styleable.tbsegmentview_cornerRadius, dip2px(this.getContext(), 4));
		
		border = (int)a.getDimension(R.styleable.tbsegmentview_border, dip2px(this.getContext(), 1));
		
		textSize = (int)a.getDimension(R.styleable.tbsegmentview_textSize, dip2px(this.getContext(), 18));
		
		String texts = a.getString(R.styleable.tbsegmentview_buttonTexts);
		buttonTexts = texts!=null && !texts.trim().equals("") ? texts.split(",") : null;
		String values = a.getString(R.styleable.tbsegmentview_buttonValues);
		buttonValues = values!=null && !values.trim().equals("") ? values.split(",") : null;
		buttonValues = buttonValues == null ? buttonTexts : buttonValues;
		
		style = a.getInt(R.styleable.tbsegmentview_style, WRAP);
		style = style != WRAP && style != EQUAL ? WRAP : style;
		
		selectedColor = (int)a.getColor(R.styleable.tbsegmentview_selectedColor, 0);
		if(selectedColor == 0)
			selectedColor = a.getResourceId(R.styleable.tbsegmentview_selectedColor, 0);
		if(selectedColor == 0)
			selectedColor = 0xFF5F646E;
		
		backgroundColor = (int)a.getColor(R.styleable.tbsegmentview_backgroundColor, 0);
		if(backgroundColor == 0)
			backgroundColor = a.getResourceId(R.styleable.tbsegmentview_backgroundColor, 0);
		if(backgroundColor == 0)
			backgroundColor = 0xFFFFFFFF;
	}
	/**
	 * 测绘
	 */
	@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }	
	/**
     * 计算组件宽度
     */
    private int measureWidth(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
        	result = getDefaultWidth();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
    /**
     * 计算组件高度
     */
    private int measureHeight(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = getDefaultHeight();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
    /**
     * 计算默认宽度
     */
    private int getDefaultWidth(){
    	if(buttonTexts == null || buttonTexts.length == 0)
    		return 0;
    	paint.setTextSize(this.textSize);
    	if(this.style == WRAP){//wrap 模式
    		int totalWidth = 0;
    		for(String text : this.buttonTexts){
    			int txtWidth = (int)this.paint.measureText(text);
    			totalWidth += (txtWidth + this.paddingLeft + this.paddingRight);
    		}
    		totalWidth += ((buttonTexts.length+1) * this.border);
    		return totalWidth;
    	}
    	//equal模式
    	int itemWidth = 0;
    	for(String text : this.buttonTexts){
			int txtWidth = (int)this.paint.measureText(text);
			itemWidth = txtWidth>itemWidth ? txtWidth : itemWidth;
		}
    	itemWidth += (this.paddingLeft + this.paddingRight);
    	int totalWidth = itemWidth*buttonTexts.length + (buttonTexts.length+1) * this.border;
    	return totalWidth;
    }
    /**
     * 计算默认宽度
     */
    private int getDefaultHeight(){
    	if(buttonTexts == null || buttonTexts.length == 0)
    		return 0;
    	paint.setTextSize(this.textSize);
    	FontMetricsInt fontMetrics = paint.getFontMetricsInt();
    	int txtHeight = fontMetrics.bottom - fontMetrics.ascent;
    	int itemHeight = txtHeight + this.paddingTop + this.paddingBottom + this.border * 2;
    	return itemHeight;
    }
    @Override
	protected void onDraw(Canvas canvas) {
//		super.onDraw(canvas);
//		paint.setColor(0xFF00FF33);
//		rectF.left = 0;
//		rectF.right = 0;
//		rectF.top = 0;
//		rectF.bottom = this.getHeight();
//		canvas.drawRect(rectF, paint);
		
		if(buttonTexts == null || buttonTexts.length == 0)
    		return ;
    	if(this.style == WRAP){//wrap 模式
			drawButtonByWrap(canvas);
			return ;
		}
    	drawButtonByEqual(canvas);//equal模式
	}
    /**
     * 按wrap模式画button
     */
    private void drawButtonByWrap(Canvas canvas){
    	paint.setTextSize(this.textSize);
    	FontMetricsInt fontMetrics = paint.getFontMetricsInt();
    	int txtHeight = fontMetrics.bottom - fontMetrics.ascent;
    	//计算可用区域的总宽度
    	int usedTotelWidth = 0;
    	usedTotelWidth += (this.buttonTexts.length+1)*this.border;
    	for(int i = 0; i<this.buttonTexts.length; i++){
			String text = this.buttonTexts[i];
			int txtWidth = (int)this.paint.measureText(text);
			usedTotelWidth += txtWidth;
			usedTotelWidth += (this.paddingLeft+this.paddingRight);
    	}
    	//画组件外围border
    	rectF.left = 1;
    	rectF.top = 1 ;
    	rectF.bottom = this.getHeight()-1;
    	rectF.right = usedTotelWidth-1;
    	paint.setAntiAlias(true);
    	paint.setColor(this.backgroundColor);    	
    	canvas.drawRoundRect(rectF, this.cornerRadius, this.cornerRadius, paint);	
    	paint.setColor(this.selectedColor);  
    	paint.setStyle(Paint.Style.STROKE);
    	paint.setStrokeWidth(this.border);
    	canvas.drawRoundRect(rectF, this.cornerRadius, this.cornerRadius, paint);
    	
    	//画组件button
    	paint.setStyle(Paint.Style.FILL);    	
    	int left = this.border;
		for(int i = 0; i<this.buttonTexts.length; i++){
			String text = this.buttonTexts[i];
			int txtWidth = (int)this.paint.measureText(text);
			rectF.left = left;
			rectF.right = rectF.left + this.paddingLeft + txtWidth + this.paddingRight;
			//计算背景区域
			if(this.selectedIndex == i){				
				paint.setColor(this.selectedColor);
				if(i == 0){//第一个button
					Path path = new Path();
					rectF.left = 1;
					path.addRoundRect(rectF, new float[]{this.cornerRadius,this.cornerRadius,0f,0f,0f,0f,this.cornerRadius,this.cornerRadius}, Path.Direction.CW);
					canvas.drawPath(path,paint);
				}else if(i == buttonTexts.length-1){//最后一个花圆角
					Path path = new Path();
					rectF.right = usedTotelWidth;
					path.addRoundRect(rectF, new float[]{0f,0f,this.cornerRadius,this.cornerRadius,this.cornerRadius,this.cornerRadius,0f,0f}, Path.Direction.CW);
					canvas.drawPath(path,paint);
				}else{
					canvas.drawRect(rectF, paint);	
				}
			}	
			//画文字
			int textColor = this.selectedIndex == i ? 0xFFFFFFFF : this.selectedColor;
			paint.setColor(textColor);
			canvas.drawText(text, left+this.paddingLeft, (this.getHeight()-txtHeight)/2-fontMetrics.ascent, paint);	
			if(i == buttonTexts.length-1)
				return ;
			//画border
			paint.setColor(this.selectedColor);
			rectF.left = rectF.right;
			rectF.right = rectF.left + this.border;
			canvas.drawRect(rectF, paint);	
			left = (int)rectF.right;
		}
    }
    /**
     * 按Equal模式画button
     */
    private void drawButtonByEqual(Canvas canvas){
    	paint.setTextSize(this.textSize);
    	//画组件外围border
    	rectF.left = 1;
    	rectF.top = 1 ;
    	rectF.bottom = this.getHeight()-1;
    	rectF.right = this.getWidth()-1;
    	paint.setAntiAlias(true);
    	paint.setColor(this.backgroundColor);    	
    	canvas.drawRoundRect(rectF, this.cornerRadius, this.cornerRadius, paint);	
    	paint.setColor(this.selectedColor);  
    	paint.setStyle(Paint.Style.STROKE);
    	paint.setStrokeWidth(this.border);
    	canvas.drawRoundRect(rectF, this.cornerRadius, this.cornerRadius, paint);
    	
    	//画组件button
    	paint.setStyle(Paint.Style.FILL);		
    	FontMetricsInt fontMetrics = paint.getFontMetricsInt();
    	int txtHeight = fontMetrics.bottom - fontMetrics.ascent;
    	//计算button的宽度
    	int itemWidth = getButtonWidthByEqual();
    	
    	int left = this.border;
		for(int i = 0; i<this.buttonTexts.length; i++){
			String text = this.buttonTexts[i];
			int txtWidth = (int)this.paint.measureText(text);
			rectF.left = left;
			rectF.right = rectF.left + this.paddingLeft + itemWidth + this.paddingRight;
			//计算背景区域
			if(this.selectedIndex == i){				
				paint.setColor(this.selectedColor);
				if(i == 0){//第一个button
					Path path = new Path();//最后一个画圆角的
					rectF.left = 1;
					path.addRoundRect(rectF, new float[]{this.cornerRadius,this.cornerRadius,0f,0f,0f,0f,this.cornerRadius,this.cornerRadius}, Path.Direction.CW);
					canvas.drawPath(path,paint);
				}else if(i == buttonTexts.length-1){//最后一个花圆角
					Path path = new Path();//最后一个画圆角的
					rectF.right = this.getWidth()-1;
					path.addRoundRect(rectF, new float[]{0f,0f,this.cornerRadius,this.cornerRadius,this.cornerRadius,this.cornerRadius,0f,0f}, Path.Direction.CW);
					canvas.drawPath(path,paint);
				}else{
					canvas.drawRect(rectF, paint);	
				}
			}	
			//画文字
			int textColor = this.selectedIndex == i ? 0xFFFFFFFF : this.selectedColor;
			paint.setColor(textColor);
			canvas.drawText(text, rectF.left+((rectF.right-rectF.left)/2-txtWidth/2), (this.getHeight()-txtHeight)/2-fontMetrics.ascent, paint);	
			if(i == buttonTexts.length-1)
				return ;
			//画border
			paint.setColor(this.selectedColor);
			rectF.left = rectF.right;
			rectF.right = rectF.left + this.border;
			canvas.drawRect(rectF, paint);	
			left = (int)rectF.right;
		}
    }
    /**
     * 获得equal模式下button的宽度，不包含padding
     */
    private int getButtonWidthByEqual(){
    	int itemWidth = 0;
    	int buttonCount = this.buttonTexts.length;
    	int totalBorder = (buttonCount+1) * this.border;
    	int totalPadding = (this.paddingLeft + this.paddingRight) * buttonCount;
    	itemWidth = (this.getWidth() - totalBorder - totalPadding)/buttonCount;
    	return itemWidth;
    }
    /**
	 * 触碰事件
	 */
	@Override
    public boolean onTouchEvent(MotionEvent event) {    	
    	try {
    		if(event.getAction() == MotionEvent.ACTION_UP){
    			clickHandler((int)event.getX(), (int)event.getY());
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return true;
    }
	/**
	 * click处理事件
	 */
	private void clickHandler(int x, int y){
		if(x < 0 || y < 0)
			return ;
		if(x > this.getWidth() || y > this.getHeight())
			return ;
		paint.setTextSize(this.textSize);
		if(this.style == WRAP){//wrap 模式					
	    	int left = 0;
			for(int i = 0; i<this.buttonTexts.length; i++){
				String text = this.buttonTexts[i];
				int txtWidth = (int)this.paint.measureText(text);
				int right = left + this.border + this.paddingLeft + this.paddingRight + txtWidth;
				if(x >= left && x<right){
					clickTheButton(i);
					return;
				}
				left = right;				
			}
			return ;
		}
		//equal模式		
		int buttonWidth = getButtonWidthByEqual();//计算button的宽度，不包含padding
		int left = 0;
		for(int i = 0; i<this.buttonTexts.length; i++){
			String text = this.buttonTexts[i];
			int right = left + this.border + this.paddingLeft + this.paddingRight + buttonWidth;
			if(x >= left && x<right){
				clickTheButton(i);
				return;
			}
			left = right;				
		}
	}
	/**
	 * 点击某个button
	 */
	private void clickTheButton(int index){
		if(index == this.selectedIndex)
			return ;
		if(this.onSelectedListener!= null){
			this.onSelectedListener.OnSelecteChanged(this.buttonValues[index], index);
		}
		this.selectedIndex = index;
		this.invalidate();
	}
	/** 
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
     */  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
    /**
     * 监听接口
     */
    public interface OnSelectedListener{
    	public void OnSelecteChanged(String value, int index);
    }
    
    
    

	public int getPadding() {
		return padding;
	}
	public void setPadding(int padding) {
		this.padding = padding;
		this.paddingLeft = padding;
		this.paddingTop = padding;
		this.paddingRight = padding;
		this.paddingBottom = padding;
		this.invalidate();
	}
	public int getPaddingLeft() {
		return paddingLeft;
	}
	public void setPaddingLeft(int paddingLeft) {
		this.paddingLeft = paddingLeft;
		this.invalidate();
	}
	public int getPaddingTop() {
		return paddingTop;
	}
	public void setPaddingTop(int paddingTop) {
		this.paddingTop = paddingTop;
		this.invalidate();
	}
	public int getPaddingRight() {
		return paddingRight;
	}
	public void setPaddingRight(int paddingRight) {
		this.paddingRight = paddingRight;
		this.invalidate();
	}
	public int getPaddingBottom() {
		return paddingBottom;
	}
	public void setPaddingBottom(int paddingBottom) {
		this.paddingBottom = paddingBottom;
		this.invalidate();
	}
	public int getCornerRadius() {
		return cornerRadius;
	}
	public void setCornerRadius(int cornerRadius) {
		this.cornerRadius = cornerRadius;
		this.invalidate();
	}
	public int getBorder() {
		return border;
	}
	public void setBorder(int border) {
		this.border = border;
		this.invalidate();
	}
	public int getTextSize() {
		return textSize;
	}
	public void setTextSize(int textSize) {
		this.textSize = textSize;
		this.invalidate();
	}
	public String[] getButtonTexts() {
		return buttonTexts;
	}
	public void setButtonTexts(String[] buttonTexts) {
		this.buttonTexts = buttonTexts;
		if(this.buttonValues == null)
			this.buttonValues = this.buttonTexts;
		this.invalidate();
	}
	public String[] getButtonValues() {
		return buttonValues;
	}
	public void setButtonValues(String[] buttonValues) {
		this.buttonValues = buttonValues;
		this.invalidate();
	}
	public int getBgColor() {
		return selectedColor;
	}
	public void setBgColor(int bgColor) {
		this.selectedColor = bgColor;
		this.invalidate();
	}
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
		this.invalidate();
	}
	public int getSelectedIndex() {
		return selectedIndex;
	}
	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
		this.invalidate();
	}
	public void setOnSelectedListener(OnSelectedListener onSelectedListener) {
		this.onSelectedListener = onSelectedListener;
	}
}
