# jee6-jms-sender

Simple app to send text messages to JMS queue with mapped name jms/TestQueue.

To listen messages sended by this app you can use [jee6-jms-receiver](https://github.com/MaratKarimov/jee6-jms-receiver) app.

To add test JMS connection factory to Glassfish 3.1.2.2:
```sh
asadmin create-jms-resource --restype javax.jms.ConnectionFactory --description "test connection factory" jms/TestConnectionFactory
```
To add JMS test queue to OpenMQ managed by Glassfish 3.1.2.2:
```sh
asadmin create-jms-resource --restype javax.jms.Queue --property Name=TestQueue jms/TestQueue
```
Also you can define JMS connection factory and JMS test queue resources by glassfish-resources.xml, but you can't share this resources with another apps:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE resources PUBLIC "-//GlassFish.org//DTD GlassFish Application Server 3.1 Resource Definitions//EN" "http://glassfish.org/dtds/glassfish-resources_1_5.dtd">
<resources>
    <!-- JMS test queue with name TestQueue definition -->
    <admin-object-resource res-adapter="jmsra" res-type="javax.jms.Queue" jndi-name="jms/TestQueue"  object-type="system-all">
        <property name="Name" value="TestQueue" />
    </admin-object-resource>

    <!-- JMS test connection factory with name jms/TestConnectionFactory definition -->
    <connector-resource enabled="true" jndi-name="jms/TestConnectionFactory" object-type="system-all" pool-name="jms/TestConnectionFactoryPool">
        <description/>
    </connector-resource>

    <!-- JMS test connection factory pool with name jms/TestConnectionFactoryPool definition -->
    <connector-connection-pool
            associate-with-thread="false"
            connection-creation-retry-attempts="0"
            connection-creation-retry-interval-in-seconds="10"
            connection-definition-name="javax.jms.ConnectionFactory"
            connection-leak-reclaim="false"
            connection-leak-timeout-in-seconds="0"
            fail-all-connections="false"
            idle-timeout-in-seconds="300"
            is-connection-validation-required="false"
            lazy-connection-association="false"
            lazy-connection-enlistment="false"
            match-connections="true"
            max-connection-usage-count="0"
            max-pool-size="4"
            max-wait-time-in-millis="60000"
            name="jms/TestConnectionFactoryPool"
            ping="false"
            pool-resize-quantity="2"
            pooling="true"
            resource-adapter-name="jmsra"
            steady-pool-size="2"
            validate-atmost-once-period-in-seconds="0"/>
</resources>
```
