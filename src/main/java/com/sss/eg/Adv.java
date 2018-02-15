package com.sss.eg;


import java.util.Objects;

public class Adv {
    private String adId;
    private String adsCategoryName;
    private String adsRegion;
    private String price;
    private String description;

    public Adv(String adId) {
        this.adId = adId;
    }

    public String getAdId() {
        return adId;
    }

    public String getAdsCategoryName() {
        return adsCategoryName;
    }

    public void setAdsCategoryName(String adsCategoryName) {
        this.adsCategoryName = adsCategoryName;
    }

    public String getAdsRegion() {
        return adsRegion;
    }

    public void setAdsRegion(String adsRegion) {
        this.adsRegion = adsRegion;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesription() {
        return description;
    }

    public void setDescription(String desription) {
        this.description = desription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adv adv = (Adv) o;
        return adId.equals(adv.adId) &&
                adsCategoryName.equals(adv.adsCategoryName) &&
                ((adsRegion == null || adv.adsRegion == null) || adsRegion.equals(adv.adsRegion)) &&
                price.equals(adv.price) &&
                (description.contains(adv.description) || adv.description.contains(description));
    }

    @Override
    public int hashCode() {
        return Objects.hash(adId, adsCategoryName, adsRegion, price, description);
    }
}
