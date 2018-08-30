package com.nuist.ui.zhu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
/*
 *
 * @author 朱雨薇
 * 首页
 *
 */

public class FirstPage extends JFrame implements MouseListener {
    //注册按钮
    JLabel resiger;
    //登录按钮
    JLabel login;

    public FirstPage() {

        resiger = new JLabel(new ImageIcon(FirstPage.class.getResource("/image/zhu/注册2.png")));
        resiger.setBounds(150, 510, 378, 151);

        resiger.addMouseListener(this);
        this.add(resiger);

        login = new JLabel(new ImageIcon(FirstPage.class.getResource("/image/zhu/登录2.png")));
        login.setBounds(650, 510, 378, 151);

        login.addMouseListener(this);
        this.add(login);

        BackImage back = new BackImage();
        back.run();
        this.add(back);

        this.setSize(1200, 675);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setIconImage(new ImageIcon(FirstPage.class.getResource("/image/zhu/logo3.jpg")).getImage());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // 鼠标点击事件
        if (e.getSource().equals(resiger)) {
            //界面跳转
            new ResigerFrame();
            //关闭当前
            dispose();
        }
        if (e.getSource().equals(login)) {
            //界面跳转
            new LoginFrame();
            //关闭当前
            dispose();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
