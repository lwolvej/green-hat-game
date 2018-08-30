package com.nuist.game.resources;


import java.awt.*;

/**
 * 游戏图片
 *
 * @author LwolveJ
 */
public class GameImages {

    /**
     * 玩家变绿图片
     */
    public static Image myGreenPeopleImage[] = {
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/green/1.png")),
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/green/2.png")),
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/green/3.png")),
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/green/4.png")),
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/green/5.png")),
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/green/6.png"))
    };

    /**
     * 电脑变绿图片
     */
    public static Image enemyGreenPeopleImage[] = {
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/green/1.png")),
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/green/2.png")),
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/green/3.png")),
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/green/6.png")),
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/green/5.png")),
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/green/4.png"))
    };

    public static Image myPeopleImage[] = {
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/normal/0.png")),
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/normal/1.png")),
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/normal/2.png")),
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/normal/21.png")),
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/normal/22.png")),
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/normal/23.png"))
    };

    /**
     * 电脑正常图片
     */
    public static Image enemyPeopleImage[] = {
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/normal/11.png")),
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/normal/12.png")),
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/normal/13.png")),
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/normal/33.png")),
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/normal/32.png")),
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/people/normal/31.png"))
    };

    /**
     * 砖块图片
     */
    public static Image brickImage[] = {
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/brick/black-brick.png")),
            Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/brick/red-brick.png"))
    };

    /**
     * 帽子图片
     */
    public static Image hatImage = Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/game/hat.png"));

    /**
     * TODO
     * 帽子碰撞图片
     */
    public static Image bombImage = Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/game/peng.png"));


    public static Image backgroundImage = Toolkit.getDefaultToolkit().getImage(GameImages.class.getResource("/image/background.png"));


}
