package com.nuist.game.view.panel;

import com.nuist.game.service.PaintService;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏面板绘制
 *
 * @author LwolveJ
 */

public class GamePanel extends JPanel {

    private static final long serialVersionUID = -4896945532830491308L;

    private PaintService paintService;

    public GamePanel(PaintService paintService) {
        this.paintService = paintService;
    }

    @Override
    public void paint(Graphics g)  {
        super.paint(g);
        paintService.repaintPanel(g, this);
    }
}
