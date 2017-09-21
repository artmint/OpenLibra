package bo.arte.graffiti.openlibra;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import bo.arte.graffiti.openlibra.adapters.BooksAdapter;
import bo.arte.graffiti.openlibra.apis.OpenLibraService;
import bo.arte.graffiti.openlibra.apis.ServiceGenerator;
import bo.arte.graffiti.openlibra.models.Book;
import bo.arte.graffiti.openlibra.models.BooksResponse;
import bo.arte.graffiti.openlibra.models.Categories;
import bo.arte.graffiti.openlibra.models.Tag;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        BooksAdapter.OnBookSelectedListener {


    private RecyclerView booksRecyclerView;
    private BooksAdapter booksAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        booksRecyclerView = (RecyclerView) findViewById(R.id.BooksRecyclerView);
        booksRecyclerView.setHasFixedSize(true);
        booksRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        booksAdapter = new BooksAdapter(this, this);

        booksRecyclerView.setAdapter(booksAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cargarDatos();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBookSelected(Book book) {

        String detail = book.getContent().replace(",", ".");
        List<Categories> category = book.getCategories();
        List<Tag> tags = book.getTags();

//        String uriString = "geo:" + latitud + "," + longitud + "?z=16&q=" + latitud + "," + longitud + "(" + unidadEducativa.getNombre() + ")";

//        Uri location = Uri.parse(uriString);
//        Intent intent = new Intent(Intent.ACTION_VIEW, location);
//        startActivity(intent);
    }

    private void cargarDatos() {
        OpenLibraService service = ServiceGenerator.createService(OpenLibraService.class);
        Call<BooksResponse> call = service.books("most_viewed", 10);

        call.enqueue(new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                swipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful()) {
                    booksAdapter.setDataset(response.body().getResult().getRecords());
                } else {
                    Log.e("Open Libra", "No se puede obtener los establecimientos");
                }
            }

            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Log.e("Open Libra", "Error obteniendo establecimientos: " + t.getMessage());
            }
        });
    }
}
