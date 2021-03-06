# DOAP parser

Parsing DOAP (RDF/XML) file and converting it into JSON document.

## Example

We can take Apache Camel DOAP file: <http://svn.apache.org/repos/asf/camel/trunk/doap.rdf>
and have it transformed to the following JSON document:

````json
{
  "homepage" : "http://camel.apache.org/",
  "license" : "http://usefulinc.com/doap/licenses/asl20",
  "maintainer" : [ {
    "name" : "Christian Mueller",
    "email" : "cmueller@apache.org"
  } ],
  "sys_project_name" : "Apache Camel",
  "sys_created" : "2008-12-17",
  "sys_description" : "Apache Camel is a powerful open source integration framework based on known Enterprise Integration Patterns.",
  "sys_content" : "Apache Camel is a powerful open source integration framework based on known Enterprise Integration Patterns.\nRules for Camel's routing and mediation engine can be defined in either a Java based DSL, XML or using DSLs for dynamic languages such as Groovy or Scala.",
  "mailing-list" : "http://camel.apache.org/mailing-lists.html",
  "download-page" : "http://camel.apache.org/download.html",
  "bug-database" : "http://issues.apache.org/activemq/browse/CAMEL",
  "programming-language" : [ "SQL", "PHP", "Scala", "Ruby", "Groovy", "XML", "Python", "JavaScript", "Java" ]
}
````

## More Info

This code is still WIP.

The goal is to have DOAP (Description of a Project) file parser that can output JSON documents that we can easily index into Searchisko.
In addition to DOAP format it also support Red Hat extension of DOAP. Example of DOAP extension RDF can be generated
using online form <http://jboss-developer.github.io/doap/form.html>.

## License

    Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.