package com.nuist.game;

import com.nuist.game.context.GameContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GameMain {

    public void start(String name) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");
        GameContext context1 = (GameContext) context.getBean("gameContext");
        context1.init(name);
        context1.startGame();
        context1.playMusic();
    }

    public static void main (String... args) {
        new GameMain().start("...");
    }
}
