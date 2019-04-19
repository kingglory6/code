package com.newer.mall.common.utils;

public interface EmailSenderService {
    /**
     *	 发送文本消息
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param to 收件人(可变参数，多个时用逗号隔开)
     */
    void sendTextMail(String subject, String content, String... to);
    /**
     * 	发送带附件邮件
     * @param subject 主题
     * @param content 内容
     * @param filePath 附件得本地路径
     * @param fileName 附件名
     * @param to 收件人
     */
    void sendAttachmentMail(String subject, String content, String filePath, String fileName, String... to);
    
}
