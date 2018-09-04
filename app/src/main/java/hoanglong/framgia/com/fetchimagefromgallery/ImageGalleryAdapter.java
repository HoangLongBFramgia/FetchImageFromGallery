package hoanglong.framgia.com.fetchimagefromgallery;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.ImageViewHolder> {

    private ArrayList<String> arrayList;

    ImageGalleryAdapter(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ImageGalleryAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageGalleryAdapter.ImageViewHolder holder, int position) {
        String imageUri = arrayList.get(position);
        holder.ivGallery.setImageURI(Uri.parse(imageUri));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView ivGallery;

        ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            ivGallery = itemView.findViewById(R.id.iv_gallery);
        }
    }
}
