# jee6-jms-sender

Simple app to send text messages to JMS queue with mapped name jms/TestQueue.

To add test JMS connection factory to Glassfish 3.1.2.2:
```sh
asadmin create-jms-resource --restype javax.jms.ConnectionFactory --description "test connection factory" jms/TestConnectionFactory
```
To add test queue to OpenMQ managed by Glassfish 3.1.2.2:
```sh
asadmin create-jms-resource --restype javax.jms.Queue --property Name=TestQueue jms/TestQueue
```
