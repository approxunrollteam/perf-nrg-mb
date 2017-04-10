#!/usr/bin/env bash

cd /media/elmarce/Windows/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/smile-master/math
mvn clean
mvn install -Dmaven.test.skip=true -Dmaven.javadoc.skip=true

if [ $? -eq 0 ]; then
    echo "Maven succeed"
else
    echo $?
    echo "Maven failed"
    exit -1
fi

cd /media/elmarce/Windows/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/smile-master/data
mvn clean
mvn install -Dmaven.test.skip=true -Dmaven.javadoc.skip=true

if [ $? -eq 0 ]; then
    echo "Maven succeed"
else
    echo $?
    echo "Maven failed"
    exit -1
fi

cd /media/elmarce/Windows/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/smile-master/core
mvn clean
mvn install -Dmaven.test.skip=true -Dmaven.javadoc.skip=true

if [ $? -eq 0 ]; then
    echo "Maven succeed"
else
    echo $?
    echo "Maven failed"
    exit -1
fi

cd /media/elmarce/Windows/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/smile-master/demo
mvn assembly:assembly -DdescriptorId=jar-with-dependencies -Dmaven.test.skip=true -Dmaven.javadoc.skip=true

if [ $? -eq 0 ]; then
    echo "Maven succeed"
else
    echo $?
    echo "Maven failed"
    exit -1
fi

#rm src/main/java/PerformanceRunner.java
cd target/
ls
java -Dsmile.home=/media/elmarce/Windows/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/smile-master/shell/src/universal/bin -cp smile-demo-1.3.0-jar-with-dependencies.jar  smile.demo.classification.AdaBoostUSPS

if [ $? -eq 0 ]; then
    echo "Accuracy measurement succeed"
else
    echo "Accuracy measurement failed"
    exit -1
fi

