package main

import (
	"fmt"
	"io/ioutil"
	"log"

	"./src/exercise"
	"github.com/golang/protobuf/jsonpb"
	"github.com/golang/protobuf/proto"
)

func main() {
	// fmt.Println("hello exercise")
	// sm := doSimple()
	// readAndWriteDemo(sm)
	// jsonDemo(sm)
	doComplex()
}

func doSimple() *exercise.Person {
	john := exercise.Person{
		Id:    1234,
		Name:  "John Doe",
		Email: "jdoe@example.com",
		Phones: []*exercise.Person_PhoneNumber{
			{Number: "555-4321", Type: exercise.Person_HOME},
		},
	}

	return &john
}

func writeToFile(fname string, pb proto.Message) error {
	out, err := proto.Marshal(pb)
	if err != nil {
		log.Fatalln("Can't serialise to bytes", err)
		return err
	}

	if err := ioutil.WriteFile(fname, out, 0644); err != nil {
		log.Fatalln("Can't write to file", err)
		return err
	}

	fmt.Println("Data hs been written!")
	return nil
}

func readFromFile(fname string, pb proto.Message) error {
	in, err := ioutil.ReadFile(fname)
	if err != nil {
		log.Fatalln("Something went wrong when reading the file", err)
		return err
	}

	err2 := proto.Unmarshal(in, pb)
	if err != nil {
		log.Fatalln("Couldn't put the bytes into the protocol buffers struct", err2)
		return err2
	}

	return nil
}

func readAndWriteDemo(sm proto.Message) {

	writeToFile("exercise.bin", sm)

	sm2 := &exercise.Person{}
	readFromFile("exercise.bin", sm2)
	fmt.Println("Read the content:\n", sm2)
}

func toJSON(pb proto.Message) string {
	marshaler := jsonpb.Marshaler{}
	out, err := marshaler.MarshalToString(pb)
	if err != nil {
		log.Fatalln("Can't convert to Json", err)
		return ""
	}
	return out
}

func fromJSON(in string, pb proto.Message) {
	err := jsonpb.UnmarshalString(in, pb)
	if err != nil {
		log.Fatalln("Couldn't unmarshal the JSON into the pb struct", err)
	}
}

func jsonDemo(sm proto.Message) {
	smAsString := toJSON(sm)
	fmt.Println(smAsString)

	sm2 := &exercise.Person{}
	fromJSON(smAsString, sm2)
	fmt.Println("Successfully created proto struct:\n", sm2)
}

func doComplex() {
	book := exercise.AddressBook{
		People: []*exercise.Person{
			doSimple(),
			doSimple(),
			doSimple(),
			doSimple(),
			doSimple(),
		},
	}

	//fmt.Println(&book)
	fmt.Println(book)
}
