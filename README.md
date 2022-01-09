## Producer.java

### To produce message as object
Add your message in ProducerRecord
```java
for (int i = 1; i <= 10; i++){
    User user=new User(i,"Prakhar",25,"Btech");
    kafkaProducer.send(new ProducerRecord("prakhar-topic",String.valueOf(user.getId()),user));
}
```
Here `prakhar-topic` is name of ***topic*** & `user` is object of **User** class

## Consumer.java
### To see consuming messages
Make sure you subscribed to the topic `prakhar-topic`
```java
List topics = new ArrayList();
topics.add("prakhar-topic");
kafkaConsumer.subscribe(topics);
```
### Writing Data into a file when consuming it
```java

while (true) {
        FileWriter fileWriter = new FileWriter("user-json-data.txt", true);
        ConsumerRecords<String, User> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));
        for (ConsumerRecord<String, User> consumerRecord : consumerRecords) {
            System.out.printf(
                "Topic: %s, Partition: %d, Value: %s%n",
                consumerRecord.topic(),
                consumerRecord.partition(),
                consumerRecord.value().toString());
            fileWriter.write(consumerRecord.value().toString() + "\n");
        }
        fileWriter.flush();
        fileWriter.close();
}
```
### Then
Just run the `Consumer.java` file. A terminal will open and there you can see your all messages from beginning produced by Producer.

### In user-json-data.txt
> {"id":1, "name":"Prakhar", "age":25, "course":"Btech"}
> 
> {"id":2, "name":"Prakhar", "age":25, "course":"Btech"}
> 
> {"id":3, "name":"Prakhar", "age":25, "course":"Btech"}
> 
> {"id":4, "name":"Prakhar", "age":25, "course":"Btech"}
> 
> {"id":5, "name":"Prakhar", "age":25, "course":"Btech"}
> 

