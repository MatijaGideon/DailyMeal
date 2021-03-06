package net.gideonbros.dailymeal.util;

import java.util.ArrayList;
import java.util.List;
import net.gideonbros.dailymeal.data.models.DailyMealModel;
import net.gideonbros.dailymeal.data.models.RestaurantModel;

/**
 * Created by Matija on 3/6/2017.
 */

public class GenerateDataUtil {
  static final RestaurantModel gastronHome =
      new RestaurantModel(0, "Gastron Home", "59 Lavender Hill, London", "+44 20 3417 5639",
          "https://media-cdn.tripadvisor.com/media/photo-s/07/f5/50/78/gastronhome.jpg",
          "info@gastronhome.co.uk", "http://gastronhome.co.uk/");
  static final RestaurantModel theLedbury =
      new RestaurantModel(1, "The Kedbury", "127 Ledbury Road, London", "+44 20 7792 9090",
          "http://i.telegraph.co.uk/multimedia/archive/01967/ledbury_riot_1967482c.jpg",
          "info@theledbury.com ", "http://www.theledbury.com/");
  static final RestaurantModel typingRoom =
      new RestaurantModel(2, "Typing Room", "8 Patriot Square, London", "+44 20 7871 0461",
          "http://www.hospitality-interiors.net/sites/hospitality-interiors.net/files/styles/article-header/public/images/article/2014/10/2.w.jpg",
          "reservations@typingroom.com", "http://www.typingroom.com/");
  static final RestaurantModel theFiveFields =
      new RestaurantModel(3, "The Five Fields", "8-9 Blacklands Terrace , London",
          "+44 20 7838 1082",
          "http://2.bp.blogspot.com/-pk-Cg7MpsZo/VcMZEwr1FuI/AAAAAAAAru4/UnJAwYfrcYo/s1600/DSC04729.JPG",
          "info@fivefieldsrestaurant.com", "http://www.fivefieldsrestaurant.com/");
  static final RestaurantModel marianne =
      new RestaurantModel(4, "Marianne", "104A Chepstow Road, London", "44 20 3675 7750",
          "https://pbs.twimg.com/media/C5HX_9kWMAACrSv.jpg", "info@mariannerestaurant.com",
          "http://mariannerestaurant.com/");
  static final RestaurantModel sevenParkPlace =
      new RestaurantModel(5, "Seven Park Place", "7-8 Park Place , London", "+44 20 7316 1600",
          "http://noexpert.co.uk/food/wp-content/uploads/2011/06/tn_CIMG3484.jpg",
          "info@stjameshotelandclub.com", "http://www.stjameshotelandclub.com/restaurants-bar");
  static final RestaurantModel medlar =
      new RestaurantModel(6, "Medlar", "438 Kings Road , London", "+44 20 7349 1900",
          "http://www.medlarrestaurant.co.uk/files/_sjh6513-150.jpg", "info@medlarrestaurant.co.uk",
          "http://www.medlarrestaurant.co.uk/");
  static final RestaurantModel nama =
      new RestaurantModel(7, "Nama", "110 Talbot Rd, London", "+44 20 7313 4638",
          "http://www.julieslifestyle.com/sites/default/files/news_cover/nama-foods-560x560.jpg", " info@namafoods.com",
          "https://namafoods.com/");

