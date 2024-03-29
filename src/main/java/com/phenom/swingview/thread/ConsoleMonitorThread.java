package com.phenom.swingview.thread;

import com.phenom.swingview.config.RpaExeConsoleAppender;
import com.phenom.swingview.constant.ExeConstant;
import com.phenom.swingview.jpanel.ConsolePanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author olic
 * @Description rpaExe控制台日志
 * @date 2022/5/28 11:11
 */
public class ConsoleMonitorThread{

    private static Logger logger = LoggerFactory.getLogger(ConsoleMonitorThread.class);
    private static ConsoleMonitorThread instance = new ConsoleMonitorThread();

    public static ConsoleMonitorThread getInstance(){
        return instance;
    }
    private Thread monitorThread;
    private volatile boolean toStop = false;

    public void start(){
        monitorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!toStop) {
                    try {
                        String line = RpaExeConsoleAppender.bufferedReader.readLine();
                        if (ConsolePanel.jTextArea != null) {
                            //控制台日志超过1000行刷新
                            if (ConsolePanel.jTextArea.getLineCount() > ExeConstant.CONSOLE_LOG_MAX_NUM) {
                                int end = ConsolePanel.jTextArea.getLineEndOffset(0);
                                ConsolePanel.jTextArea.replaceRange("", 0, end);
                            }
                            ConsolePanel.jTextArea.append(line + "\n");
                            //滚动条自动滚到最底部
                            ConsolePanel.jTextArea.setCaretPosition(ConsolePanel.jTextArea.getText().length());
                        }
                    } catch (Exception e) {
                    }
                }
                logger.error(">>>>>>>>>>> rpaExe, console log monitor thread stop");
            }
        });
        //设置守护线程。主线程结束结束，子线程也结束
        monitorThread.setDaemon(true);
        monitorThread.setName("rpaExe, console log monitor ConsoleMonitorThread");
        monitorThread.start();
    }

    public void toStop(){
        toStop = true;
    }
}

