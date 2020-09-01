package simple.proto;

import java.util.Arrays;

import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.InvalidProtocolBufferException;

import example.simple.Simple.SimpleMessage;

public class ProtoToJSONMain {
    public static void main(String[] args) {
        
        SimpleMessage.Builder builder = SimpleMessage.newBuilder();

        // simple fields
        builder
            .setId(42) // setting an ID
            .setIsSimple(true) // setting an is_simple field
            .setName("My Simple Message Name"); // setting the name

        // repeated field
        builder.addSampleList(1)
            .addSampleList(2)
            .addSampleList(3)
            .addAllSampleList(Arrays.asList(4,5,6));

        // Print this as a JSON
        try {
            String jsonString = JsonFormat.printer().print(builder);
            System.out.println(jsonString);

            // parse JSON into Protobuf
            SimpleMessage.Builder builder2 = SimpleMessage.newBuilder();
            JsonFormat.parser().ignoringUnknownFields().merge(jsonString, builder2);
            System.out.println(builder2);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        
    }
}
