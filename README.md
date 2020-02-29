## 什么是OAuth2
OAuth是为了用户资源的授权定义了一个安全，开放及简单的标准，第三方无需知道用户的账号及密码，就可以获取用户的授权信息。
OAuth2.0是OAuth协议的延续版本，但不向后兼容OAuth1.0
![image](http://note.youdao.com/yws/res/18833/5421FEC5A0FB4A549F80B52C46BADAF2)

## 应用场景
第三方应用授权登录：在App或者网页接入一些第三方应用时，时常会需要用户登录另一个合作平台，比如QQ，微博，微信的授权登录，第三方应用通过oauth2方式获取用户信息。

## 运行流程

```
1. 第三方发起微信授权登录请求，微信用户允许授权第三方应用后，微信会拉起应用或重定向到第三方网站，并且带上授权临时票据code参数；

2. 通过code参数加上AppID和AppSecret等，通过API换取access_token；

3. 通过access_token进行接口调用，获取用户基本数据资源或帮助用户实现基本操作。
```

## 步骤说明
- 用户访问第三方网站，第三方应用需要用户登录验证，用户选择微信授权登录
- 第三方应用发起微信登录授权请求
```
https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
```
- 微信服务器拉起用户授权页面
- 用户授权通过
- 微信发送请求到第三方应用redirectUrl，返回凭证code与state
- 第三方应用获取到code之后，根据code获取accessToken
- accessToken获取用户信息
- 对用户信息进行处理(用户是否第一次登录，保存用户信息，自定义token，session处理等)
- 返回结果(步骤1对应url或者重定向到首页)

## 接口设计
**注册应用接口**


**登录Login**
- 调用login接口，参数如下
```
userName: "xinyu.huang02"
password: "sDW45daIrHy3o5trqNvOSFL4dPj6n06CK5DCANCgisk="
code: "202408"
appKey: "69a99e1f7eae50d9"
redir: "https%3A%2F%2Faut.liulishuo.work%2F"
```
- 验证用户信息是否存在，不存在返回报错信息；
- 验证短信验证码是否正确，不正确或者已经过期，返回报错信息；
- 验证appkey对应的app是否存在，不存在返回报错信息
- 调用app对应的redirectUrl

