package cn.nicolite.huthelper.network.api;

import cn.nicolite.huthelper.model.bean.Vote;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 投票API
 * Created by nicolite on 17-11-1.
 */

public interface VoteAPI {
    /**
     * 获取统计数据
     *
     * @return
     */
    @GET("opensource/staticAPI/vote.json")
    Observable<Vote> getAirConditionerData();

    /**
     * 提交统计数据
     *
     * @return
     */
    @GET("opensource/staticAPI/vote.json")
    Observable<Vote> setAirConditionerData();

}
