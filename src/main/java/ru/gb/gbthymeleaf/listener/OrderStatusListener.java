package ru.gb.gbthymeleaf.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.gb.gbapi.events.OrderEvent;
import ru.gb.gbshopproject.config.JmsConfig;
import ru.gb.gbshopproject.sender.MailService;

@RequiredArgsConstructor
@Component
public class OrderStatusListener {

    private final MailService mailService;

    @JmsListener(destination = JmsConfig.ORDER_STATUS_CHANGED_QUEUE)
    public void listen(@Payload OrderEvent orderEvent){
        mailService.sendSimpleMessage(orderEvent.getOrderDto().getMail(), "Order status changed", orderEvent.getOrderDto().toString());
    }

}
