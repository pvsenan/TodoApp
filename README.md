# Todo Application
#### This is a simple todo application developed using springboot and java. Persistence is implemented using MySQL database.

#### This application has the following features
* Creating Todo task
* Editing Todo task
* Deleting Todo task

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

### Deploying to AWS
Deployment can be done to Elastic Beanstalk using the provided Jenkins pipeline. Jenkins pipeline config assumes that you 
have set up the aws credentials in jenkins secret with the following ID
````yaml
    AWS_ACCESS
    AWS_SECRET
````
*IMPORTANT*: Before configuring and running the pipeline it requires that the Elastic Beanstalk environment and MySQL database 
is provisioned in AWS. You can find the provisioning script and how to run the script can be found here
<https://github.com/pvsenan/TodoApp-Provision>

Once the provisioning is done, the script will automatically create db password for admin user and can be found under AWS Secrets manager. 
Add the database url and credentials in production properties file. see the example below
````yaml
spring.datasource.url=<your datasource url>
spring.datasource.username=admin
spring.datasource.password=<password>
````
### Verifying after deployment
Navigate to Elastic Beanstalk application from AWS console and copy the application link. You can access the swagger ui using the link
to do CRUD operation against the application. The following is an example link to the swagger ui after deploying to AWS. Please not that this is
an example link and replace `your_eb_url` with your application url.
````yaml
http://your_eb_url/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
````
