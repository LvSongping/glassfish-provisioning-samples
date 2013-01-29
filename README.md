glassfish-provisioning-samples
==============================

glassfish-provisioning-samples

## Design  

Totol 6 bundles: a_api, a_impl, b_api, b_impl, c_api, c_impl

Dependecy Relationship is as following:

1) a_impl ---> a_api

2) b_impl ---> b_api and a_api
            
3) c_impl ---> c_api and b_api

## Building

You'll need a machine with JDK 6 1.22 above + and Apache Maven 3 installed.

Checkout:

    https://github.com/tangyong/glassfish-provisioning-samples.git

Building:
    
    mvn -DskipTests=true clean install
    
## Running

While deploying the samples into an OSGi container(glassfish), in server.log, will print the following:

"Hello A And Hello B And Hello C!"