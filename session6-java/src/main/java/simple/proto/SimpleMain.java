package simple.proto;

import example.simple.Simple.SimpleMessage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class SimpleMain {
    public static void main(String[] args){
        System.out.println("Hello Gradle!!");

        SimpleMessage.Builder builder = SimpleMessage.newBuilder();
        
        
        // builder.setId(42);
        // builder.setIsSimple(true);
        // builder.setName("My Simple Message Name");

        // simple fields
        builder
            .setId(42) // setting an ID
            .setIsSimple(true) // setting an is_simple field
            .setName("My Simple Message Name"); // setting the name

        // repeated field
        builder.addSampleList(1)
            .addSampleList(2)
            .addSampleList(3);

        builder.addAllSampleList(Arrays.asList(4,5,6));
        //builder.setSampleList(3, 42);


        System.out.println(builder.toString());


        SimpleMessage message = builder.build();

        // write the protocol buffers binary to a file
        try {
            FileOutputStream outputStream = new FileOutputStream("simple_message.bin");
            message.writeTo(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // send as byte array
        //byte[] bytes = message.toByteArray();
        
        try {
            System.out.println("Reading from file...");
            FileInputStream fileInputStream = new FileInputStream("simple_message.bin");
            SimpleMessage messageFromFile = SimpleMessage.parseFrom(fileInputStream);
            System.out.println(messageFromFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }
}
