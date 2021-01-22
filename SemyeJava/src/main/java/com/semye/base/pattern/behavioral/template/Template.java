package com.semye.base.pattern.behavioral.template;

/**
 * Created by yesheng on 2018/10/20.
 * 模板方法模式
 * <p>
 * 开发app的流程
 */
public abstract class Template {


    /**
     * 需求分析阶段
     */
    abstract void demand();

    /**
     * 架构设计阶段
     */
    abstract void design();

    /**
     * 代码编写阶段
     */
    abstract void coding();


    public final void createApp() {
        demand();
        design();
        coding();
    }

}
