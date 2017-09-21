package bo.arte.graffiti.openlibra.apis;

import bo.arte.graffiti.openlibra.models.BooksResponse;
import bo.arte.graffiti.openlibra.models.Categoriesresponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by armin on 20-09-17.
 */

public interface OpenLibraService {

    @GET("api/v1/get/")
    Call<Categoriesresponse> categories(@Query("get_categories") String resourceId);

    @GET("api/v1/get/")
    Call<BooksResponse> books(@Query("criteria") String resourceId, @Query("results_range") int limit);

    @GET("api/v1/get/")
    Call<BooksResponse> search(@Query("keyword") String resourceId, @Query("results_range") int limit);
}
