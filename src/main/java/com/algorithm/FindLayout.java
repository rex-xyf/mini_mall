package com.algorithm;

import com.sim.domain.Plan;

public class FindLayout {
    public Plan find(Plan layout){
        //多的情况
        if (layout.getCount() >20){
            //需要考虑类型比例，衣服6，商店6，餐饮5，娱乐3

            return new Plan();
        }
        //少的情况
        else if (layout.getCount() <20){
            //首先锁定大型店铺，之后是中型店铺，之后是小型店铺，按人均消费
            return new Plan();
        }
        //一致的情况
        else {
            return new Plan();
        }
    }


}
