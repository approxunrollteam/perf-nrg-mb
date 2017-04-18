#!/usr/bin/env bash

function runmaven {
    cd $2
    mvn clean

    if [ $1 -eq 1 ]; then
        mvn assembly:assembly -q -DdescriptorId=jar-with-dependencies -Dmaven.test.skip=true -Dmaven.javadoc.skip=true
    else
        mvn install -q -Dmaven.test.skip=true -Dmaven.javadoc.skip=true
    fi

    if [ $? -eq 0 ]; then
        echo "Maven succeed"
    else
        echo $?
        echo "Maven failed"
        exit -1
    fi
}

function runsearch {
    java -cp mylucenedemo-1.0-SNAPSHOT-jar-with-dependencies.jar fr.inria.diverse.SearchFiles -index /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_DATA/TEXT/index -query $1 -paging 1000 > /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/OUTPUT/lucen2/$2.$3.$1
    if [ $? -eq 0 ]; then
        echo "Search program - Accuracy measurement succeed"
    else
        echo "Search program - Accuracy measurement failed"
    fi
}

runmaven 0 /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/lucene-solr-master/maven-build/lucene/core/
runmaven 0 /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/lucene-solr-master/maven-build/lucene/queryparser/
runmaven 1 /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/mylucenedemo/

rm -r /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_DATA/TEXT/index

if [ $? -eq 0 ]; then
    echo "Index files removed! OK"
else
    echo "Unable to remove Index files!"
    exit -1
fi

cd /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/mylucenedemo/target/
ls
java -cp mylucenedemo-1.0-SNAPSHOT-jar-with-dependencies.jar fr.inria.diverse.IndexFiles -docs /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_DATA/TEXT/documents -index /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_DATA/TEXT/index

if [ $? -eq 0 ]; then
    echo "Index program - Accuracy measurement succeed"
else
    echo "Index program - Accuracy measurement failed"
    exit -1
fi

runsearch WWII $1 $2
runsearch atomic $1 $2
runsearch electron $1 $2
runsearch Napoleon $1 $2
runsearch Einstein $1 $2
runsearch relativity $1 $2
runsearch painting $1 $2
runsearch France $1 $2
runsearch modernism $1 $2
runsearch WWI $1 $2




