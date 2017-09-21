package bo.arte.graffiti.openlibra.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import bo.arte.graffiti.openlibra.R;
import bo.arte.graffiti.openlibra.models.Book;

/**
 * Created by armin on 20-09-17.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private Context context;
    private List<Book> dataset;
    private OnBookSelectedListener onBookSelectedListener;

    public interface OnBookSelectedListener {
        void onBookSelected(Book book);
    }

    public BooksAdapter(Context context, OnBookSelectedListener onBookSelectedListener) {
        this.context = context;
        this.dataset = new ArrayList<>();
        this.onBookSelectedListener = onBookSelectedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book b = dataset.get(position);

        holder.title.setText(b.getTitle());
        holder.description.setText(b.getContent_short());

        Glide.with(context)
                .load(b.getThumbail())
                .into(holder.imageBook);

        holder.setDeviceSelectedListener(b, onBookSelectedListener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    public void add(Book book) {
        dataset.add(book);
        notifyDataSetChanged();
    }

    public void setDataset(List<Book> books) {
        if (books == null) {
            dataset = new ArrayList<>();
        } else {
            dataset = books;
        }
        notifyDataSetChanged();
    }

    public void clear() {
        dataset.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View cardView;

        TextView title;
        TextView description;
        ImageView imageBook;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card_view);

            title = (TextView) itemView.findViewById(R.id.card_title);
            description = (TextView) itemView.findViewById(R.id.card_text);
            imageBook = (ImageView) itemView.findViewById(R.id.card_image);
        }

        public void setDeviceSelectedListener(final Book book, final OnBookSelectedListener onBookSelectedListener) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBookSelectedListener.onBookSelected(book);
                }
            });
        }
    }

}
