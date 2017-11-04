package com.qiaobei.hmp.modules.listener;

import com.squareup.okhttp.*;
import com.xiaoleilu.hutool.Log;
import com.xiaoleilu.hutool.log.LogWrapper;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

import java.io.IOException;
public class HisSessionListener extends SessionListenerAdapter {

    private LogWrapper logger = Log.get(getClass());

    @Override
    public void onExpiration(Session session) {
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder()
                .add("sessionId", session.getId().toString())
                .build();
        final Request request = new Request.Builder()
                .url("http://websocket.yijiazhen.com" + "/api/sessionExpired")
                .post(formBody)
                .build();
        final Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                logger.error(e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                logger.info("websocket url post success");
            }
        });
        super.onExpiration(session);
    }
}
