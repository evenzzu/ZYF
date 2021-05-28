/**
 * @projectName ZYF
 * @package com.example.zyf.DesignPattern.State
 * @className com.example.zyf.DesignPattern.State.State
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.DesignPattern.State;

import lombok.Data;

/**
 * State
 * @description
 * @author zyf
 * @date 2020/12/24 15:30
 * @version 1.0
 */
@Data
public abstract class State {
    private String name;
    public abstract void doAction(Context context);
}