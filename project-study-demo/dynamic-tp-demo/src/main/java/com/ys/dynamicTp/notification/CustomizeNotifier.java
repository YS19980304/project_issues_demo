package com.ys.dynamicTp.notification;

import lombok.extern.slf4j.Slf4j;
import org.dromara.dynamictp.common.entity.NotifyPlatform;
import org.dromara.dynamictp.common.notifier.AbstractNotifier;

@Slf4j
public class CustomizeNotifier extends AbstractNotifier {


    public CustomizeNotifier() {
        System.out.println(" ===========> init CustomizeNotifier");
    }

    /**
     * 平台名称
     *
     * @return
     */
    @Override
    public String platform() {
        return "customize";
    }


    /**
     * 自定义发送消息
     *
     * @param platform platform
     * @param content  content
     */
    @Override
    protected void send0(NotifyPlatform platform, String content) {
        log.info("======>platform configuration information :: {}", platform);
        log.info("======>content :: {}", content);

        // 调用自定义接口发送信息
    }
}