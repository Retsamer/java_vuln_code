package com.cinderella.demo.util.ssrfHook;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author Cinderella
 * @time 2021/8/23 16:39
 * @Description
 **/
public class SocketHook {

    public static void startHook() throws IOException {
        SocketHookFactory.initSocket();
        SocketHookFactory.setHook(true);
        try{
            Socket.setSocketImplFactory(new SocketHookFactory());
        }catch (SocketException ignored){
        }
    }

    public static void stopHook(){
        SocketHookFactory.setHook(false);
    }
}
