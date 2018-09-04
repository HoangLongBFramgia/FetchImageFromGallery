package hoanglong.framgia.com.fetchimagefromgallery;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageGalleryAdapter imageGalleryAdapter;
    RecyclerView recyclerView;
    ArrayList<String> listOfAllImages = new ArrayList<>();


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();
        setUpData();
        setUpRecyclerView();

    }

    private void setUpView() {
        recyclerView = findViewById(R.id.rv_gallery);
    }

    private void setUpData() {
        new LoadImageAsyncTask().execute();
        imageGalleryAdapter = new ImageGalleryAdapter(listOfAllImages);
    }

    private void setUpRecyclerView() {
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(imageGalleryAdapter);
    }

    class LoadImageAsyncTask extends AsyncTask<Intent, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(Intent... intents) {
            Uri uri;
            Cursor cursor;
            int column_index_data, column_index_folder_name;
            String absolutePathOfImage = null;
            uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

            String[] projection = {MediaStore.MediaColumns.DATA,
                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

            cursor = getContentResolver().query(uri, projection, null,
                    null, null);

            column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            column_index_folder_name = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            while (cursor.moveToNext()) {
                absolutePathOfImage = cursor.getString(column_index_data);
                listOfAllImages.add(absolutePathOfImage);
            }
            return listOfAllImages;
        }

        @Override
        protected void onPostExecute(ArrayList<String> s) {
            super.onPostExecute(s);
            listOfAllImages.addAll(s);
        }
    }
}



