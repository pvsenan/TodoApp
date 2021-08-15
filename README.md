# Todo Application
#### This is a simple todo application developed using springboot and java. Persistence is implemented using MySQL database.

#### This application has the following features
* Creating Todo list
* Editing Todo list
* Deleting Todo list

# Building the application locally
```bash
cd to application directory
./gradlw build
```
# Running the application locally
```bash
./gradlew bootRun --args='--spring.profiles.active=local'
```
## OpenAPI(Swagger) documentation
OpenAPI documentation when running locally:
<http://localhost:5000/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config>
