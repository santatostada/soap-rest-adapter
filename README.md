# soap-rest-adapter
sipmle rest adapter for soap-service
implemetation of rest to soap adapter
implements methods addUser (gets string id, string name parameters), deleteUser (gets string id parameter), getUser (gets string id parameter)

to run service:
java -jar soap-service-1.0-SNAPSHOT.jar

to test use:
curl --header "content-type: text/xml" -d @request.xml http://localhost:1050/ws
