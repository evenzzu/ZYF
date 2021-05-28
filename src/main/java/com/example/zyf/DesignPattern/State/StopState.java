/**
 * @projectName ZYF
 * @package com.example.zyf.DesignPattern.State
 * @className com.example.zyf.DesignPattern.State.StopState
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.DesignPattern.State;

/**
 * StopState
 * @description
 * @author zyf
 * @date 2020/12/24 15:32
 * @version 1.0
 */
public class StopState extends State{
    @Override
    public void doAction(Context context) {
        System.out.println(this);
        if (!context.isFlag()) {
            if (context != null && 0 == context.getStatus()) {
                setName("HR");
                context.setMessage(context.getMessage() + getName() + "通过");
                System.out.println(context.getMessage());
                context.setStatus(0);
                context.setState(new EndState());
                context.getState().doAction(context);
            }
        }else {
            System.out.println("流程结束");
        }
    }
    public String toString(){
        return "Stop State";
    }
}
