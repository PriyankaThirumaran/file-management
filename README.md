# file-management
This is a Java Springboot-maven application that can upload files(creates the file in the disk at the specified directory path) and downloads them thro' REST APIs. 

Contains REST APIs to upload single file, upload multiple files, download files, get the information of all files managed using MySQL DB. Contains File class. Utilizes Hibernate ORM to map POJO classes with the DB schema.

When a file is uploaded, it is created in the local disk and the file details such as name, creation date, storage path are all captured and stored in MySQL DB.
So when the same file wants to be fetched, it checks the DB for storage path and then fetches the file from the path.

The REST APIs run on port 8083.
