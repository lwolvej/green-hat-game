package com.nuist.service;

import com.nuist.entity.Score;

import java.util.List;

/**
 * 分数服务类
 *
 * @author LwolveJ
 */
public interface ScoreService {

    /**
     * 获取用户历史得分记录
     *
     * @param userName 用户名
     * @return 用户的历史得分记录（包含自己分数的排名，从高到低）
     * @throws com.nuist.exception.MyException 抛出异常的情况：用户历史记录不存在，用户名不存在
     */
    List<Score> getHistoryScore(String userName);

    /**
     * 获取全服排行榜
     *
     * @return 排行榜
     */
    List<Score> getRankList();

    /**
     * 添加玩家的分数
     *
     * @param userName 用户名
     * @param score    分数
     * @return 返回是否添加成功（不成功的情况：用户不存在，网络错误）
     */
    Boolean addScore(String userName, String score);
}
