package simple.proto;

import java.util.Arrays;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.tutorial.AddressBookProtos.*;

import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.InvalidProtocolBufferException;

public class ExerciseMain {
    public static void main(String[] args) {

        Person john = Person.newBuilder()
                            .setId(1234)
                            .setName("John Doe")
                            .setEmail("jdoe@example.com")
                            .addPhones(
                            Person.PhoneNumber.newBuilder()
                                .setNumber("555-4321")
                                .setType(Person.PhoneType.HOME))
                            .build();

        System.out.println(john);
        
        

        // write the protocol buffers binary to a file
        try {
            System.out.println("Write bin file...");
            FileOutputStream outputStream = new FileOutputStream("exercise.bin");
            john.writeTo(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // send as byte array
        //byte[] bytes = message.toByteArray();
        
        try {
            System.out.println("Reading from file...");
            FileInputStream fileInputStream = new FileInputStream("exercise.bin");
            Person messageFromFile = Person.parseFrom(fileInputStream);
            System.out.println(messageFromFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print this as a JSON
        try {
            System.out.println("Print this as a JSON...");
            String jsonString = JsonFormat.printer().print(john);
            System.out.println(jsonString);

            // parse JSON into Protobuf
            Person.Builder builder2 = Person.newBuilder();
            JsonFormat.parser().ignoringUnknownFields().merge(jsonString, builder2);
            System.out.println(builder2);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }   
}
