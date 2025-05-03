# SpringBoot-Postgres-Minio

<img src="screenshots/springboot_stroge" alt="Main Information" width="800" height="300">

### 📖 Information

<ul style="list-style-type:disc">
  <li>MinIO is the only object storage suite available on
      every public cloud with supporting high-performance.</li>
  <li>It is API compatible with Amazon S3 cloud storage service</li>    
  <li>It can handle unstructured data such as photos, videos, log files, backups, and container images with (currently) the maximum supported object size of 5TB.</li>
  <li>Here is the explanation of the project
      <ul>
        <li>A deployment REST endpoint</li>
        <li>A download REST endpoint</li>
        <li>object-storage | file-system 2 Storage Strategy libraries</li>
        <li>Implement the process of uploading file to Minio</li>
        <li>Implement the process of downloading file from Minio</li>
        <li>During uploading .jon file to Minio also posgres database are saved  </li>   
      </ul>
  </li>
  

### 🔨 Run the App

<b>1 )</b> Install <b>Docker Desktop</b>. Here is the installation <b>link</b> : https://docs.docker.com/docker-for-windows/install/

<b>2 )</b> Open <b>Terminal</b> under <b>\ApiStorage\ApiStorage></b> folder to run on <b>Docker.yml </b> Container
```
    docker-compose up -d
```
<b>3 )</b> Open <b>Minio</b> in the Browser 
```
    127.0.0.1:9001
```
<b>4 )</b> Enter username and password minio
```
    username : minioadmin
    password : minioadmin
```
<b>5 )</b> Open <b>Posgres database</b> in the Browser 
```
    127.0.0.1:5432
    
```
<b>6 )</b> Enter username and password database
```
     POSTGRES_DB: docker
    POSTGRES_USER: postgres
    POSTGRES_PASSWORD: mysecretpassword
```
<b>7 )</b> Explore Rest APIs
<table style="width:100%">
  <tr>
    <th>Method</th>
    <th>Url</th>
    <th>Description</th>
    <th>Valid Request Body</th>
  </tr>
  <tr>
    <td>POST</td>
    <td>/upload</td>
    <td>Upload file to Minio</td>
    <td><a href="README.md#upload">Info</a></td>
  </tr>  
<tr>
       <td>GET</td>
       <td>/{bucketName}/version/{objectName}</td>
       <td>Download object in BucketName from Minio dipnot: minio.bucket-name=bunucci in applications.properties file</td>
       <td></td>    
  </tr>  
</table>

### Used Dependencies
* Spring Boot Web
* Minio
* Lombok
* postgresql
* spring-boot-devtools
* mapstruct

## Valid Request Body

##### <a id="upload">Upload -> http://localhost:8080/mypackage/1.0.0</a>
```
    file : Uploaded File
    bucketname : bunucci -> mypackage -> 1.0.0
    dipnot: in application.properties file minio.bucket-name=bunucci
```

## Valid Request Params

##### <a id="download">Download File -> http://localhost:8080/{bucketName}/{version}/{objectName}</a>
```
   http://localhost:8080/mypackage/1.0.0/meta.json
```
### Screenshots

<details>
<summary>Click here to show the screenshots of project</summary>
    <p> Figure 1 </p>
    <img src ="screenshots/screenshot_1.PNG">
    <p> Figure 2 </p>
    <img src ="screenshots/screenshot_2.PNG">
    <p> Figure 3 </p>
    <img src ="screenshots/screenshot_3.PNG">
    <p> Figure 4 </p>
    <img src ="screenshots/screenshot_4.PNG">
    <p> Figure 5 </p>
    <img src ="screenshots/screenshot_5.PNG">
    <p> Figure 6 </p>
    <img src ="screenshots/screenshot_6.PNG">
</details>
