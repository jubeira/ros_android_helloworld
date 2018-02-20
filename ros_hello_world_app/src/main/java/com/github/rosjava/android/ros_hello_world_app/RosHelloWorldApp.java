package com.github.rosjava.android.ros_hello_world_app;

import android.app.Activity;
import android.os.Bundle;

import org.ros.android.RosActivity;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

public class RosHelloWorldApp extends RosActivity
{
    public RosHelloWorldApp() {
        this("RosHelloWorldApp", "RosHelloWorldApp");
    }

    protected RosHelloWorldApp(String notificationTicker, String notificationTitle) {
        super(notificationTicker, notificationTitle);
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected void init(NodeMainExecutor nodeMainExecutor) {
        Talker talker = new Talker();
        NodeConfiguration nodeConfiguration = NodeConfiguration.newPublic(getRosHostname(), getMasterUri());
        nodeConfiguration.setNodeName("talker");
        nodeMainExecutor.execute(talker, nodeConfiguration);

        Listener listener = new Listener();
        nodeConfiguration.setNodeName("listener");
        nodeMainExecutor.execute(listener, nodeConfiguration);
    }
}
