package ru.maratk;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Stateless
@Path("/")
public class JmsSender {

    @Resource(name="jms/TestConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(name="jms/TestQueue")
    private Destination destination;

    @GET
    public Response get(@DefaultValue("Hello!") @QueryParam("message") String messageText) {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage();
            message.setText(messageText);
            producer.send(message);
            session.close();
            connection.close();
        } catch (JMSException ex) {
            ex.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok().build();
    }
}