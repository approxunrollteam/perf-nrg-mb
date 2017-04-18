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

#runmaven 0 /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/lucene-solr-master/maven-build/lucene/analysis/common/
#runmaven 0 /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/lucene-solr-master/maven-build/lucene/core/
#runmaven 0 /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/lucene-solr-master/maven-build/lucene/expressions/
#runmaven 0 /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/lucene-solr-master/maven-build/lucene/facet/
#runmaven 0 /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/lucene-solr-master/maven-build/lucene/queries/
#runmaven 0 /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/lucene-solr-master/maven-build/lucene/queryparser/
#runmaven 0 /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/lucene-solr-master/maven-build/lucene/codecs/
#runmaven 1 /home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/mylucenedemo/