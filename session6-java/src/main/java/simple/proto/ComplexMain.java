package simple.proto;

import java.util.Arrays;

import example.complex.Complex.*;

public class ComplexMain {
    public static void main(String[] args) {
        System.out.println("Complex example");

        DummyMessage oneDummy = newDummyMessage(55, "one dummy message");

       
        ComplexMessage.Builder builder = ComplexMessage.newBuilder();
         // singular message field
        builder.setOneDummy(oneDummy);

        // repeated field
        builder.addMultipleDummy(newDummyMessage(66, "second message"));
        builder.addMultipleDummy(newDummyMessage(67, "third message"));
        builder.addMultipleDummy(newDummyMessage(68, "forth message"));

        builder.addAllMultipleDummy(Arrays.asList(
            newDummyMessage(79, "other message"),
            newDummyMessage(70, "other other message")
        ));

        ComplexMessage message = builder.build();
        
        System.out.println(message.toString());

        //GET EXAMPLE
        message.getMultipleDummyList();

    }

    public static DummyMessage newDummyMessage(int id, String name) {
        // same learning as "SimpleMain"
        DummyMessage.Builder dummyMessageBuilder = DummyMessage.newBuilder();

        DummyMessage message = dummyMessageBuilder
                                    .setName(name)
                                    .setId(id)
                                    .build();
        return message;
    }
}
