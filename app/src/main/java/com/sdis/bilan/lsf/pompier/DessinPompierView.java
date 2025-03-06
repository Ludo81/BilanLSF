package com.sdis.bilan.lsf.pompier;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Stack;

public class DessinPompierView extends View {

    public static class DrawAction {
        public Path path;
        public int color;

        public DrawAction(Path path, int color) {
            this.path = path;
            this.color = color;
        }
    }

    public interface UndoRedoListener {
        void onUndoRedoStateChanged(boolean canUndo, boolean canRedo);
    }

    private Path drawPath;
    private Paint drawPaint;
    private Paint canvasPaint;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;

    private int currentColor = Color.BLACK;

    public Stack<DrawAction> undoStack = new Stack<>();
    public Stack<DrawAction> redoStack = new Stack<>();

    private UndoRedoListener listener;

    public DessinPompierView(Context context) {
        super(context);
        init();
    }

    public DessinPompierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DessinPompierView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(currentColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    public void setUndoRedoListener(UndoRedoListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                undoStack.push(new DrawAction(new Path(drawPath), currentColor));
                redoStack.clear();
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                notifyUndoRedoState();
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }

    public void changeColor(int color) {
        currentColor = color;
        drawPaint.setColor(currentColor);
    }

    public void clearDrawing() {
        drawCanvas.drawColor(Color.WHITE);
        undoStack.clear();
        redoStack.clear();
        invalidate();
        notifyUndoRedoState();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(undoStack.pop());
            redrawCanvas();
            notifyUndoRedoState();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(redoStack.pop());
            redrawCanvas();
            notifyUndoRedoState();
        }
    }

    private void notifyUndoRedoState() {
        if (listener != null) {
            listener.onUndoRedoStateChanged(!undoStack.isEmpty(), !redoStack.isEmpty());
        }
    }

    private void redrawCanvas() {
        drawCanvas.drawColor(Color.WHITE);
        for (DrawAction action : undoStack) {
            drawPaint.setColor(action.color);
            drawCanvas.drawPath(action.path, drawPaint);
        }
        invalidate();
    }
}