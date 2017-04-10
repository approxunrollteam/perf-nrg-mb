#!/usr/bin/env bash

cd ${compile_folder}
mvn clean
mvn install -Dmaven.test.skip=true -Dmaven.javadoc.skip=true

if [ $? -eq 0 ]; then
    echo "Maven succeed"
else
    echo $?
    echo "Maven failed"
    exit -1
fi

cd /media/elmarce/Windows/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/openimaj-master/demos/approxdemos
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
java -cp approxdemos-1.4-SNAPSHOT-jar-with-dependencies.jar org.openimaj.approxdemos.KLTHaarFaceTrackerDemoApproxEval

if [ $? -eq 0 ]; then
    echo "Accuracy measurement succeed"
else
    echo "Accuracy measurement failed"
    exit -1
fi

