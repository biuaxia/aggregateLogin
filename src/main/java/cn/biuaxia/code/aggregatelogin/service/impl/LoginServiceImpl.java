package cn.biuaxia.code.aggregatelogin.service.impl;

import cn.biuaxia.code.aggregatelogin.core.Core;
import cn.biuaxia.code.aggregatelogin.model.Userinfo;
import cn.biuaxia.code.aggregatelogin.service.ILoginService;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class LoginServiceImpl implements ILoginService {

    /**
     * 跳转登录地址
     * <p>
     * 参数列表:
     * <ol>
     *     <li>appID</li>
     *     <li>appKey</li>
     *     <li>登录类型，参见</li>
     *     <li>登录成功后跳转的地址</li>
     * </ol>
     */
    public static final String JUMP_TO_LOGIN_ADDRESS_URL = "https://u.cccyun.cc/connect.php?act=login&appid={}&appkey={}&type={}&redirect_uri={}";

    /**
     * 获取用户信息
     * <p>
     * 参数列表:
     * <ol>
     *     <li>appID</li>
     *     <li>appKey</li>
     *     <li>登录类型，参见</li>
     *     <li>授权码</li>
     * </ol>
     */
    public static final String GET_USER_INFORMATION_URL = "https://u.cccyun.cc/connect.php?act=callback&appid={}&appkey={}&type={}&code={}";

    private final Core core;

    public LoginServiceImpl(Core core) {
        this.core = core;
    }

    /**
     * 获取跳转登录地址
     *
     * @return 登录地址，微信和支付宝返回二维码地址
     */
    @Override
    public String getTheRedirectLoginAddress(String type) {
        String reqUrl = StrUtil.format(JUMP_TO_LOGIN_ADDRESS_URL,
                core.getAppID(),
                core.getAppKey(),
                type,
                core.getRedirectUrl());
        log.debug("type: [{}], reqUrl: [{}]", type, reqUrl);

        String body = HttpRequest.get(reqUrl).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        Integer respCode = jsonObject.getInt("code");
        if (respCode != 0) {
            log.warn("获取跳转登录地址失败");
            return null;
        }

        String url = jsonObject.getStr("url");
        log.debug("获取跳转登录地址成功: [{}]", url);
        return url;
    }

    /**
     * 获取用户信息
     *
     * @param type
     * @return 用户信息
     */
    @Override
    public Userinfo getUserInformation(String code, String type) {
        String reqUrl = StrUtil.format(GET_USER_INFORMATION_URL,
                core.getAppID(),
                core.getAppKey(),
                type,
                code);
        log.debug("type: [{}], reqUrl: [{}]", type, reqUrl);

        String body = HttpRequest.get(reqUrl).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        Integer respCode = jsonObject.getInt("code");
        if (respCode != 0) {
            log.warn("获取跳转登录地址失败");
            return null;
        }

        String nickname = jsonObject.getStr("nickname");

        log.debug("获取用户 [{}] 信息成功: [{}]",
                nickname,
                JSONUtil.toJsonPrettyStr(body));

        return jsonObject.toBean(Userinfo.class);
    }
}
