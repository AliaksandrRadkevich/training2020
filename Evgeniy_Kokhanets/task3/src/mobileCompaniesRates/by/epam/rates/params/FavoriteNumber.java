package mobileCompaniesRates.by.epam.rates.params;

public class FavoriteNumber extends Params{
    private String favoriteNumber;

    @Override
    public void setValue(String value) {
        this.favoriteNumber = value;
    }

    @Override
    public String getValue() {
        return this.favoriteNumber;
    }

    @Override
    public String toString() {
        return "FavoriteNumber [favoriteNumber=" + favoriteNumber + "]";
    }
    
    

}
