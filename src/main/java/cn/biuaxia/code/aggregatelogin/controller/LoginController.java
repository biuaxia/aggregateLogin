package cn.biuaxia.code.aggregatelogin.controller;

import cn.biuaxia.code.aggregatelogin.model.Userinfo;
import cn.biuaxia.code.aggregatelogin.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("api/login")
public class LoginController {

    private final ILoginService loginService;

    public LoginController(ILoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String login(@RequestParam String type) {
        return "redirect:" + loginService.getTheRedirectLoginAddress(type);
    }

    @GetMapping("callback")
    public ModelAndView callback(@RequestParam String code,
                                 @RequestParam String type) {
        ModelAndView modelAndView = new ModelAndView("userinfo");
        log.debug("code: [{}], type: [{}]", code, type);

        Userinfo userinfo = loginService.getUserInformation(code, type);
        modelAndView.addObject("userinfo", userinfo);

        return modelAndView;
    }

}
