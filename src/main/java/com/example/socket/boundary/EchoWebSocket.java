package com.example.socket.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/echo/{id}")
@Component
public class EchoWebSocket {

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoWebSocket.class);

    @OnOpen
    public void onOpen(Session session) {
        LOGGER.info("onOpen " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("id") int id) {
        try {
            LOGGER.info("onMessage From=" + session.getId());
            LOGGER.info("onMessage Message=" + message);
            LOGGER.info("onMessage ID=" + id);

//            Echo echo = new Echo();
//            echo.setText(message);
            //echoRepository.saveAndFlush(echo);

            session.getBasicRemote().sendText(message.toUpperCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        LOGGER.info("onClose " + session.getId());
    }

    @OnError
    public void onError(Throwable t) {
        LOGGER.error(t.getMessage(), t);
    }
}