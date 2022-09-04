package com.phenom.swingview.window;

import com.phenom.PhenomApplication;
import com.phenom.swingview.constant.UIConstant;
import com.phenom.swingview.eventlistener.ListenerUtil;
import com.phenom.swingview.jpanel.LoginPanel;
import org.springframework.boot.SpringApplication;
import javax.swing.*;
import java.awt.*;

/**
 * RPA执行器-登录窗体
 * @author olic
 * @date 2022/8/2914:50
 */
public class LoginFrame extends JFrame {

    /**
     * 启动客户端
     */
    public void start(){
        //窗体初始化
        init();
        addComponent();
        //设置窗体是否可见，刷新加载组件。注：需要在加载所有组件之后调用该方法，才可以正常显示组件
        setVisible(true);
        //窗体关闭监听
        ListenerUtil.closeConfirm(this);

        //run
        SpringApplication.run(PhenomApplication.class);
    }

    /**
     * 初始化窗体
     */
    public void init(){
        setTitle(UIConstant.LOGIN_WINDOW_TITLE);
        //设置窗体禁止调整大小
        setResizable(false);
        //设置主窗体位置、大小
        setBounds(UIConstant.getLoginWindowX(),UIConstant.getLoginWindowwY(),UIConstant.LOGIN_WINDOW_WIDTH,
                UIConstant.LOGIN_WINDOW_HEIGHT);
        //无任何操作(不隐藏窗体也不停止程序)
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    /**
     * 添加组件
     */
    public void addComponent(){
        Container c = getContentPane();
        c.setBackground(UIConstant.COLOR_TWO);
        c.setLayout(new GridLayout(1,1));
        c.add(new LoginPanel(this));
    }
}