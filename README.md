# Getting Started

Project:
The application is to provide market rates history of digital coins in multiple currencies.

Project Description:
Application will refresh in every 30-second and log rates of the digital coins and provide history against multiple currencies.


Technologies used in project

 - Database H2 
 - Spring boot 3.0.0-SNAPSHOT
 - JAVA 17 
 - Docker

Application Rest Endpoints:
- View History : /digital-currency
- View Market-wise: /local-currency?currency=PKR
- Delete History: /local-currency?currency=PKR

How to Install and Run the Project:
Mentioned points need to be required before running the service.
- Users can define markets in the YML file for fetch history. 

API Consumed:
- For coins details: https://api.coincap.io/v2/assets/ 
- For currency details: https://api.exchangerate.host

