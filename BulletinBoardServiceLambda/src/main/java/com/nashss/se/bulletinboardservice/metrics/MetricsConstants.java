package com.nashss.se.bulletinboardservice.metrics;

/**
 * Constant values for use with metrics.
 */
public class MetricsConstants {
    public static final String GETUSER_PLAYLISTNOTFOUND_COUNT = "GetPlaylist.PlaylistNotFoundException.Count";
    public static final String UPDATEPLAYLIST_INVALIDATTRIBUTEVALUE_COUNT =
            "UpdatePlaylist.InvalidAttributeValueException.Count";
    public static final String UPDATEPLAYLIST_INVALIDATTRIBUTECHANGE_COUNT =
            "UpdatePlaylist.InvalidAttributeChangeException.Count";
    public static final String SERVICE = "Service";
    public static final String SERVICE_NAME = "BulletinBoardService";
    public static final String NAMESPACE_NAME = "U3/BulletinBoardService";

    public static final String GETUSER_USERNOTFOUND_COUNT = "GetUser.UserNotFoundException.Count";
    public static final String GETAD_ADNOTFOUND_COUNT = "GetAd.AdNotFoundException.Count";
    public static final String GETVENUE_VENUENOTFOUND_COUNT = "GetVenue.VenueNotFoundException.Count";
    public static final String GETLOCATION_LOCATIONNOTFOUND_COUNT = "GetLocation.LocationNotFoundException.Count";

    public static final String GETMEALPLAN_MEALPLANNOTFOUND_COUNT = "GetMealPlan.MealPlanNotFoundException.Count";

    public static final String GETRECIPE_RECIPENOTFOUND_COUNT = "GetRecipe.RecipeNotFoundException.Count";
}
