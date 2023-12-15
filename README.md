# rest-client-reactive-quickstart

To reproduce, start the demo: 
```shell
mvn compile quarkus:dev
```

Test the different scenarios:

### Server: test multiple files upload with same part name (supported):

```shell
curl -X 'POST' \
  'http://localhost:8080/hello' \
  -H 'accept: */*' \
  -H 'Content-Type: multipart/form-data' \
  -F 'resources=@src/main/resources/img1.jpg;type=image/jpeg' \
  -F 'resources=@src/main/resources/img2.jpg;type=image/jpeg'
```

Output: 
```shell
img1.jpg: 38149; img2.jpg: 58320; 
```

### Client: test multiple files upload with same part name (NOT SUPPORTED):

```shell
curl -X 'GET' \
  'http://localhost:8080/hello' \       
  -H 'accept: */*' \
```
Output (empty) because files are not written to the request: 
```shell

```

### Client: test single file upload (supported):

```shell
curl -X 'GET' \
  'http://localhost:8080/hello/single' \
  -H 'accept: */*' \
```
Output:
```shell
img1.jpg: 38149;
```

