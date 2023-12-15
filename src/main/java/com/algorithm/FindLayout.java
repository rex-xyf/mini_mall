package com.algorithm;

import com.sim.domain.Plan;

public class FindLayout {
    public Plan find(Plan layout){
        //多的情况
        if (layout.getCount() >20){
            return new Plan();
        }
        //少的情况
        else if (layout.getCount() <20){
            return new Plan();
        }
        //一致的情况
        else {
            return new Plan();
        }
    }
}
