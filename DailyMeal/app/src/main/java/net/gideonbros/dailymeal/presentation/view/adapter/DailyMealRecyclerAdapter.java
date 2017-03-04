package net.gideonbros.dailymeal.presentation.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.gideonbros.dailymeal.R;
import net.gideonbros.dailymeal.data.models.DailyMealModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by Matija on 4.3.2017..
 */

public class DailyMealRecyclerAdapter extends RecyclerView.Adapter
        implements RealmChangeListener<RealmResults<DailyMealModel>> {

    @NonNull
    private Context context;
    @NonNull
    private OnOrderClickListener listener;
    private RealmResults<DailyMealModel> dailyMeals;

    public interface OnOrderClickListener {
        void onClick(DailyMealModel dailyMealModel);
    }

    public DailyMealRecyclerAdapter() {
    }

    public void setDailyMeals(Context context, OnOrderClickListener listener, RealmResults<DailyMealModel> dailyMeals) {
        this.context = context;
        this.listener = listener;
        this.dailyMeals = dailyMeals;
        this.dailyMeals.addChangeListener(this);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_daily_meal, parent, false);

        return new DailyMealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final DailyMealModel dailyMealModel = dailyMeals.get(position);
        ((DailyMealViewHolder) holder).setData(dailyMealModel);
    }

    @Override
    public int getItemCount() {
        return dailyMeals != null ? dailyMeals.size() : 0;
    }

    @Override
    public void onChange(RealmResults<DailyMealModel> element) {
        notifyDataSetChanged();
    }

    public class DailyMealViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_image)
        ImageView dailyMealImage;
        @BindView(R.id.card_title)
        TextView dailyMealTitle;
        @BindView(R.id.card_text)
        TextView dailyMealDescription;

        @BindView(R.id.action_button)
        Button dailyMealOrder;
        @BindView(R.id.directions_button)
        ImageButton dailyMealDirection;
        @BindView(R.id.favorite_button)
        ImageButton dailyMealFavorite;

        public DailyMealViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }

        void setData(@NonNull final DailyMealModel dailyMealModel) {
            if (!dailyMealModel.getDailyMealImageUrl().isEmpty())
                Glide.with(context)
                        .load(dailyMealModel.getDailyMealImageUrl())
                        .placeholder(R.drawable.ic_cloud_off_black_24dp)
                        .into(dailyMealImage);
            else
                Glide.clear(dailyMealImage);

            dailyMealTitle.setText(dailyMealModel.getDailyMealName());
            dailyMealDescription.setText(dailyMealModel.getDailyMealDescription());
            dailyMealOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(dailyMealModel);
                }
            });
        }


    }


}