# Project Design Doc - Music Bulletin Board Design

## 1. Problem Statement

The Music Bulletin Board is a service allowing musicians to connect with their peers, within similar areas, to find work and post their needs. Users want to have the ability to detail our their 'want' ads with role needs, music genre expected, locations involved, as well as look at and respond to other user's posted ads. 

## 2. Top Questions to Resolve in Review
1.) How much depth should go into a user's posted ads? Name/description/role/date/salary?
    - should these be required?

2.) Will the application allow user's that are responding to posted ads to 'deep-dive' into the user posting the ad? - will that be more of a stretch goal and should this remain, at base level, an app for a user to simply post their ad.

3.) Should a filter/search system be put into place from the perspective of a user looking at ads?

4.) Should there be a separate login validation to determine if someone is looking or posting?
    - should there be a difference? Should there just be a separate page to allow a user to search through existing ads.

5.) Should an ad include tags?

6.) There is still a case to be debated on whether Locations and Venues should be a table rather than an attribute included with the other tables.


## 3. Use Cases

U1. As a user, I want to create a new ad.

U2. As a user, I want to view all my posted ad(s)

U3. As a user, I want to update my ad(s) details. ***Can this be broken up into multiple cases - 

U4. As a user, I want to be able to delete my ad(s).

U5. As a user, I want to be able to update my bio details.

U6. As a user, I want to be able to update my Name detail.

U7. As a user, I want to be able to update my groups details.

U8. As a user, I want to be able to update my roles details.

U9. As a user, I want to bb able to view my individual ad(s).

U10. As a user, I want to be able to update my ad's name.

U11. As a user, I want to be able to update my ad's description.

U12. As a user, I want to be able to update my ad's location.

U13. As a user, I want to be able to update my ad's tags.

U14. As a user, I want to be able to update my ad's salary.

U15. As a user, I want to be able to update my ad's name.

U16. As a user, I want to be able to view my user details.

### 3.2  Stretch Use Cases

U1. As a user, I want to be able to view other user's ads.

U2. As a user, I want to be able to apply to another user's ad.

U3. As a user, I want to be able to filter through other user's ads.

U4. As a user, I want to be able to change bulletin boards.

U5. As a user, I want to view venues associated with a board.

## 4. Project Scope
### 4.1 In Scope

* Creating, retrieving, and updating an ad
* Retrieving all ads a user has created
* Updating a user's details. 

### 4.2 Out of Scope

* Multiple Boards (broader than Nashville area) - mid stretch
* Venues as an entity rather than a String - closest stretch?
* Allow users to apply to ads and view applications to their ads - farthest stretch 

## 5. Proposed Architecture Overview

The MLP will include creating, retrieving, and updating an ad. Included will also be the functionality to update a user's personal details.

API Gateway and Lambda will be used to create these endpoints to satisfy our requirements:
* `GetAd`
* `CreateAd`
* `UpdateAd`
* `DeleteAd`
* `GetUser`
* `CreateUser`
* `UpdateUser`

Boards, users, ads, locationsm and venues will be stored within DynamoDB in their own respective tables. 


## 6. API
### 6.1 Public Models
````
// UserModel

String userId;
String name;
String bio;
List<String> groups;
List<String> ads; //adId
````

````
// AdModel

String adId;
String name;
String description;
Double salary; //double?
String location; //venue name
String userId;
Set<String> tags;
````

````
// LocationModel

String locationId;
String name;
List<String> venues; //venueId
````

````
// VenueModel

String venueId;
String name; 
String description;
String location;
````

````
// BoardModel

String boardId;
String name;
Set<String> users; //userIds
Set<String> ads; //adIds
String location;
````

### 6.2 Get Ad Endpoint
* Accepts `GET` requests to `/ads/:adId`

### 6.3 Create Ad Endpoint
* Accepts `POST` requests to `/ads`

### 6.4 Update Ad Endpoint
* Accepts `PUT` requests to `/ads/:adId`

### 6.5 Delete Ad Endpoint
* Accepts `DELETE` requests to `/ads/:adID`

### 6.6 Get User Endpoint
* Accepts `GET` requests to `/users/:userId`

### 6.7 Create User Endpoint 
* Accepts `POST` requests to `/users`

### 6.8 Update User Endpoint
* Accepts `PUT` requests to `/users/:userId`

## 7. Tables
### 7.1 `boards`
````
boardId // partition key, string
name // string
usersList // stringSet (userId)
adsList // stringSet (adId)
location // stringSet (locationId)
````

### 7.2 `ads`
````
Id // partition key, string
name // string
userId // string
tags // stringSet
````

### 7.3 `users`
````
userId // partition key, string
name // string
bio // string
group // stringList
adsList // stringSet (adId)
roles // stringList
````

### 7.4 `locations`
````
locationId // partition key, string
venuesList // stringSet (venueId)
````

### 7.5 `venues`
````
venueId // partition key, string
name // string
description // string
location // string (locationId)
````

## 8. Pages

Currently, having issues with image links. There are reference images for the frontend pages intended for the project in resources/images/.



