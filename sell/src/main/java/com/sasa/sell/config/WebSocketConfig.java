package com.sasa.sell.config;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Component
public class WebSocketConfig {

    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
