package sara.com.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import sara.com.Models.Photo;
import sara.com.Flickrtask.R;

public class PhotoAdapter extends BaseAdapter {


    private ArrayList<Photo> photos;
    private Context context;


    public PhotoAdapter(Context context, ArrayList<Photo> photos) {


        this.photos = photos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return photos.size();

    }

    @Override
    public Object getItem(int position) {
        return photos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.adapter_photos, parent, false);
        }
        ImageView img = (ImageView) convertView.findViewById(R.id.img_flickr);

        Photo photo = photos.get(position);

        Picasso.with(context).load(photo.getPhotoUrl()).placeholder(R.drawable.loading)
                .error(R.drawable.no_img).into(img);
        return convertView;
    }

    public void addAll(ArrayList<Photo> lst) {

        photos.addAll(lst);
        notifyDataSetChanged();
    }

    public void clear() {

        photos.clear();
        notifyDataSetChanged();
    }
}
