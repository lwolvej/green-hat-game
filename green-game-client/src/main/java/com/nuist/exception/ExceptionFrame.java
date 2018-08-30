package com.nuist.exception;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 弹窗
 *
 * @author XiaoQi
 */
class ExceptionFrame extends JFrame implements MouseListener {

    private static final long serialVersionUID = -4501357610572138729L;

    private static final int WIDTH = 427;
    private static final int HEIGHT = 536;

    private JLabel ok_enter;

    ExceptionFrame(String message) {
        JLabel prompt = new JLabel(message, JLabel.CENTER);
        prompt.setBounds(0, 200, 400, 84);
        prompt.setFont(new Font("Dialog", Font.BOLD, 18));
        prompt.setForeground(Color.darkGray);

        this.add(prompt);

        ok_enter = new JLabel(new ImageIcon(ExceptionFrame.class.getResource("/image/ok_enter.png")));
        ok_enter.setBounds(0, 455, 427, 84);
        ok_enter.setEnabled(false);
        ok_enter.addMouseListener(this);
        this.add(ok_enter);

        JLabel error_dialog = new JLabel(new ImageIcon(ExceptionFrame.class.getResource("/image/error.png")));
        error_dialog.setBounds(0, 0, WIDTH, HEIGHT);
        this.add(error_dialog);

        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == ok_enter) {
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
        if (e.getSource() == ok_enter) {
            ok_enter.setEnabled(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == ok_enter) {
            ok_enter.setEnabled(false);
        }
    }
}

