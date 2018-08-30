package com.nuist.ui.wu;

import com.nuist.entity.Score;
import com.nuist.factory.BeanFactory;
import com.nuist.service.ScoreService;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * @author 吴惠敏
 * 排行榜界面
 */
public class RankListPanel extends JPanel implements KeyListener {
    Image background;
    Image title;
    Image honor;
    JLabel exit = new JLabel();
    PeaShooter peashooter = new PeaShooter();
    Pea[] peas = {};
    ScoreService scoreService = BeanFactory.getInstance().create(ScoreService.class);
    List<Score> scoreList = scoreService.getRankList();

    public RankListPanel() {
        try {
//            background = ImageIO.read(new File("background.png"));
//            title = ImageIO.read(new File("phb.png"));
//            honor = ImageIO.read(new File("绿帽王.png"));
            background = ImageIO.read(RankListPanel.class.getResourceAsStream("/image/wu/background.png"));
            title = ImageIO.read(RankListPanel.class.getResourceAsStream("/image/wu/phb.png"));
            honor = ImageIO.read(RankListPanel.class.getResourceAsStream("/image/wu/绿帽王.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, RankListFrame.WIDTH, RankListFrame.HEIGHT, null);
        g.drawImage(title, 250, 10, 300, 130, null);
        //绘制豌豆射手
        peashooter.paintPerson(g);
        //绘制所有豌豆
        for (int i = 0; i < peas.length; i++) {
            peas[i].paintPea(g);
        }

        //绘制排行榜数据
        g.setColor(Color.WHITE);
        g.setFont(new Font("华文琥珀", Font.BOLD, 24));
        g.drawString("名次", 140, 170);
        g.drawString("昵称", 270, 170);
        g.drawString("得分", 410, 170);
        g.drawString("时间", 560, 170);

        int i = 0;
        for (Score score : scoreList) {
            g.setColor(Color.WHITE);
            //前三名信息设为绿色
            if (i <= 3) {
                g.setColor(Color.GREEN);
            }
            g.drawString(score.getRank().toString(), 150, 220 + i * 50);
            g.drawString(score.getUserName(), 250, 220 + i * 50);
            g.drawString(score.getNumber().toString(), 400, 220 + i * 50);
            g.drawString(score.getTime(), 500, 220 + i * 50);
//            //判断是否被帽子射中
//            for(int j=0;j<peas.length;j++){
//                if(peas[j].getX()>=200&&peas[j].getY()==200+i*50){
//                    g.setColor(Color.GREEN);
//                }
//            }
            i++;
        }
        //绘制头衔
        g.drawImage(honor, 110, 170, 100, 50, null);

    }

    //豌豆射手移动的方法
    public void stepAction() {
        peashooter.step();
        for (int i = 0; i < peas.length; i++) {
            peas[i].step();
        }
    }

    //豌豆入场方法
    int index = 0;

    public void enteredAction() {
        index++;
        //enteresAction每执行1000次，生成1个豌豆
        if (index % 400 == 0) {
            Pea pea = new Pea();//生成一个豌豆
            pea.setY(peashooter.getY());
            peas = Arrays.copyOf(peas, peas.length + 1);//对数组扩容
            peas[peas.length - 1] = pea;
        }
    }

    //线程方法
    public void action() {
        //创建一个线程并启动
        new Thread() {
            public void run() {
                while (true) {
                    enteredAction();
                    stepAction();
                    repaint();//重绘方法
                    //删除越界的豌豆
                    for (int i = 0; i < peas.length; i++) {
                        if (peas[i].getX() >= RankListFrame.WIDTH + peas[i].getWidth()) {
                            Pea t = peas[peas.length - 1];
                            peas[peas.length - 1] = peas[i];
                            peas[i] = t;
                            peas = Arrays.copyOf(peas, peas.length - 1);
                        }
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        int y = peashooter.getY();
        if (e.getKeyCode() == KeyEvent.VK_UP && y >= 150) {
            peashooter.setY(y - 10);

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && y < 500) {

            peashooter.setY(y + 10);
            peas[peas.length - 1].setY(y + 10);
        }

    }

    public void keyReleased(KeyEvent e) {

    }
}
