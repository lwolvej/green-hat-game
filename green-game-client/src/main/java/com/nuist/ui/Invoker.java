package com.nuist.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 实现界面之间的动画效果：Invoke.show(该界面类);（不要实现setVisible）
 *
 * @author LwolveJ
 */
public class Invoker implements ActionListener {

    private static final int ANIMATION_FRAMES = 30;

    //要被显示的窗口
    private Window window;

    //窗口全部展开的全部大小
    private Dimension fullSize;

    //动画定时器
    private Timer timer;

    //当前动画帧
    private int frameIndex;

    public static void show(Window w) {
        if (w.isVisible()) {
            return;
        }
        new Invoker(w).invoke();
    }

    private Invoker(Window window) {
        this.window = window;
        fullSize = window.getSize();
        timer = new Timer(15, this);
        window.setSize(0, 0);
    }

    private void invoke() {
        if (!window.isVisible()) {
            timer.start();
            window.setVisible(true);
        }
    }

    //动画一帧的处理
    @Override
    public void actionPerformed(ActionEvent e) {
        int w = fullSize.width * frameIndex / ANIMATION_FRAMES;
        int h = fullSize.height * frameIndex / ANIMATION_FRAMES;
        window.setSize(w, h);
        if (frameIndex == ANIMATION_FRAMES) {
            timer.stop();
            timer = null;
            window = null;
            fullSize = null;
        } else {
            frameIndex++;
        }
    }
}
