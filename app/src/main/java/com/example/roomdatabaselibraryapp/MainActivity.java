package com.example.roomdatabaselibraryapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private EditText editTitle, editGenre, editYear, editId, editDirector;
    private TextView textViewMovies;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        editTitle = findViewById(R.id.editTitle);
        editGenre = findViewById(R.id.editGenre);
        editYear = findViewById(R.id.editYear);
        editId = findViewById(R.id.editId);
        textViewMovies = findViewById(R.id.textViewMovies);
        editDirector = findViewById(R.id.editDirector);
        CheckBox checkBoxFavorite = findViewById(R.id.checkBoxFavorite);

        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonView = findViewById(R.id.buttonView);
        Button buttonUpdate = findViewById(R.id.buttonUpdate);
        Button buttonDelete = findViewById(R.id.buttonDelete);
        Button buttonSearch = findViewById(R.id.buttonSearch);
        ImageButton buttonFavorite = findViewById(R.id.buttonFavorite);


        // Initialize database
        db = AppDatabase.getInstance(this);

        // Add a new movie
        buttonAdd.setOnClickListener(v -> {
            String title = editTitle.getText().toString();
            String genre = editGenre.getText().toString();
            String yearText = editYear.getText().toString();
            String director = editDirector.getText().toString();

            if (!title.isEmpty() && !genre.isEmpty() && !yearText.isEmpty()) {
                int year = Integer.parseInt(yearText);
                Movie movie = new Movie(title, genre, year, checkBoxFavorite.isChecked(), director);

                // Insert movie in background thread
                Executors.newSingleThreadExecutor().execute(() -> {
                    db.movieDao().insert(movie);
                    runOnUiThread(this::displayMovies);
                });
            }
            Toast.makeText(this, "Movie added successfully", Toast.LENGTH_SHORT).show();
        });

        // View all movies
        buttonView.setOnClickListener(v -> displayMovies());

        // Update a movie
        buttonUpdate.setOnClickListener(v -> {
            String idText = editId.getText().toString();
            String title = editTitle.getText().toString();
            String genre = editGenre.getText().toString();
            String yearText = editYear.getText().toString();
            String director = editDirector.getText().toString();
            boolean favorite = checkBoxFavorite.isChecked();

            if (!idText.isEmpty() && !title.isEmpty() && !genre.isEmpty() && !yearText.isEmpty()) {
                int id = Integer.parseInt(idText);
                int year = Integer.parseInt(yearText);

                Executors.newSingleThreadExecutor().execute(() -> {
                    Movie movie = db.movieDao().getMovieById(id);
                    if (movie != null) {
                        movie.setTitle(title);
                        movie.setGenre(genre);
                        movie.setReleaseYear(year);
                        movie.setFavorite(favorite);
                        movie.setDirector(director);
                        db.movieDao().update(movie);
                        runOnUiThread(() -> displayMovies());
                    }
                });
            }
            Toast.makeText(this, "Movie updated successfully", Toast.LENGTH_SHORT).show();
        });

        // Delete a movie
        buttonDelete.setOnClickListener(v -> {
            String idText = editId.getText().toString();
            if (!idText.isEmpty()) {
                int id = Integer.parseInt(idText);
                Executors.newSingleThreadExecutor().execute(() -> {
                    Movie movie = db.movieDao().getMovieById(id);
                    if (movie != null) {
                        db.movieDao().delete(movie);
                        runOnUiThread(() -> displayMovies());
                    }
                });
            }
            Toast.makeText(this, "Movie deleted successfully", Toast.LENGTH_SHORT).show();
        });

        // Search movies by title
        buttonSearch.setOnClickListener(v -> {
            String title = editTitle.getText().toString();
            if (!title.isEmpty()) {
                Executors.newSingleThreadExecutor().execute(() -> {
//                    List<Movie> movies = db.movieDao().searchMoviesByTitle(title);
                    runOnUiThread(() -> displayMovies(title));
                });
            }
        });

        buttonFavorite.setOnClickListener(v -> {
            Executors.newSingleThreadExecutor().execute(() -> {
                List<Movie> movies = db.movieDao().getFavoriteMovies();
                runOnUiThread(() -> {
                    StringBuilder sb = new StringBuilder();
                    for (Movie movie : movies) {
                        sb.append("ID: ").append(movie.getId()).append("\n")
                                .append("Title: ").append(movie.getTitle()).append("\n")
                                .append("Director: ").append(movie.getDirector()).append(("\n"))
                                .append("Genre: ").append(movie.getGenre()).append("\n")
                                .append("Year: ").append(movie.getReleaseYear()).append("\n")
                                .append("Favorite: ").append(movie.getFavorite() ? "Yes" : "No").append("\n\n");
                    }
                    textViewMovies.setText(sb.toString());
                });
            });
        });
    }

    // Display all movies in the TextView
    private void displayMovies() {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<Movie> movies = db.movieDao().getAllMovies();
            runOnUiThread(() -> {
                StringBuilder sb = new StringBuilder();
                for (Movie movie : movies) {
                    sb.append("ID: ").append(movie.getId()).append("\n")
                            .append("Title: ").append(movie.getTitle()).append("\n")
                            .append("Director: ").append(movie.getDirector()).append(("\n"))
                            .append("Genre: ").append(movie.getGenre()).append("\n")
                            .append("Year: ").append(movie.getReleaseYear()).append("\n")
                            .append("Favorite: ").append(movie.getFavorite() ? "Yes" : "No").append("\n\n");
                }
                textViewMovies.setText(sb.toString());
            });
        });
    }

    private void displayMovies(String title) {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<Movie> movies = db.movieDao().searchMoviesByTitle(title);
            runOnUiThread(() -> {
                StringBuilder sb = new StringBuilder();
                for (Movie movie : movies) {
                    sb.append("ID: ").append(movie.getId()).append("\n")
                            .append("Title: ").append(movie.getTitle()).append("\n")
                            .append("Director: ").append(movie.getDirector()).append(("\n"))
                            .append("Genre: ").append(movie.getGenre()).append("\n")
                            .append("Year: ").append(movie.getReleaseYear()).append("\n\n")
                            .append("Favorite: ").append(movie.getFavorite() ? "Yes" : "No").append("\n\n");
                }
                textViewMovies.setText(sb.toString());
            });
        });
    };
}
