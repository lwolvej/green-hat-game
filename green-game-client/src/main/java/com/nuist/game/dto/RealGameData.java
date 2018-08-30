package com.nuist.game.dto;

import com.nuist.game.entity.Bomb;
import com.nuist.game.entity.EnemyPeople;
import com.nuist.game.entity.MyPeople;
import com.nuist.game.enums.StuffTypeEnum;
import com.nuist.game.resources.map.Map;

import java.util.Vector;

/**
 * 游戏当前数据传输
 *
 * @author LwolveJ
 */
public class RealGameData {

    /**
     * 我
     */
    private Vector<MyPeople> myPeople = new Vector<>();

    /**
     * 敌人
     */
    private Vector<EnemyPeople> enemyPeople = new Vector<>();

    private Vector<Bomb> bombs = new Vector<>();

    /**
     * 事物类型
     */
    private StuffTypeEnum stuffTypeEnum = StuffTypeEnum.BRICK;

    /**
     * 地图
     */
    private Map map;

    /**
     * 我的数量
     */
    private int myPeopleNum;

    /**
     * 敌人的数量
     */
    private int enemyPeopleNum;

    /**
     * 被带帽数量
     */
    private int hatNum;

    private boolean isStart = false;

    private boolean isStop = false;

    private boolean up = false;

    private boolean down = false;

    private boolean left = false;

    private boolean right = false;

    private int level = 1;

    private int dy = 600;

    private int ky = 600;

    private int kx = 0;

    private int beKilled;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void keyPressDirect(boolean up, boolean down, boolean left, boolean right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public void rest() {
        enemyPeople.forEach(obj -> obj.setLive(false));
        myPeople.clear();
        enemyPeople.clear();
        map = null;
    }

    public Vector<MyPeople> getMyPeople() {
        return myPeople;
    }

    public void setMyPeople(Vector<MyPeople> myPeople) {
        this.myPeople = myPeople;
    }

    public Vector<EnemyPeople> getEnemyPeople() {
        return enemyPeople;
    }

    public void setEnemyPeople(Vector<EnemyPeople> enemyPeople) {
        this.enemyPeople = enemyPeople;
    }

    public StuffTypeEnum getStuffTypeEnum() {
        return stuffTypeEnum;
    }

    public void setStuffTypeEnum(StuffTypeEnum stuffTypeEnum) {
        this.stuffTypeEnum = stuffTypeEnum;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public int getMyPeopleNum() {
        return myPeopleNum;
    }

    public void setMyPeopleNum(int myPeopleNum) {
        this.myPeopleNum = myPeopleNum;
    }

    public int getEnemyPeopleNum() {
        return enemyPeopleNum;
    }

    public void setEnemyPeopleNum(int enemyPeopleNum) {
        this.enemyPeopleNum = enemyPeopleNum;
    }

    public int getHatNum() {
        return hatNum;
    }

    public void setHatNum(int hatNum) {
        this.hatNum = hatNum;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public Vector<Bomb> getBombs() {
        return bombs;
    }

    public void setBombs(Vector<Bomb> bombs) {
        this.bombs = bombs;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getKy() {
        return ky;
    }

    public void setKy(int ky) {
        this.ky = ky;
    }

    public int getKx() {
        return kx;
    }

    public void setKx(int kx) {
        this.kx = kx;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public int getBeKilled() {
        return beKilled;
    }

    public void setBeKilled(int beKilled) {
        this.beKilled = beKilled;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }
}
