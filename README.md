# URLShortener
This is a project which deals with shortening of Long Url's into short Url's based on hashing.

it is implemented in 2 ways
1 - using InMemory Data Structures (HashMap)
2 - Using Redis

==========================================================================
In Memory Data Structures Implementation

It has a Controller (V1) , a service and A Repository as a project structure.

============================================================================
Redis - Implementation (V2)

the Project structure is similar to In Memory Implementation except it has Redis Template Configured.

this implementation Offers 3 endpoins

1 - Url Shortener Endpoint 

This endpoint Deals with shortening the Leangthy Url to short Url by assigning a temporary Domain name along with the hash value.

if a long url is passed to this endpoint, it is going to check with the existing redis if there is already a shortened Url generated for this Url , if then it is going to return the already generated short Url to the end user.
if not then it is going to calculate the hash value and append to the domain url (static) , stores it in db and returns the shortened Url.
this end point also updates the metric in db. ex:- it checks the domain of the long url and updates do accordingly;

Example : ![image](https://github.com/MATHESHKOLIMI/URLShortener/assets/37385872/c079c78b-70a9-47d9-9b83-b92fd526ff48)


2 - Url Redirect Endpoint

This Endpoint deals with Redirecting the short Url to its original Url.
if a short url is passed to this endpoint it checks the db if it persists or not , if yes then it return the assign value to the End user.
If no value is assigned to the user then it shows "NOT MAPPED TO ANY URL".

Example: ![image](https://github.com/MATHESHKOLIMI/URLShortener/assets/37385872/2205f9c5-9ed6-433d-91c9-030e1a1b0a24)


3 - Metrics Endpoint

This Endpoint Deals with how many Urls are converted from Long Urls to Short Url's

Example: ![image](https://github.com/MATHESHKOLIMI/URLShortener/assets/37385872/608b6e82-072b-419d-9212-bc3ca08fa1fd)

=========================================================================
