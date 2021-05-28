/**
 * @projectName ZYF
 * @package com.example.zyf.DesignPattern.State
 * @className com.example.zyf.DesignPattern.State.Context
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.DesignPattern.State;

import org.apache.commons.lang.StringUtils;

import lombok.Data;

/**
 * Context
 * @description
 * @author zyf
 * @date 2020/12/24 15:32
 * @version 1.0
 */
@Data
public class Context {
    private boolean flag; //代表流程是否结束
    private State state; // 阶段流程信息
    private String message; // 消息
    /**
     * 流程状态 0：通过 1:驳回 2.退回整改 3.已申请
     */
    private int status;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Context() {
        super();
    }

    public static boolean start(Context context) {
        State state = new StartState();
        context.setState(state);
        context.setStatus(3);
        context.getState().doAction(context);
        if (StringUtils.equals("CEO", state.getName()) && 0 == context.getStatus() && context.isFlag()) {
            System.out.println("通过，流程结束");
            return true;
        } else {
            System.out.println("未通过，流程结束");
            return false;
        }
    }
}
