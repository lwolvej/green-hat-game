package com.nuist.ui.tang;


import java.awt.event.MouseListener;
import java.io.IOException;
import java.awt.event.MouseEvent;

import java.awt.*;

import java.util.List;

import com.nuist.entity.Score;
import com.nuist.factory.BeanFactory;
import com.nuist.service.ScoreService;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author tll123
 * 历史记录
 */
public class HistoryFrame extends JFrame implements MouseListener {

    public final int WIDTH = 800, HEIGHT = 550;
    private List<Score> scoress;

    public HistoryFrame(String userName) {
        ScoreService service = BeanFactory.getInstance().create(ScoreService.class);
        scoress = service.getHistoryScore(userName);
        this.setSize(WIDTH, HEIGHT);
        this.addMouseListener(this);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setUndecorated(false);
        this.setLocationRelativeTo(null);
    }


    private int t = 0;

    public void paint(Graphics g) {
        Image title = null;
        Image backImage = null;
        Image back = null;

        try {
            backImage = ImageIO.read(HistoryFrame.class.getResourceAsStream("/image/tang/background1.png"));
            back = ImageIO.read(HistoryFrame.class.getResourceAsStream("/image/tang/back1.png"));
            title = ImageIO.read(HistoryFrame.class.getResourceAsStream("/image/tang/historytitle1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(backImage, 0, 0, 800, 600, null);
        g.drawImage(back, 650, 65, 100, 40, null);
        g.drawImage(title, 250, 0, 300, 130, null);
        g.setColor(Color.white);
        g.setFont(new Font("华文琥珀", Font.BOLD, 24));
        g.drawString("排名       分数            时间", 180, 165);

        if (scoress.size() < 10) t = scoress.size();
        else t = 10;
        for (int i = 0; i < t; i++) {
            Score score = scoress.get(i);
            g.drawString(score.getRank().toString(), 190, 200 + i * 38);
            g.drawString(score.getNumber().toString(), 290, 200 + i * 38);
            g.drawString(score.getTime(), 400, 200 + i * 38);
        }


    }

    public void mouseClicked(MouseEvent arg0) {
        if (arg0.getX() >= 650 && arg0.getX() <= 750 && arg0.getY() > 50 && arg0.getY() < 100) {
            dispose();
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {

    }

    @Override
    public void mouseExited(MouseEvent arg0) {

    }

    @Override
    public void mousePressed(MouseEvent arg0) {

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {

    }
}
