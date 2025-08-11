package com.example.notificationservice.serivce;

import com.example.notificationservice.event.OrderPlacedEvent;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j(topic = "EMAIL-SERVICE")
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendOrderEmail(OrderPlacedEvent event) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("hello@demomailtrap.co");
            helper.setTo("son.lyhoang2014@gmail.com");
            helper.setSubject("Đơn hàng mới #" + event.getOrderId());

            String body = "<p>Xin chào userId <b>" + event.getUserId() + "</b></p>," +
                    "<p>Đơn hàng #" + event.getOrderId() + " vừa được tạo thành công!</p>," +
                    "<p>Tổng tiền: <b>$" + event.getTotal() + "</b></p>";
            helper.setText(body, true);

            mailSender.send(message);
            log.info("Gửi email thành công!");
        } catch (Exception e) {
            log.error("Gửi email thất bại: {}", e.getMessage());
        }
    }
}
