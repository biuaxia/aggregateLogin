package cn.biuaxia.code.aggregatelogin.service;

import cn.biuaxia.code.aggregatelogin.model.Userinfo;

public interface ILoginService {

    /**
     * 获取跳转登录地址
     *
     * @return 登录地址，微信和支付宝返回二维码地址
     */
    String getTheRedirectLoginAddress(String type);

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    Userinfo getUserInformation(String code, String type);

}
