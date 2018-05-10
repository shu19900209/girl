package com.sasa.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@ServerEndpoint("/webSocket")
@Component
public class WebSocket {
    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSockets.add(this);
        log.info("【websocket消息】有新的连接，总数={}",webSockets.size());
    }
    @OnClose
    public void onClose(){
        log.info("【websocket消息】 webSockets={}", webSockets.contains(this));
        webSockets.remove(this);
        log.info("【websocket消息】连击断开，总数{}",webSockets.size());
    }
    @OnMessage
    public void onMessage(String message){
        log.info("【websocket消息】收到客户端发来的消息{}",message);
    }

    public void sendMesage(String message){
        for(WebSocket webSocket: webSockets){
            log.info("websoccket消息 广播消息，message={}",message);
            try{
                webSocket.session.getBasicRemote().sendText(message);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
