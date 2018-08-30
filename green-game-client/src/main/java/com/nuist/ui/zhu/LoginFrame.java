package com.nuist.ui.zhu;

import com.nuist.entity.User;
import com.nuist.factory.BeanFactory;
import com.nuist.service.UserService;
import com.nuist.ui.Invoker;
import com.nuist.ui.chen.MenuFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/*
 * @author 朱雨薇
 * 登陆界面
 *
 */
public class LoginFrame extends JFrame {
    JLabel name, password;
    JTextField tname;
    JPasswordField tpassword;
    JButton Login, Cancel;
    String nameText;
    String passwordText;

    private UserService userService = BeanFactory.getInstance().create(UserService.class);

    public LoginFrame() {
        // 设置窗体的相关属性


        name = new JLabel("账  号");
        name.setFont(new Font("宋体", Font.BOLD, 24));
        name.setForeground(Color.WHITE);
        name.setBounds(500, 450, 100, 30);
        this.add(name);

        password = new JLabel("密  码");
        password.setFont(new Font("宋体", Font.BOLD, 24));
        password.setForeground(Color.WHITE);
        password.setBounds(500, 520, 100, 30);
        this.add(password);

        tname = new JTextField(20);
        tname.setBounds(600, 450, 141, 30);
        //设置输入框凹陷效果
        tname.setBorder(BorderFactory.createLoweredBevelBorder());
        this.add(tname);

        tpassword = new JPasswordField(20);
        tpassword.setBounds(600, 520, 141, 30);
        //设置输入框凹陷效果
        tpassword.setBorder(BorderFactory.createLoweredBevelBorder());
        this.add(tpassword);

        Login = new JButton("登录");
        Login.setFont(new Font("宋体", Font.BOLD, 22));
        Login.setForeground(Color.GREEN);
        Login.setBounds(520, 580, 70, 40);
        Login.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        //监听
        Login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                nameText = tname.getText();

                passwordText = tpassword.getText();
                User user = userService.login(nameText, passwordText);
                if (nameText.length() == 0) {
                    JOptionPane.showMessageDialog(null, "账号不能为空");
                }
                if (passwordText.length() == 0) {
                    JOptionPane.showMessageDialog(null, "密码不能为空");
                }
                if (user != null) {
                    JOptionPane.showMessageDialog(null, "登录成功");
//                    Invoker.show(new MenuFrame(nameText));
                    new MenuFrame(nameText);
                    setVisible(false);
                    dispose();
                }
            }
        });
        this.add(Login);

        Cancel = new JButton("取消");
        Cancel.setFont(new Font("宋体", Font.BOLD, 22));
        Cancel.setForeground(Color.GREEN);
        Cancel.setBounds(640, 580, 70, 40);
        Cancel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        Cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new FirstPage().setVisible(true);
                dispose();

            }
        });
        this.add(Cancel);

        BackImage back = new BackImage();
        back.run();
        this.add(back);
        // 设置窗体的相关属性
        this.setSize(1200, 675);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        //设置窗体logo
        this.setIconImage(new ImageIcon("logo3.jpg").getImage());
        this.setVisible(true);
    }
}
	
