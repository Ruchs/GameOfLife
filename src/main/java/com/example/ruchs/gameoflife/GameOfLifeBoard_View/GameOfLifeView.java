package com.example.ruchs.gameoflife.GameOfLifeBoard_View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.example.ruchs.gameoflife.app.Cell;
import com.example.ruchs.gameoflife.app.GameOfLifeBoard;

public class GameOfLifeView extends SurfaceView implements Runnable {

    public static final int DEFAULT_SIZE = 70;
    public static final int DEFAULT_ALIVE_COLOR = Color.WHITE;
    public static final int DEFAULT_DEAD_COLOR = Color.BLACK;
    private Thread thread;
    private boolean isRunning = false;
    private int columnWidth = 1;
    private int rowHeight = 1;
    private int columns = 1;
    private int rows = 1;
    private GameOfLifeBoard gameOfLifeBoard;
    private Rect rect = new Rect();
    private Paint paint = new Paint();

    public GameOfLifeView(Context context) {
        super(context);
        initGameOfLifeBoard();
    }

    public GameOfLifeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameOfLifeBoard();
    }

    @Override
    public void run() {
        while (isRunning) {
            if (!getHolder().getSurface().isValid())
                continue;
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }
            Canvas canvas = getHolder().lockCanvas();
            gameOfLifeBoard.nextGeneration();
            drawCells(canvas);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    public void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        isRunning = false;

        while (true) {
            try {
                thread.join();
            } catch (InterruptedException e) {
            }
            break;
        }

    }

    private void initGameOfLifeBoard() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        columns = point.x / DEFAULT_SIZE;
        rows = point.y / DEFAULT_SIZE;
        columnWidth = point.x / columns;
        rowHeight = point.y / rows;
        gameOfLifeBoard = new GameOfLifeBoard(columns, rows);
    }

    private void drawCells(Canvas canvas) {
        for (int i = 0; i < columns; i++)
            for (int j = 0; j < rows; j++) {
                Cell cell = gameOfLifeBoard.get(i, j);
                rect.set((cell.xPosition * columnWidth) - 1,
                        (cell.yPosition * rowHeight) - 1,
                        (cell.xPosition * columnWidth + columnWidth) - 1,
                        (cell.yPosition * rowHeight + rowHeight) - 1);
                if (cell.aliveCell)
                    paint.setColor(DEFAULT_ALIVE_COLOR);
                else
                    paint.setColor(DEFAULT_DEAD_COLOR);
                canvas.drawRect(rect, paint);
            }
    }

}