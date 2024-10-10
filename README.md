# MDEV - F24 - Data Management 

Video Link : https://youtu.be/3JZH7NpYvGQ

1. Add Search Functionality (5 Points)

Implement a search feature to allow users to search movies (or any entity) by their title.
The search should display matching results in the app's TextView or a RecyclerView.
Modify the DAO interface to include a method to query the database based on a partial title match (@Query with LIKE).

2. Favorite Movies Feature (5 Points)

Add a "favorite" column to the Movie entity, represented by a boolean.
Add an option for users to mark or unmark movies as favorites.
Create a UI indicator (e.g., a star icon) to show which movies are marked as favorites.
Update the DAO to include methods to mark/unmark favorites and retrieve all favorite movies.

3. Room Database Migration (3 Points)
   
Implement a database migration strategy for handling changes in the database schema.
Modify the Movie entity (e.g., add a director column) and ensure existing data is not lost during migration.
Use the Room.databaseBuilder() to handle migration.

4. Add Toast Notifications (2 Points)

Add Toast messages that appear when a user adds, updates, or deletes a movie.
Submit your program and a short video showing all the required features in the working state.
