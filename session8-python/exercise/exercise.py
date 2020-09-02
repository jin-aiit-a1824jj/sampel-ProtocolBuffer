import addressbook_pb2

def makePerson():
    person = addressbook_pb2.Person()
    person.id = 1234
    person.name = "John Doe"
    person.email = "jdoe@example.com"
    phone = person.phones.add()
    phone.number = "555-4321"
    phone.type = addressbook_pb2.Person.HOME
    person.phones.add(number="333-4444", type=addressbook_pb2.Person.WORK)
    return person

book = addressbook_pb2.AddressBook()
book.people.extend([makePerson()]) 
book.people.extend([makePerson()])
book.people.extend([makePerson()])


print(book)


with open("exercise.bin", "wb") as f:
    print("Write as binary")
    bytesAsString = book.SerializeToString()
    f.write(bytesAsString)

with open("exercise.bin", "rb") as f:
    print("read values")
    book_read = addressbook_pb2.AddressBook().FromString(f.read())

print(book_read)

# print("Is phone.type?: " + str(person_read.phones[0]))
# print("Is Id?: " + str(person_read.id))
