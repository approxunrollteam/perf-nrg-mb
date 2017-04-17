#!/usr/bin/env bash

function runmaven {
    cd $2
    mvn clean

    if [ $1 -eq 1 ]; then
        mvn assembly:assembly -DdescriptorId=jar-with-dependencies -Dmaven.test.skip=true -Dmaven.javadoc.skip=true
    else
        mvn install -Dmaven.test.skip=true -Dmaven.javadoc.skip=true
    fi


    if [ $? -eq 0 ]; then
        echo "Maven succeed"
    else
        echo $?
        echo "Maven failed"
        exit -1
    fi
}

runmaven 0 /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/lucene-solr-master/maven-build/lucene/analysis/common/
runmaven 0 /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/lucene-solr-master/maven-build/lucene/core/
runmaven 0 /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/lucene-solr-master/maven-build/lucene/expressions/
runmaven 0 /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/lucene-solr-master/maven-build/lucene/facet/
runmaven 0 /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/lucene-solr-master/maven-build/lucene/queries/
runmaven 0 /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/lucene-solr-master/maven-build/lucene/queryparser/
runmaven 0 /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/lucene-solr-master/maven-build/lucene/codecs/
runmaven 1 /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/mylucenedemo/



cd /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/mylucenedemo/target/
ls
java -cp mylucenedemo-1.0-SNAPSHOT-jar-with-dependencies.jar fr.inria.diverse.IndexFiles -docs /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_DATA/TEXT/documents

if [ $? -eq 0 ]; then
    echo "Index program - Accuracy measurement succeed"
else
    echo "Index program - Accuracy measurement failed"
    exit -1
fi

java -cp mylucenedemo-1.0-SNAPSHOT-jar-with-dependencies.jar fr.inria.diverse.SearchFiles -index /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_DATA/TEXT/index -query string Napoleon -paging 1000 > Napoleon_$1

if [ $? -eq 0 ]; then
    echo "Search program - Accuracy measurement succeed"
else
    echo "Search program - Accuracy measurement failed"
fi

rm -r /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_DATA/TEXT/index

if [ $? -eq 0 ]; then
    echo "Index files removed! OK"
else
    echo "Unable to remove Index files!"
    exit -1
fi
