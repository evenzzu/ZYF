/**
 * @projectName ZYF
 * @package com.example.zyf.DesignPattern.State
 * @className com.example.zyf.DesignPattern.State.EndState
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.DesignPattern.State;

/**
 * EndState
 * @description
 * @author zyf
 * @date 2020/12/24 19:29
 * @version 1.0
 */
public class EndState extends State{

    @Override
    public void doAction(Context context) {
        if (!context.isFlag()){
            // 节点名称
            setName("CEO");
            context.setStatus(0);
            context.setMessage(context.getMessage()+getName()+"通过");
            System.out.println(context.getMessage());
            context.setState(new StopState());
            context.setFlag(true);
        }
    }
    public String toString(){
        return "End State";
    }
}
