package lat.ta.ujianpemrograman.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * merupakan class adapter dari recycler view.
 * @param <T> object data yang akan ditampilkan pada recyclerview.
 *
 * Penggunaan :
 *           Implementasikan activity atau fragment dengan Adapter.OnBinding.
 *           Implementasikan semua method abstract.
 *           Gunakan instance dengan format :
 *              - parameter pertama gunakan keyword this jika telah di lakukan step sebelumnya.
 *              - parameter kedua merupakan resId layout, contoh : R.layout.item_
 *              - parameter ketiga merupakan instance dari list atau juga dapat null.
 *
 * Contoh penggunaan di PilihPelajaranActivity.
 */

public class Adapter<T> extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private OnBinding onBinding;
    private List<T> list;
    private int layout;

    public Adapter(OnBinding binding, int layout, List<T> list) {
        this.list = (list == null) ? new ArrayList<>() : list;
        this.layout = layout;
        this.onBinding = binding;
    }

    public void setNewData(T data) {
        list.add(data);
        notifyDataSetChanged();
    }

    public void setNewData(List<T> data) {
        list.addAll(data);
        notifyDataSetChanged();
    }

    public void refreshData(List<T> data) {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        onBinding.onBindViewHolder(holder.itemView, list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface OnBinding<T> {
        void onBindViewHolder(View itemView, T obj);
    }
}
