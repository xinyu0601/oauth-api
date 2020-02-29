package com.xin.oauth.models.ao;

import com.xin.oauth.models.bo.TicketBO;
import lombok.Builder;

/**
 * @author xinyu.huang02
 * @date 2020-02-28 11:38
 * @class Ticket AO
 */

@Builder
public class TicketAO {

    private String ticket;

    public static TicketAO fromBO(TicketBO ticketBO) {
        return TicketAO.builder().ticket(ticketBO.getTicket()).build();
    }
}
