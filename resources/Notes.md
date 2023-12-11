### Friday 11-10
* 'Finished' up the create user endpoint and successfully deployed with the current codebase on branch `feature/user-endpoints`
  * Resulted in all 4 tables being created
  * Ran a test local frontend to ensure it still works. - Everything looks good aside from it being for the musicplaylistservice still.
  * Started a super simple frontend with `index.html` 
    * names are changed but functionality is not!

### Saturday 11-11
* Made placeholder html pages that successfully navigate with buttons. No js files were made for functionality. 
  * Just placeholders to make the future work flow more efficiently. 

### Monday 11-13
* Fleshed out all classes for the ad endpoints: create, update, get, and delete.
  * Still stuck on the choice for createUser - ask instructor
  * Do I need to make all these classes for locations and venues???
    * Gut says yes due to tables entities.


### Tuesday 11-14
* Took a look at the cognito username as a use for a user in a table
  * Need to go back over
* Stubbed out test classes


### Wednesday 11-15
* N/A

### Thursday 11-16

### Friday 11-17

### Tuesday 11-28
* Getting front end together
  * All pages have a working header
    * Currently trying to get the Login through cognito to lead directly to the homePage.html - currently looking at the env files


### Wednesday 11-29
* Current issue with retrieving user/creating user between entry page to the home page. Moving on to get the front end wired up for the rest of the endpoints.
  * Not an ideal solution, however better to get work done than stare at the screen for an hour.
* Currently displaying the current user details as headers 
  * This is purely for testing later to make sure all things are working, this won't work long term for bio.
    * Will need to be cleaned up.
  * This is 'done'
* CreateAd is mostly wired up - currently receiving a network error on create submit.
  * There's a good chance the html is setup odd to handle it with the hidden error and error-message placement?
      * Going to continue on wiring the other pages so that when I start solving the similar issues on one page, the rest will also be manageable.
* Update ad is wired up
* Noticed something odd may be happening with the redirect methods, but are they being used? Double check next session.

### Thurs 11-30
* Got the front end wired up with creating a user - fixed the issue. I had the template looking to post through an id rather than just the table.gg