#!/usr/bin/sh
protoc -I=simple/ --python_out=simple/ simple/simple.proto
