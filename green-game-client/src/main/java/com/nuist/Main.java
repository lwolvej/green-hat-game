package com.nuist;

import com.nuist.ui.Invoker;
import com.nuist.ui.zhu.FirstPage;

import java.awt.*;

/**
 * @author LwolveJ
 */

public class Main {


    //入口方法
    public static void main(String... args) {
        EventQueue.invokeLater(() -> Invoker.show(new FirstPage()));
    }


}
