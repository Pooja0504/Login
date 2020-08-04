package pooja.jadhav.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<CustomClass> {
    public CustomAdapter(@NonNull Context context, ArrayList<CustomClass> resource) {
        super(context, 0, resource);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview, parent, false);

        CustomClass item = getItem(position);

        TextView name = convertView.findViewById(R.id.txtname);
        name.setText("Name : " + item.getName());

        TextView rollno = convertView.findViewById(R.id.txtRollno);
        rollno.setText("Roll no : " + item.getRollno());

        TextView email = convertView.findViewById(R.id.txtEmail);
        email.setText("Email : " + item.getEmail());

        TextView phone = convertView.findViewById(R.id.txtPhone);
        phone.setText("Phone : " + item.getPhone());

        TextView location = convertView.findViewById(R.id.txtLoc);
        location.setText("Location : " + item.getLocation());


        ImageView imageView = convertView.findViewById(R.id.imageView);
        imageView.setImageResource(item.getImg_src());


        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.my_anim);
        convertView.startAnimation(animation);

        return convertView;
    }

}