  public static List<DailyMealModel> getDailyMeals() {
    List<DailyMealModel> dailyMeals = new ArrayList<>();
    List<RestaurantModel> restaurants = new ArrayList<>();
    restaurants.add(gastronHome);
    restaurants.add(theLedbury);
    restaurants.add(typingRoom);
    restaurants.add(theFiveFields);
    restaurants.add(marianne);
    restaurants.add(sevenParkPlace);
    restaurants.add(medlar);
    restaurants.add(nama);

    //final int min = 0;
    //final int max = restaurants.size() - 1;

    DailyMealModel spagetti =
        new DailyMealModel(0, "Spagheti Bolognese", "Deliciously meaty with a chilli kick.",
            "http://www.recipetineats.com/wp-content/uploads/2016/08/Spaghetti-Bolognese_3.jpg",
            "Great! Hot and spicy", restaurants.get(0));
    dailyMeals.add(spagetti);

    DailyMealModel bourbonChicken = new DailyMealModel(1, "Bourbon Chicken",
        "Created by a Chinese cook who worked in a restaurant on Bourbon Street.",
        "http://img.sndimg.com/food/image/upload/h_420,w_560,c_fit/v1/img/recipes/45/80/9/ggIfR8tpTUOkImhj16cV_DSC_1678-2.jpg",
        "OMG! I have died and gone to heaven.", restaurants.get(1));
    dailyMeals.add(bourbonChicken);

    DailyMealModel creamyBurritoCasserole = new DailyMealModel(2, "Creamy Burrito Casserole",
        "Burrito can be personalized to suit all tastes.",
        "http://pictures.food.com/api/file//3g2y484QaO09dP4OhpLw-Creamy-Burrito-Casserole---33919-4.JPG/convert?loc=/pictures.food.com/recipes/33/91/9/aIvTUi7pTuu7SHGeRoZq_Creamy%20Burrito%20Casserole%20-%2333919-4.JPG&width=555&height=416&fit=max&flags=progressive&quality=95",
        "It was easy to put together, and I used low-fat versions of the ingredients.",
        restaurants.get(2));
    dailyMeals.add(creamyBurritoCasserole);

    DailyMealModel theUltimateGreekSalad = new DailyMealModel(3, "The Ultimate Greek Salad",
        "The Greek salad is known and loved around the world.",
        "http://img.sndimg.com/food/image/upload/h_488,w_651,c_fit/v1/img/recipes/90/97/5/xfirb0rQRMPAHtkSwTyA_THE%20FOOD%20GAYS%20-%20GREEK%20SALAD-2.jpg",
        "The dressing was absolutely heavenly.", restaurants.get(3));
    dailyMeals.add(theUltimateGreekSalad);

    DailyMealModel salisburySteak = new DailyMealModel(4, "The Very Best Salisbury Steak",
        "Seasoned ground beef patties are browned and simmered in a savory onion soup sauce.",
        "http://img.sndimg.com/food/image/upload/h_420,w_560,c_fit/v1/img/recipes/56/69/4/fVgy28p7Qqyb5s5nw1UR_DSC_0279.JPG",
        "Best steak ever :)", restaurants.get(4));
    dailyMeals.add(salisburySteak);

    DailyMealModel oliveGarden = new DailyMealModel(5, "Olive Garden", "Sweet taste of Italy.",
        "http://img.sndimg.com/food/image/upload/h_420,w_560,c_fit/v1/img/recipes/38/29/8/21quB2GzTFC4vQ5FEGBZ_IAM_5119.jpg",
        "Amazing, just like I tried in Florence", restaurants.get(5));
    dailyMeals.add(oliveGarden);

    DailyMealModel chineseFriedRice =
        new DailyMealModel(6, "Chinese Fried Rice", "Most popular rice meal.",
            "http://pictures.food.com/api/file/gpcRqESQQZGx9GUrVljG-IMG_0704.jpg/convert?loc=/pictures.food.com/recipes/38/74/8/uXUajHqyRq0TEyO7rfDX_IMG_0704.jpg&width=560&height=420&fit=crop&flags=progressive&quality=95",
            "Just like I get at my favorite Chinese restaurant", restaurants.get(6));
    dailyMeals.add(chineseFriedRice);

    DailyMealModel fajitas =
        new DailyMealModel(7, "Chipotle Chicken Fajitas", "These spicy chicken fajitas take barely any time to make.",
            "http://clv.h-cdn.co/assets/16/06/1600x2400/gallery-1455298956-clx010116brkfeature-04.jpg",
            "They taste great!", restaurants.get(7));
    dailyMeals.add(fajitas);

    return dailyMeals;
  }
}