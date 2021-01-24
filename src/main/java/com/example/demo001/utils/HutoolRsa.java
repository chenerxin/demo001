package com.example.demo001.utils;


import cn.hutool.crypto.asymmetric.RSA;

/**
 * @Description: TODO
 * @Author: erxin.chen
 * @Date: 2021/1/24 15:10
 */
public class HutoolRsa {

    public static void main(String[] args) {
        RSA rsa = new RSA();
        System.out.println(rsa.getPrivateKey());
        System.out.println(rsa.getPublicKey());

    }
}
