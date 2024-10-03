package com.example.roomdatabaselibraryapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MovieDAO {
    // Add a new movie
    @Insert
    void insert(Movie movie);

    // View all movies
    @Query("SELECT * FROM movies")
    List<Movie> getAllMovies();

    // View a specific movie by ID
    @Query("SELECT * FROM movies WHERE id = :id")
    Movie getMovieById(int id);

    // Update a movie
    @Update
    void update(Movie movie);

    // Delete a movie
    @Delete
    void delete(Movie movie);
}
