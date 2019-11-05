# basketFinder

### System description
System displays basketball public and private courts with active events on the map.
Authenticated users may create or join event on selected court (Authentication uses Google oAuth2). 
If event is created in private court event admin must confirm users attendance

### Dev environment
System runs on docker with 3 images
* basket_finder_java - Java instance
* basket_finder_postgres - postgres DB instance
* basket_finder_client - React front end client instance

Also there is JavaImporter main class which imports courts data form csv files. 
When running containers for the first time make sure to run importer class to have up to date court data.
