/**
 * @projectName ZYF
 * @package com.example.zyf.DesignPattern.State
 * @className com.example.zyf.DesignPattern.State.StartState
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.DesignPattern.State;

/**
 * StartState
 * @description
 * @author zyf
 * @date 2020/12/24 15:31
 * @version 1.0
 */
public class StartState extends State {

    @Override
    public void doAction(Context context) {
        System.out.println(this); // Start State\
        if (!context.isFlag()) {
            if (context != null && 3 == context.getStatus()) {
                System.out.println(context.getMessage());
                // 节点名称
                setName("经理");
                context.setStatus(0);
                context.setMessage(context.getMessage() + getName() + "通过");
                System.out.println(context.getMessage());
                context.setState(new StopState());
                context.getState().doAction(context);
            }
        }else {
            System.out.println("流程已结束");
        }
    }

    public String toString() {
        return "Start State";
    }
}
