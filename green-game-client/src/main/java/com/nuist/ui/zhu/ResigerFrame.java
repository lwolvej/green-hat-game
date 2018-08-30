package com.nuist.ui.zhu;

import com.nuist.factory.BeanFactory;
import com.nuist.service.UserService;
import com.nuist.ui.Invoker;
import com.nuist.ui.chen.MenuFrame;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/*
 * @author 朱雨薇
 * 注册界面
 *
 */
public class ResigerFrame extends JFrame {
    JLabel name, password, repassword;
    JTextField tname;
    JPasswordField tpassword, trepassword;
    JButton OK, Cancel;
    String nameText;
    String passwordText;
    String repasswordText;
    BackImage back;

    private UserService userService = BeanFactory.getInstance().create(UserService.class);

    public ResigerFrame() {

        name = new JLabel("账          号");
        name.setForeground(Color.WHITE);
        name.setBounds(500, 450, 100, 30);
        this.add(name);

        password = new JLabel("密          码");
        password.setForeground(Color.WHITE);
        password.setBounds(500, 500, 100, 30);
        this.add(password);

        repassword = new JLabel("确认密码");
        repassword.setForeground(Color.WHITE);
        repassword.setBounds(500, 550, 110, 30);
        this.add(repassword);

        tname = new JTextField(20);
        tname.setBounds(575, 450, 141, 30);
        //设置输入框凹陷效果
        tname.setBorder(BorderFactory.createLoweredBevelBorder());
        this.add(tname);

        tpassword = new JPasswordField(20);
        tpassword.setBounds(575, 500, 141, 30);
        //设置输入框凹陷效果
        tpassword.setBorder(BorderFactory.createLoweredBevelBorder());
        this.add(tpassword);

        trepassword = new JPasswordField(20);
        trepassword.setBounds(575, 550, 141, 30);
        //设置输入框凹陷效果
        trepassword.setBorder(BorderFactory.createLoweredBevelBorder());
        this.add(trepassword);

        OK = new JButton("注册");
        OK.setForeground(Color.GREEN);
        OK.setBounds(520, 620, 70, 40);
        OK.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        OK.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                nameText = tname.getText();
                passwordText = tpassword.getText();
                repasswordText = trepassword.getText();
                if (nameText.length() == 0) {
                    JOptionPane.showMessageDialog(null, "用户名不能为空");
                } else if (passwordText.length() == 0) {
                    JOptionPane.showMessageDialog(null, "密码不能为空");
                } else if (!repasswordText.equals(passwordText)) {
                    JOptionPane.showMessageDialog(null, "密码错误");
                } else {
                    userService.register(nameText, passwordText);
                    JOptionPane.showMessageDialog(null, "注册成功");
                    Invoker.show(new MenuFrame(nameText));
                    dispose();
                }
            }
        });
        this.add(OK);

        Cancel = new JButton("取消");
        Cancel.setForeground(Color.GREEN);
        Cancel.setBounds(620, 620, 70, 40);
        Cancel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        Cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new FirstPage().setVisible(true);
                dispose();

            }
        });
        this.add(Cancel);


        back = new BackImage();
        back.run();
        this.add(back);


        // 设置窗体的相关属性
        this.setSize(1200, 675);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setVisible(true);
    }
}
