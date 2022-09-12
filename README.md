# My-News
# 一、项目介绍
本项目是一款基于renren后端API而开发的视频资讯APP

项目地址：https://github.com/2462370039/My-News

# 二、项目功能
- [x] 登录注册
- [x] 首页
- [x] 视频页
- [x] 视频播放器
- [x] 视频点赞收藏
- [x] 资讯页
- [ ] 资讯详情页
- [x] 个人页面
- [x] 我的收藏

# 三、项目实现
## 技术点
- 项目使用MVC模式作架构
- OkHttp作为项目的数据传输和处理
- Picasso作为图片加载框架
- [DKVideoPlayer](https://github.com/Doikki/DKVideoPlayer)作为项目视频播放器
- 使用Git作为整个APP项目的代码版本控制

## post请求
项目使用OkHttp作网络请求框架,封装成网络请求类Api<br>
Android数据存储类SharedPreferences存储登录令牌token
```java
/**
 * post请求
 * @param callback 回调接口
 */
public void postRequest(Context context,TtitCallback callback){
    SharedPreferences sp = context.getSharedPreferences("sp_tzh", MODE_PRIVATE);
    String token = sp.getString("token", "");
    JSONObject jsonObject = new JSONObject(mParams);
    String jsonStr = jsonObject.toString();
    RequestBody requestBodyJson =
            RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonStr);

    //3、创建Request
    Request request = new Request.Builder()
            .url(requestUrl)
            .addHeader("contentType", "application/json; charset=UTF-8")
            .addHeader("token", token)
            .post(requestBodyJson)
            .build();

    //4、创建call回调对象
    final Call call = client.newCall(request);

    //5、发起请求
    call.enqueue(new Callback() {
        @Override
        public void onFailure(@NonNull Call call, @NonNull IOException e) {
            Log.e("onFailure", e.getMessage());
            callback.onFailure(e);
        }

        @Override
        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            final String result = response.body().string();
            callback.onSuccess(result);
        }
    });
}
```


