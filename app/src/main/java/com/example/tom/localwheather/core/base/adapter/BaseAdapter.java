package com.example.tom.localwheather.core.base.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseAdapter<E, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {

    private List<E> items;
    private OnItemClickListener listener;

    public BaseAdapter(@NonNull List<E> items){
        this.items = items;
    }

    public BaseAdapter(){
        items = new ArrayList<>();
    }

    protected static View inflate(@NonNull ViewGroup parent, @LayoutRes int layoutRes) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
    }

    public int getItemCount(){
        return items.size();
    }

    public void add (@NonNull E item){
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void add(@NonNull Collection<E> items) {
        this.items.addAll(items);
        notifyItemInserted(items.size() - 1);
    }

    public void replace(@NonNull Collection<E> items){
        this.items.clear();
        notifyDataSetChanged();
        add(items);
    }
    public void clear() {
        items.clear();
        notifyItemRangeRemoved(0, items.size());
    }

    public void remove(E item) {
        final int position = items.indexOf(item);
        if (position > -1) {
            items.remove(item);
            notifyItemRemoved(position);
        }
    }

    public void remove(int position){
        items.remove(position);
        notifyItemRemoved(position);
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    protected List<E> getItems(){
        return items;
    }

    protected OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(@Nullable OnItemClickListener listener){
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(@NonNull View view, int position);
    }
}
