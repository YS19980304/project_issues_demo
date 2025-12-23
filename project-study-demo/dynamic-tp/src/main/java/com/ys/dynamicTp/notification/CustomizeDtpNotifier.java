package com.ys.dynamicTp.notification;

import org.apache.commons.lang3.tuple.Pair;
import org.dromara.dynamictp.core.notifier.AbstractDtpNotifier;

/**
 *
 */
public class CustomizeDtpNotifier extends AbstractDtpNotifier {

    public CustomizeDtpNotifier() {
        super(new CustomizeNotifier());
        System.out.println(" ===========>  init CustomizeDtpNotifier");
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

    @Override
    protected String getNoticeTemplate() {
        return CustomizeNotifyConst.CUSTOMIZE_NOTICE_TEMPLATE;
    }

    @Override
    protected String getAlarmTemplate() {
        return CustomizeNotifyConst.CUSTOMIZE_ALARM_TEMPLATE;
    }

    @Override
    protected Pair<String, String> getColors() {
        return null;
    }
}