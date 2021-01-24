package com.example.demo001.interceptor;


import com.example.demo001.utils.RSAHelper;
import com.example.demo001.utils.TestRSA;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description: 签名校验拦截器
 * @Author: erxin.chen
 * @Date: 2021/1/24 14:50
 */
@Component
@Slf4j
public class RsaSignInterceptor implements HandlerInterceptor {
    private static Gson gson = new Gson();
    private String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCgf/G15wo+SeUl+QPxVOJfv4eL0nQtzawTKuYvESDFWg5LiuKUXDXtqujA/FIFwI7H8ZfzlL7z+dnEFXfycSpSMDWXYVonJi/R9oqkWZYuc6krn9r4b2kOd+mr9Z37Xsu8QQ0zRPHvqt12xEryVe9A2pN6/3KqyV7alydB5/ugr3bv4nRnWCbk5WNp28b5aFYvlNCWpwS4kS7O4Pj5dUdF5orX2D0yfwoIvyEpB/mpb7apOAlKb00qs7h95b7DQ7e5slvjLDp6+otmnJDmkRLZOi4qp1Gvdeqzzq5co18avU1NjWCGwkb/WWA6JhZIGqtP0mTTInLaKDvU8t2HXiSbAgMBAAECggEAXQ6RqHQSvla946xjmtMqhXVZM7qL3dq4AE0JNRTrOUGjtC0MfhSQuAI1/V7Od8NThZ4QCgyeQnsExenkp87xSMHm8KRjTvuXEletvuiqaLhqx35hyVVfRiJEe41nUc1+tuHIs4cv91PMIAZjpeopVCLnC5N8eoVS18531oCYpmrpAK1UWl4UU0ntE7nTmcmL6mdyLZM24OFcT2+byAGh4pY6M17xcwFAG1Q1O3kue+Uj1OyoPKR5I+vJsmaz0wW0Z/yjs3jRs/G4RZnlPf2b/qm8aoxClXwh75iexItbmpkS4t60M/FOobLJdbkE+j1HYFe8F7fYFCHHZ74nLZZ3UQKBgQDkb7z6i1l6VKli4dW8/KKykQQT4hXtpNIPzCfYOVLCg2xur55APEojG6v0zKheqG67AS/oI09xkWcv287YDFUH2hyVcHgBpcIf7DWfXTTzxQDEHl8bdDeQ6fVM6vczjupj1hc8099sPivqN8xLupBtTUdi+yyrx6qHTi4hDJWPbwKBgQCz3a7yxGKATeXzvxnPe/L4EFIM4nN/UHzOPWCzZRsU2nXteJfz+YRCPPZjYQGAJ9phd+zExvB/LOCzYtn378wn7qtZjBmHLmy9TJn+BzEEVJtTTSE5CSr6fGUDaXBXwYc6005C8QiJ3G09rsr1OzWxaD1/4wJuc4jaA/LW5CRnlQKBgQCHdE59zlWPeVY7oShJeRDMi7r8Q0q3cM1yup9B0rPlTWLlu1M+ScG4UbdEYHrSLlQCpXCP2ShJcmvacB+V8rIVBtt+LYOrjmuu3DTru6wjhESxBkVlFSPB1Uk0g/tzYiJQcumykvvfxnnp145XtVfgyDs1UTUq4ymWM+Ccjka9FwKBgG0c5X22bv7gwsf8PR8mVT3xQKU6VtB8Nkoy58/MXQJoiLO3vY6u5nH8Y4fkGDHze0I3qzWwbcLoqaOJKyuCmDn9Sot3NyFPo32u05hMKosy/EsrdiUQj7cG8YyBUTqbV3rzJv3rFL/1LXwG5m6gLbFiyDQltZrlkbaYQJ/mJMOFAoGBAOQZkBD6G0mS17A5LeIbOCiVpXL9poIUBlaDR180H211tckgBsPYtU7TEOYZAtIbv44e+xsb8W0A8oCHCzTMC43++1ri0YiLuqs2UjI1s9VNJOoIDZ7XXO7lWj4AOUtILA68f6XOoZ9FUjydqwizNTdVPEr8wHwktXvRliAQrile";
    private String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoH/xtecKPknlJfkD8VTiX7+Hi9J0Lc2sEyrmLxEgxVoOS4rilFw17arowPxSBcCOx/GX85S+8/nZxBV38nEqUjA1l2FaJyYv0faKpFmWLnOpK5/a+G9pDnfpq/Wd+17LvEENM0Tx76rddsRK8lXvQNqTev9yqsle2pcnQef7oK927+J0Z1gm5OVjadvG+WhWL5TQlqcEuJEuzuD4+XVHReaK19g9Mn8KCL8hKQf5qW+2qTgJSm9NKrO4feW+w0O3ubJb4yw6evqLZpyQ5pES2TouKqdRr3Xqs86uXKNfGr1NTY1ghsJG/1lgOiYWSBqrT9Jk0yJy2ig71PLdh14kmwIDAQAB";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sign = request.getHeader("sign");
        log.info(request.getHeader("sign"));
        log.info(request.getParameter("param1"));
        RequestWrapper requestWrapper = new RequestWrapper(request);
        String body = requestWrapper.getBody();
        log.info("客户原请求参数body={}", body);
        TreeMap<String, Object> requestMap = gson.fromJson(body,new TypeToken<TreeMap<String,Object>>(){}.getType());
        log.info("requestMap={}", requestMap);
//        RSAHelper cipher= new RSAHelper();
//        cipher.initKey(privateKey,publicKey,2048);
//        boolean flag = cipher.verifyRSA(gson.toJson(requestMap).getBytes("UTF-8"),Base64.decodeBase64(sign),false,"UTF-8");
//        System.out.println("======" + flag);
        boolean flag = TestRSA.verify(gson.toJson(requestMap),TestRSA.getPublicKey(publicKey) ,sign);
        System.out.println("======" + flag);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public static void main(String[] args) throws Exception {
         String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCgf/G15wo+SeUl+QPxVOJfv4eL0nQtzawTKuYvESDFWg5LiuKUXDXtqujA/FIFwI7H8ZfzlL7z+dnEFXfycSpSMDWXYVonJi/R9oqkWZYuc6krn9r4b2kOd+mr9Z37Xsu8QQ0zRPHvqt12xEryVe9A2pN6/3KqyV7alydB5/ugr3bv4nRnWCbk5WNp28b5aFYvlNCWpwS4kS7O4Pj5dUdF5orX2D0yfwoIvyEpB/mpb7apOAlKb00qs7h95b7DQ7e5slvjLDp6+otmnJDmkRLZOi4qp1Gvdeqzzq5co18avU1NjWCGwkb/WWA6JhZIGqtP0mTTInLaKDvU8t2HXiSbAgMBAAECggEAXQ6RqHQSvla946xjmtMqhXVZM7qL3dq4AE0JNRTrOUGjtC0MfhSQuAI1/V7Od8NThZ4QCgyeQnsExenkp87xSMHm8KRjTvuXEletvuiqaLhqx35hyVVfRiJEe41nUc1+tuHIs4cv91PMIAZjpeopVCLnC5N8eoVS18531oCYpmrpAK1UWl4UU0ntE7nTmcmL6mdyLZM24OFcT2+byAGh4pY6M17xcwFAG1Q1O3kue+Uj1OyoPKR5I+vJsmaz0wW0Z/yjs3jRs/G4RZnlPf2b/qm8aoxClXwh75iexItbmpkS4t60M/FOobLJdbkE+j1HYFe8F7fYFCHHZ74nLZZ3UQKBgQDkb7z6i1l6VKli4dW8/KKykQQT4hXtpNIPzCfYOVLCg2xur55APEojG6v0zKheqG67AS/oI09xkWcv287YDFUH2hyVcHgBpcIf7DWfXTTzxQDEHl8bdDeQ6fVM6vczjupj1hc8099sPivqN8xLupBtTUdi+yyrx6qHTi4hDJWPbwKBgQCz3a7yxGKATeXzvxnPe/L4EFIM4nN/UHzOPWCzZRsU2nXteJfz+YRCPPZjYQGAJ9phd+zExvB/LOCzYtn378wn7qtZjBmHLmy9TJn+BzEEVJtTTSE5CSr6fGUDaXBXwYc6005C8QiJ3G09rsr1OzWxaD1/4wJuc4jaA/LW5CRnlQKBgQCHdE59zlWPeVY7oShJeRDMi7r8Q0q3cM1yup9B0rPlTWLlu1M+ScG4UbdEYHrSLlQCpXCP2ShJcmvacB+V8rIVBtt+LYOrjmuu3DTru6wjhESxBkVlFSPB1Uk0g/tzYiJQcumykvvfxnnp145XtVfgyDs1UTUq4ymWM+Ccjka9FwKBgG0c5X22bv7gwsf8PR8mVT3xQKU6VtB8Nkoy58/MXQJoiLO3vY6u5nH8Y4fkGDHze0I3qzWwbcLoqaOJKyuCmDn9Sot3NyFPo32u05hMKosy/EsrdiUQj7cG8YyBUTqbV3rzJv3rFL/1LXwG5m6gLbFiyDQltZrlkbaYQJ/mJMOFAoGBAOQZkBD6G0mS17A5LeIbOCiVpXL9poIUBlaDR180H211tckgBsPYtU7TEOYZAtIbv44e+xsb8W0A8oCHCzTMC43++1ri0YiLuqs2UjI1s9VNJOoIDZ7XXO7lWj4AOUtILA68f6XOoZ9FUjydqwizNTdVPEr8wHwktXvRliAQrile";
         String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoH/xtecKPknlJfkD8VTiX7+Hi9J0Lc2sEyrmLxEgxVoOS4rilFw17arowPxSBcCOx/GX85S+8/nZxBV38nEqUjA1l2FaJyYv0faKpFmWLnOpK5/a+G9pDnfpq/Wd+17LvEENM0Tx76rddsRK8lXvQNqTev9yqsle2pcnQef7oK927+J0Z1gm5OVjadvG+WhWL5TQlqcEuJEuzuD4+XVHReaK19g9Mn8KCL8hKQf5qW+2qTgJSm9NKrO4feW+w0O3ubJb4yw6evqLZpyQ5pES2TouKqdRr3Xqs86uXKNfGr1NTY1ghsJG/1lgOiYWSBqrT9Jk0yJy2ig71PLdh14kmwIDAQAB";

        RSAHelper cipher = new RSAHelper();
        cipher.initKey(privateKey,publicKey,2048);
        Map<String,String> param = new HashMap<String,String>();
        param.put("param1","param1111");
        param.put("param2","中国好");
//        String sign =Base64.encodeBase64String(cipher.signRSA(gson.toJson(param).getBytes("UTF-8"),false,"UTF-8")) ;
//        System.out.println(sign);
//        boolean flag = cipher.verifyRSA(gson.toJson(param).getBytes("UTF-8"),Base64.decodeBase64(sign),false,"UTF-8");
//        System.out.println(flag);
        String sign = TestRSA.sign(gson.toJson(param),TestRSA.getPrivateKey(privateKey));
        System.out.println(sign);
    }
}
