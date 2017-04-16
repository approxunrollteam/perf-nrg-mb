#!/usr/bin/env bash

mvn assembly:assembly -DdescriptorId=jar-with-dependencies
if [ $? -eq 0 ]; then
    echo "Maven succeed"
else
    echo $?
    echo "Maven failed"
    exit -1
fi

rm src/main/java/PerformanceRunner.java

cd target/

java -cp small-loop-1.0-SNAPSHOT-jar-with-dependencies.jar PerformanceRunner
if [ $? -eq 0 ]; then
    echo "MB succeed"
else
    echo $?
    echo "MB failed"
    exit -1
fi

