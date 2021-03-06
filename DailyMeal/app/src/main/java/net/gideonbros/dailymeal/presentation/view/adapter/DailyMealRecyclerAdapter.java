package net.gideonbros.dailymeal.presentation.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import net.gideonbros.dailymeal.R;
import net.gideonbros.dailymeal.data.models.DailyMealModel;

/**
 * Created by Matija on 4.3.2017..
 */

public class DailyMealRecyclerAdapter extends RecyclerView.Adapter
    implements RealmChangeListener<RealmResults<DailyMealModel>> {

  @NonNull private Context context;
  @NonNull private OnClickListener listener;
  @Nullable private RealmResults<DailyMealModel> dailyMeals;

  public interface OnClickListener {
    void onOrderClick(int restaurantId);

    void onDirectionsClick(String restaurantLocation);
  }

  public DailyMealRecyclerAdapter(Context context, OnClickListener listener) {
    this.context = context;
    this.listener = listener;
  }

  public void addListener() {
    this.dailyMeals.addChangeListener(this);
  }

  public void setDailyMeals(RealmResults<DailyMealModel> dailyMeals) {
    this.dailyMeals = dailyMeals;
    notifyDataSetChanged();
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.card_item_daily_meal, parent, false);
    return new DailyMealViewHolder(view);
  }

  @Override public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
    final DailyMealModel dailyMealModel = dailyMeals.get(position);
    ((DailyMealViewHolder) holder).setData(dailyMealModel);
  }

  @Override public int getItemCount() {
    return dailyMeals != null ? dailyMeals.size() : 0;
  }

  @Override public void onChange(RealmResults<DailyMealModel> element) {
    notifyDataSetChanged();
  }

  public class DailyMealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.card_image) ImageView dailyMealImage;
    @BindView(R.id.card_title) TextView dailyMealTitle;
    @BindView(R.id.card_text) TextView dailyMealDescription;

    @BindView(R.id.action_button) Button dailyMealOrder;
    @BindView(R.id.directions_button) ImageButton dailyMealDirection;

    public DailyMealViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, itemView);
      dailyMealOrder.setOnClickListener(this);
      dailyMealDirection.setOnClickListener(this);
    }

    void setData(@NonNull final DailyMealModel dailyMealModel) {
      if (!dailyMealModel.getDailyMealImageUrl().isEmpty()) {
        Glide.with(context)
            .load(Uri.parse(dailyMealModel.getDailyMealImageUrl()))
            .listener(new RequestListener<Uri, GlideDrawable>() {
              @Override
              public boolean onException(Exception e, Uri model, Target<GlideDrawable> target,
                  boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
              }

              @Override public boolean onResourceReady(GlideDrawable resource, Uri model,
                  Target<GlideDrawable> target, boolean isFromMemoryCache,
                  boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
              }
            })
            .crossFade()
            .into(dailyMealImage);
      } else {
        Glide.clear(dailyMealImage);
      }

      dailyMealTitle.setText(dailyMealModel.getDailyMealName());
      dailyMealDescription.setText(dailyMealModel.getDailyMealDescription());
    }

    @Override public void onClick(View v) {
      switch (v.getId()) {
        case R.id.action_button:
          listener.onOrderClick(dailyMeals.get(getAdapterPosition()).getRestaurant().getId());
          break;
        case R.id.directions_button:
          listener.onDirectionsClick(
              dailyMeals.get(getAdapterPosition()).getRestaurant().getRestaurantAddress());
          break;
        default:
      }
    }
  }
}
