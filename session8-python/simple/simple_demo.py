import simple_pb2 as simple_pb2

simple_message = simple_pb2.SimpleMessage()
simple_message.id = 123
simple_message.is_simple = False
simple_message.name = "This is a simple Message"
sample_list = simple_message.sample_list
sample_list.append(3)
sample_list.append(4)
sample_list.append(5)

print(sample_list)
print(simple_message)

with open("simple.bin", "wb") as f:
    print("Write as binary")
    bytesAsString = simple_message.SerializeToString()
    f.write(bytesAsString)

with open("simple.bin", "rb") as f:
    print("read values")
    simple_message_read = simple_pb2.SimpleMessage().FromString(f.read())

print(simple_message_read)

print("Is Simple?: " + str(simple_message.is_simple))
print("Is Id?: " + str(simple_message.id))
